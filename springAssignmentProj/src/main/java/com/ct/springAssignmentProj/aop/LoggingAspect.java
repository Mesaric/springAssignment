package com.ct.springAssignmentProj.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.ct.springAssignmentProj.translation.TranslationController.*(..))")
    //pointcut annotation with a pointcut expression
    public void loggingControllerPointcut() {

    }

    /**
     * Adding advice that runs before joinPoint with loggingControllerPointcut() signature
     *
     * @param joinPoint
     */
    @Before("loggingControllerPointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("Before translateController method invoked:" + joinPoint.getSignature());
    }

    /**
     * Adding advice that runs after joinPoint with loggingControllerPointcut() signature
     *
     * @param joinPoint
     */
    @After("loggingControllerPointcut()")
    public void after(JoinPoint joinPoint) {
        log.info("After translateController method invoked:" + joinPoint.getSignature());
    }
}
