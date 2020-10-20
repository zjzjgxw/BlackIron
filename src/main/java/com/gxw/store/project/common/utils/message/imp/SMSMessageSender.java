package com.gxw.store.project.common.utils.message.imp;

import com.gxw.store.project.common.utils.message.MessageSender;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SMSMessageSender implements MessageSender {


    @Override
    public  boolean sendCode(String account, String code) {
        /**
         * 使用短信服务商进行发送短信
         */
        System.out.println("account:"+account+" code:"+code);
        return true;
    }

    @Override
    public  boolean sendCode(Set<String> accounts, String code) {
        return false;
    }
}
