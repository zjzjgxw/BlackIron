package com.gxw.store.project.sso.service;


import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.sso.dto.LoginUser;
import com.gxw.store.project.sso.dto.PhoneAccount;

/**
 * C端登陆
 */
public interface SsoService {

    /**
     * 登陆
     *
     * @param loginUser
     * @return
     */
    ResponseResult login(LoginUser loginUser);


    /**
     * 手机登陆
     *
     * @param phoneAccount
     * @param cachedCode   已经发送的code
     * @return
     */
    ResponseResult loginByPhone(PhoneAccount phoneAccount, String cachedCode);

    /**
     * 微信登陆
     *
     * @param code
     * @return
     */
    default ResponseResult loginByWeiXin(String code, Long businessId){
        return null;
    }

    /**
     * 获取SessionKey
     *
     * @param userId
     * @return
     */
    default String getWxSessionKey(Long userId){
        return null;
    }

    ResponseResult refreshToken(String token);

    ResponseResult logout(String token);

}
