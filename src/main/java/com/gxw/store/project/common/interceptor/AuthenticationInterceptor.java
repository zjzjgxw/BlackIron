package com.gxw.store.project.common.interceptor;


import com.gxw.store.project.common.utils.exception.EmptyTokenException;
import com.gxw.store.project.sso.service.TokenService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 请求验证
 */
public class AuthenticationInterceptor implements HandlerInterceptor {


    @Resource
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //需要认证
        if(method.isAnnotationPresent(NeedToken.class)){
            NeedToken needToken = method.getAnnotation(NeedToken.class);
            if(needToken.required()){
                String token = request.getHeader("Authorization");// 从 http 请求头中取出 token
                if(token == null || token.isEmpty()){
                    throw new EmptyTokenException("token 为空");
                }
                String uri = request.getRequestURI();
                if(!uri.startsWith("/app")){
                    tokenService.setCustomPrefix("staff:");
                }
                tokenService.checkToken(token);
            }
        }


        //默认情况下都通过
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
