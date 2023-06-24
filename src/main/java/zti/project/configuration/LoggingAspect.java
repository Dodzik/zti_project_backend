package zti.project.configuration;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.jboss.logging.Logger;
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    Logger logger = Logger.getLogger(LoggingAspect.class);

    @Pointcut(value = "execution(* zti.project.repository.*.*(..))")
    public void loggingPointcut() {
    }

    @Around("loggingPointcut()")
    public Object LoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Before method: " + proceedingJoinPoint.getSignature());
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("Class name: " + className);
        logger.info("Method name: " + methodName);
        Object object = proceedingJoinPoint.proceed();
        logger.info("After method: " + proceedingJoinPoint.getSignature());
        return object;
    }
}

