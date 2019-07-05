package com.baiyu.tmall.advice;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class NoAuthExceptionAdvice {
    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system/unauth");
        return modelAndView;
    }
}
