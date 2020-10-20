package com.gxw.store.project.common.utils.message.imp;

import com.gxw.store.project.common.utils.message.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EmailMessageSender implements MessageSender {

    private static final Logger log = LoggerFactory.getLogger(EmailMessageSender.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public  boolean sendCode(String account, String code) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            // 设置收件人，寄件人
            simpleMailMessage.setTo(account);
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setSubject("验证码");
            simpleMailMessage.setText("您的验证码为:"+code+",有效期为10分钟");
            // 发送邮件
            mailSender.send(simpleMailMessage);
        }catch (Exception e){
            log.info(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public  boolean sendCode(Set<String> accounts, String code) {
        return false;
    }
}
