package com.example.crud.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {
    @AfterThrowing(pointcut = "execution(* com.example.crud.service.*.*(..))", throwing = "ex")
    public void handleException(Exception ex) {
        System.out.println("An exception occurred: " + ex.getMessage());
    }
}