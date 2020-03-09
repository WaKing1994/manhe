package com.manhe.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Aspect
@Component
@Slf4j
public class LoginAspect {

    private static final String INDEX = "/admin/login";


    @Pointcut("execution(* com.manhe.admin.controller..*(..))")
    public void LoginAop() {
    }

    @Before("LoginAop()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        try {
            HttpServletRequest request = getNativeRequest();
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                hasNoPermision();
            }
        } catch (Exception e) {
            hasNoPermision();
        }
    }

    private void hasNoPermision() throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.sendRedirect(INDEX);
    }

    private HttpServletRequest getNativeRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
}
