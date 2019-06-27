package com.baiyu.tmall.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class ControllerAdvice {
    @ExceptionHandler(Exception.class)
    public String validateException(HttpServletRequest request, Exception e) {
        return "a";
    }
}
