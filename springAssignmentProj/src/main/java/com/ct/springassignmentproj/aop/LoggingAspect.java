package com.ct.springassignmentproj.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    @Pointcut("@annotation(com.ct.springassignmentproj.aop.Log)")
    public void pointcut() {}

    @Before("pointcut()")
    public void logMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder arguments = new StringBuilder();
        for (Object arg : args) {
            arguments
                    .append("Type: ").append(arg.getClass().getSimpleName())
                    .append("Value: ").append(arg);
        }
        log.info("Executing service: {} with arguments: {}", methodName, arguments);
    }
}
