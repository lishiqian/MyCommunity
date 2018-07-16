package com.lsq.community.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopTest {

    public AopTest(){
        System.out.println("--------------AopTest-----------------");
    }

    @Pointcut("execution (* com.lsq.community.service.*.*(..))")
    public void userAdd(){
        System.out.println("Pointcut:userAdd.............");
    }

    @Before("userAdd()")
    public void beforeUserAdd(){
        System.out.println("beforeUserAdd--------------");
    }


    @After("userAdd()")
    public void afterUserAdd(){
        System.out.println("userAdd---------------------");
    }
}
