package org.radak.library.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    @Before("@annotation(Logged)")  //Jednostavniji nacin sa anotacijom '@logged'
    public void logStartingExecutionMethod(JoinPoint jp){
        System.out.println("Before execution method. [LOGGED]. ");
        System.out.println(jp.getSignature());
        //Ispis argumenata u konzoli sa vrednostima
        for(Object o : jp.getArgs()){
            System.out.println(o);
        }

    }

    @Around("execution(* org.radak.library.app.controller.AdminController.*(Long))")
    public ResponseEntity<Object> logAroundExecution(ProceedingJoinPoint jp){
        System.out.println("Before execution method. [AROUND].");
        System.out.println(jp.getSignature());
        try {
            Object [] args = jp.getArgs();
            if((Long)args[0] <= 0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Object result = jp.proceed(args);
            return (ResponseEntity<Object>)result;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("After execution method. [AROUND].");
        return null;
    }

    //Nakon izvrsavanja ispisuje metode u konzoli
    @After("execution(* org.radak.library.app.controller.AdminController.*(Long)) && args(id,..)")
    public void logEndExecution(Long id){
        System.out.println("After execution method.");
        System.out.println(id);
    }
}
