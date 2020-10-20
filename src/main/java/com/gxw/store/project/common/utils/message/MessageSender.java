package com.gxw.store.project.common.utils.message;

import java.util.Set;

public interface MessageSender {

    /**
     * 发送验证码
     *
     * @param account
     * @param code
     */
     boolean sendCode(String account, String code);

    /**
     * 群发验证码
     *
     * @param accounts
     * @param code
     */
     boolean sendCode(Set<String> accounts, String code);

}
