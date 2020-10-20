package com.gxw.store.project.sso.controller;



import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.sso.dto.MessageAccount;
import com.gxw.store.project.sso.service.imp.MessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 发送验证码
 */
@RestController
@RequestMapping("/sso/message")
public class MessageController {

    @Autowired
    public MessageServiceImp messageServiceImp;

    @PostMapping("/send")
    public ResponseResult sendCode(@RequestBody MessageAccount account) {
        boolean success = messageServiceImp.sendCodeByPhone(account.getPhone());
        if (success) {
            return ResponseResult.success("发送成功");
        }
        return ResponseResult.error();
    }
}
