package com.gxw.store.project.sso.controller;



import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.common.utils.SessionUtils;
import com.gxw.store.project.sso.dto.LoginUser;
import com.gxw.store.project.sso.service.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sso/admins")
public class SsoAdminController {

    @Autowired
    @Qualifier("adminLoginServiceImp")
    private SsoService ssoService;

    @PostMapping("/login")
    public ResponseResult login(@Validated @RequestBody LoginUser loginUser){
        return ssoService.login(loginUser);
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

}
