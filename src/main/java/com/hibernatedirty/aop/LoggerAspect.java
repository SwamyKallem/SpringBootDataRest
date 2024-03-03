package com.hibernatedirty.aop;


import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonSerializer;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Before("@annotation(com.hibernatedirty.annotations.LogRequest)")
    public void logSportsIconBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
			/*
			 * try { JsonSerializer serializer = new JsonSerializer(); String json =
			 * serializer.prettyPrint(true).deepSerialize(log); logger.info(json); } catch
			 * (Exception e) { logger.error("Could not serialize data. "+e.getMessage()); }
			 */       

        	  log.info("******* "+arg.getClass()+" before :: {}", arg.toString());
           
        }
    }

    @AfterReturning(value = "@annotation(com.hibernatedirty.annotations.LogResponse)", returning = "result")
    public void logSportsIconAfter(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        if (Objects.nonNull(result)) {
            if (result instanceof ResponseEntity) {
                ResponseEntity responseEntity = (ResponseEntity) result;

                if (responseEntity.getStatusCode().value() == 200)
                    log.info("******* Returning object :: {}", responseEntity.getBody());
                else
                    log.error("Something went wrong while logging...!");
            }
        }
    }
}
