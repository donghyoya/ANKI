package com.kmu.anki.backend.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerLogTracer {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController * )")
    public void restControllerAnotation(){}

    @Around("restControllerAnotation()")
    public Object controllerLogTrace(ProceedingJoinPoint joinPoint) throws Throwable{
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.info("[API TRACE] Executing: {}.{}()", className, methodName);

        try {
            Object result = joinPoint.proceed();
            log.info("[API TRACE] Executed: {}.{}()", className, methodName);
            return result;
        } catch (Throwable throwable) {
            log.error("[API TRACE] Exception in {}.{}(): {}", className, methodName, throwable.getMessage(), throwable);
            throw throwable;
        }
    }
}
