package ru.fonzy.fnotes.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    @AfterReturning(value = "execution(* ru.fonzy.fnotes.controller..*(..)) ||" +
                            "execution(* ru.fonzy.fnotes.service..*(..))",
                    returning = "retVal")
    public void afterReturningLogger(JoinPoint joinPoint, Object retVal) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        System.out.println("Class: " + method.getDeclaringClass());
        System.out.println("Called method: " + method.getName());
        System.out.println("Parametes: " + Arrays.toString(method.getGenericParameterTypes()));

        if (retVal != null)
            System.out.println("Returnned value: "  + retVal);

        System.out.println("---------------------------------");
    }
}
