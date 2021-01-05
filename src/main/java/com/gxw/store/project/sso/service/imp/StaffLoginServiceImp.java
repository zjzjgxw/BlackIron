package com.gxw.store.project.sso.service.imp;

import com.gxw.store.project.common.utils.Md5Utils;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.exception.NotExistException;
import com.gxw.store.project.sso.dto.LoginUser;
import com.gxw.store.project.sso.dto.PhoneAccount;
import com.gxw.store.project.sso.service.SsoService;
import com.gxw.store.project.sso.service.TokenService;
import com.gxw.store.project.user.entity.business.Staff;
import com.gxw.store.project.user.entity.business.StaffStatus;
import com.gxw.store.project.user.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StaffLoginServiceImp implements SsoService {
    @Autowired
    private StaffService staffService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginTriedService loginTriedService;

    private static final Integer CAN_TRY_TIMES = 5;

    private static final String KEY_PREFIX = "staff:";

    @Override
    public ResponseResult login(LoginUser loginUser) {
        Staff staff = staffService.getStaffByAccount(loginUser.getAccount());
        if (staff == null) {  //请求成功，但是没有找到对应的用户信息
            throw new NotExistException("用户未找到");
        }
        if(staff.getStatus() == StaffStatus.UNUSABLE){
            return ResponseResult.error("该账户已被禁");
        }
        //检查尝试次数
        Integer times = loginTriedService.getTriedTimes(loginUser.getAccount());
        if (times != null && times >= CAN_TRY_TIMES) {
            return ResponseResult.error("尝试次数过多，请稍后再试");
        }
        //验证账号密码
        if (checkPassword(loginUser, staff.getPassword())) {
            //生成token
            HashMap<String, String> token = tokenService.createToken(staff.getId(), staff.getName(), staff.getBusinessId(), KEY_PREFIX);
            return ResponseResult.success(token);
        } else { //记录失败次数
            loginTriedService.record(loginUser.getAccount(), KEY_PREFIX);
            return ResponseResult.error("用户密码错误");
        }
    }

    @Override
    public ResponseResult loginByPhone(PhoneAccount phoneAccount, String cachedCode) {
        return null;
    }

    /**
     * 更新token
     *
     * @param token
     * @return
     */
    @Override
    public ResponseResult refreshToken(String token) {
        HashMap<String, String> res = tokenService.RefreshToken(token, KEY_PREFIX);
        return ResponseResult.success(res);
    }

    @Override
    public ResponseResult logout(String token) {
        tokenService.deleteToken(token, KEY_PREFIX);
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
