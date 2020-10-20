package com.gxw.store.project.common.utils;

import com.gxw.store.project.common.utils.exception.EmptyTokenException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于获取登录用户token，id,name 信息工具类
 */
public class SessionUtils {
    private static final String AUTHORIZATION = "Authorization";

    private static final String ACCESS_TOKEN = "AccessToken";

    public static Long getUserId() {
        String token = getToken();
        if(StringUtils.isEmpty(token)){
            throw new EmptyTokenException("token 为空");
        }
        return JwtTokenUtil.getUserId(token);
    }

    /**
     * 获取用户id
     * @param allowNull 允许返回为null
     * @return
     */
    public static Long getUserId(boolean allowNull)
    {
        String token = getToken();
        if(StringUtils.isEmpty(token)){
           if(allowNull){
               return null;
           }else {
               throw new EmptyTokenException("token 为空");
           }
        }
        return JwtTokenUtil.getUserId(token);
    }

    public static String getUserName() {
        String token = getToken();
        return JwtTokenUtil.getUserName(token);
    }

    public static String getToken() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(AUTHORIZATION);
        return token;
    }


    public static Long getAdminId() {
        String token = getAccessToken();
        if(StringUtils.isEmpty(token)){
            throw new EmptyTokenException("token 为空");
        }
        return JwtTokenUtil.getUserId(token);
    }


    public static String getAccessToken(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(ACCESS_TOKEN);
        return token;
    }
}
