package com.gxw.store.project.sso.service.imp;


import com.gxw.store.project.common.utils.cache.RedisCache;
import com.gxw.store.project.common.utils.exception.NotExistException;
import com.gxw.store.project.common.utils.message.MessageSender;
import com.gxw.store.project.common.utils.message.imp.EmailMessageSender;
import com.gxw.store.project.common.utils.message.imp.SMSMessageSender;
import com.gxw.store.project.user.entity.User;
import com.gxw.store.project.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * 发送 信息（短信/邮件）
 */
@Service
public class MessageServiceImp {


    private MessageSender curMessageSender;

    @Resource
    private EmailMessageSender emailMessageSender;
    @Resource
    private SMSMessageSender smsMessageSender;

    @Resource
    private RedisCache redisCache;

    @Resource
    private UserService userService;

    private final static String CODE_PRE = "code_pre:";
    private final static Long CODE_EXPIRE_TIME = 600L; //code 过期时间，单位秒

    public MessageServiceImp() {
        curMessageSender = null;
    }

    public void setMessageSender(int type) {
        switch (type) {
            case 1:
                curMessageSender = smsMessageSender;
                break;
            case 2:
                curMessageSender = emailMessageSender;
                break;
            default:
                break;
        }
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    public boolean sendCodeByPhone(String phone) {
        String code = getCodeFromCache(phone);
        if(code == null){ //如果没有生成过code 则生成验证码 （也就说在过期时效内只能发一次验证码，防止code 被刷）
            User user = userService.selectUserByPhone(phone);
            if (user == null) {  //请求成功，但是没有找到对应的用户信息
                throw new NotExistException("用户未找到");
            }
            //生成验证码
            code = randomCode();
            if (curMessageSender == null) {
                setMessageSender(1);
            }
            //将code放入缓存
            cacheCode(phone,code);
            return curMessageSender.sendCode(phone, code);
        }else{
            return false;
        }
    }

    /**
     * 发送邮件
     *
     * @param email
     * @return
     */
    public boolean sendCodeByEmail(String email) {
        String code = randomCode();
        if (curMessageSender == null) {
            setMessageSender(2);
        }
        //将code放入缓存
        cacheCode(email,code);
        return curMessageSender.sendCode(email, code);
    }


    private String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    private void cacheCode(String account, String code) {
        redisCache.setCacheObject(CODE_PRE + account, code,CODE_EXPIRE_TIME);
    }

    public String getCodeFromCache(String account){
        return redisCache.getCacheObject(CODE_PRE + account);
    }
}
