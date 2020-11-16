package com.gxw.store.project.sso.service.imp;

import com.gxw.store.project.common.utils.Md5Utils;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.WeiXinUtils;
import com.gxw.store.project.common.utils.cache.RedisCache;
import com.gxw.store.project.common.utils.exception.NotExistException;
import com.gxw.store.project.sso.dto.LoginUser;
import com.gxw.store.project.sso.dto.PhoneAccount;
import com.gxw.store.project.sso.service.SsoService;
import com.gxw.store.project.sso.service.TokenService;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class UserLoginServiceImp implements SsoService {
    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @Resource
    private LoginTriedService loginTriedService;

    @Resource
    private WeiXinUtils weiXinUtils;

    @Resource
    private RedisCache redisCache;

    private static final Integer CAN_TRY_TIMES = 5;

    //微信openId 缓存前缀
    private static final String OPEN_ID_PREFIX = "wx_open_id:";

    //缓存过期时间
    protected static final long EXPIRE_TIME = 30; //30分钟


    @Override
    public ResponseResult login(LoginUser loginUser) {
        User user = userService.selectUserByAccount(loginUser.getAccount());
        if (user == null) {  //请求成功，但是没有找到对应的用户信息
            throw new NotExistException("用户未找到");
        }
        //检查尝试次数
        Integer times = loginTriedService.getTriedTimes(loginUser.getAccount());
        if (times != null && times >= CAN_TRY_TIMES) {
            return ResponseResult.error("尝试次数过多，请稍后再试");
        }
        //验证账号密码
        if (checkPassword(loginUser, user.getPassword())) {
            //生成token
            HashMap<String, String> token = tokenService.createToken(user.getId(), user.getName());
            return ResponseResult.success(token);
        } else { //记录失败次数
            loginTriedService.record(loginUser.getAccount());
            return ResponseResult.error("用户密码错误");
        }

    }

    @Override
    public ResponseResult loginByPhone(PhoneAccount phoneAccount, String cachedCode) {
        User user = userService.selectUserByPhone(phoneAccount.getPhone());
        if (user == null) {  //请求成功，但是没有找到对应的用户信息
            throw new NotExistException("用户未找到");
        }
        //检查尝试次数
        Integer times = loginTriedService.getTriedTimes(phoneAccount.getPhone());
        if (times != null && times >= CAN_TRY_TIMES) {
            return ResponseResult.error("尝试次数过多，请稍后再试");
        }
        if (phoneAccount.getCode().equals(cachedCode)) { //验证用户输入的code 和缓存中的code
            //生成token
            HashMap<String, String> token = tokenService.createToken(user.getId(), user.getName());
            return ResponseResult.success(token);
        } else {
            loginTriedService.record(phoneAccount.getPhone());
            return ResponseResult.error("验证码错误");
        }
    }

    @Override
    public ResponseResult loginByWeiXin(String code) {

        HashMap<String, String> wxSession = weiXinUtils.codeToSession(code);
        String openId = wxSession.get("openid");

        // 根据openId查找用户信息，不存在则新建一个用户
        User user = userService.selectUserByOpenId(openId, true);
        if (user == null) {  //请求成功，但是没有找到对应的用户信息
            throw new NotExistException("用户未找到");
        }
        //保存sessionKey 到缓存中，用于后续获取微信用户详情时进行数据校验。
        redisCache.setCacheObject(OPEN_ID_PREFIX + user.getId(), wxSession, EXPIRE_TIME);
        //生成token
        HashMap<String, String> token = tokenService.createToken(user.getId(), user.getName(), user.getBusinessId());
        return ResponseResult.success(token);
    }

    @Override
    public ResponseResult getWxSession(Long userId) {
        HashMap<String, String> cacheObject = redisCache.getCacheObject(OPEN_ID_PREFIX + userId);
        if (cacheObject == null) {
            return ResponseResult.error("session key 失效请重新登陆");
        }
        return ResponseResult.success(cacheObject);
    }

    /**
     * 更新token
     *
     * @param token
     * @return
     */
    @Override
    public ResponseResult refreshToken(String token) {
        HashMap<String, String> res = tokenService.RefreshToken(token);
        return ResponseResult.success(res);
    }

    @Override
    public ResponseResult logout(String token) {
        tokenService.deleteToken(token);
        return ResponseResult.success();
    }

    /**
     * 检查密码
     *
     * @param loginUser 用户输入信息
     * @param password  数据库中的密码信息
     * @return
     */
    private boolean checkPassword(LoginUser loginUser, String password) {
        String password_md5 = Md5Utils.hash(loginUser.getPassword());
        return password_md5.equals(password);
    }

}
