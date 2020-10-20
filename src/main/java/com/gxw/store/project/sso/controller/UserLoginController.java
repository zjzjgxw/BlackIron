package com.gxw.store.project.sso.controller;

import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.sso.dto.LoginUser;
import com.gxw.store.project.sso.dto.PhoneAccount;
import com.gxw.store.project.sso.service.SsoService;
import com.gxw.store.project.sso.service.imp.MessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *  C端用户登录
 */
@RestController
@RequestMapping("/sso/users")
public class UserLoginController {

    @Autowired
    @Qualifier("userLoginServiceImp")
    private SsoService ssoService;

    @Autowired
    private MessageServiceImp messageServiceImp;

    @PostMapping("/login")
    public ResponseResult login(@Validated @RequestBody LoginUser loginUser){
        return ssoService.login(loginUser);
    }

    @PostMapping("/loginByPhone")
    public ResponseResult loginByPhone(@Validated @RequestBody PhoneAccount phoneAccount){
        String cachedCode  = messageServiceImp.getCodeFromCache(phoneAccount.getPhone());
        if(cachedCode == null){
            return ResponseResult.error("登陆失败");
        }
        return ssoService.loginByPhone(phoneAccount,cachedCode);
    }

    @GetMapping("/refreshToken")
    public ResponseResult refreshToken(@RequestParam String token){
        return ssoService.refreshToken(token);
    }

    @GetMapping("/logout")
    public ResponseResult logout(){
        String token  = SessionUtils.getToken();
        if(token == null){
            return ResponseResult.success();
        }
        return ssoService.logout(SessionUtils.getToken());
    }

    /**
     * 微信登陆
     * @param code
     * @return
     */
    @GetMapping("/wxlogin")
    public ResponseResult WxLogin(@RequestParam("code") String code){
        return ssoService.loginByWeiXin(code);
    }

    @GetMapping("/wxSession")
    public ResponseResult wxSession(@RequestParam Long userId)
    {
        return ssoService.getWxSession(userId);
    }
}
