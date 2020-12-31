package com.gxw.store.project.app.controller;


import com.gxw.store.project.common.controller.BaseController;
import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.sso.service.SsoService;
import com.gxw.store.project.user.dto.WxEncryptedData;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("app/users")
public class AppUserController extends BaseController {

    @Resource
    private UserService userService;


    @Resource
    @Qualifier("userLoginServiceImp")
    private SsoService ssoService;

    @NeedToken
    @PutMapping()
    public ResponseResult update(@Valid @RequestBody User user) {
        userService.updateUser(user);
        return ResponseResult.success();
    }

    @NeedToken
    @GetMapping("/current")
    public ResponseResult current(){
        User user = userService.selectUserById(SessionUtils.getUserId());
        HashMap<String, User> res = new HashMap<>();
        res.put("user", user);
        return ResponseResult.success(res);
    }


    /**
     * 微信更新用户信息
     *
     * @param wxEncryptedData
     * @return
     */
    @NeedToken
    @PutMapping("/wx")
    public ResponseResult updateWeiXin(@Valid @RequestBody WxEncryptedData wxEncryptedData) {
        Long userId = SessionUtils.getUserId();
        String sessionKey = ssoService.getWxSessionKey(userId);
        if(userService.updateWxUser(userId, sessionKey, wxEncryptedData)){
            return ResponseResult.success();
        }else{
            return ResponseResult.error();
        }
    }

}
