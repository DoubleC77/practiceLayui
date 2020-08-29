package com.fchan.layui.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fchan.layui.entity.AopTestEntity;
import com.fchan.layui.represent.service.JdkProxySubject;
import com.fchan.layui.represent.service.Subject;
import com.fchan.layui.represent.service.impl.RealSubject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TestAspect {

    private static ObjectMapper JSON = new ObjectMapper();

    @Pointcut(value = "execution(* com.fchan.layui.service.SpringAopTestService.insert(..))")
    public void insertPointcut(){}

    /**
     * 获取到 before 通知时的方法入参
     * @param entity
     * @return
     */
    @Before("insertPointcut() && args(entity)")
    public void beforeInsert(AopTestEntity entity){
        if(!"hello".equals(CurrentUserHolder.get())){
            throw new RuntimeException("用户不对");
        }
    }

    @Pointcut("@annotation(CheckUser)")
    public void CheckUser(){

    }

    @After(value = "CheckUser()")
    public void afterDelete(JoinPoint joinPoint){
        log.info("删除一条数据之后:{}",joinPoint.getArgs());
    }

    /**
     * returning = "returnValue"要和方法的形参变量名returnValue一致
     * @param returnValue
     * @throws JsonProcessingException
     */
    @AfterReturning(value = "insertPointcut()",returning = "returnValue")
    public void testAfterReturning(Object returnValue) throws JsonProcessingException {

        AopTestEntity aopTestEntity = JSON.readValue(returnValue.toString(),AopTestEntity.class);
        aopTestEntity.setName("被修改了的名字");
        log.info("AfterReturning中获取到的返回的值:{}",returnValue);
    }



    @Around(value = "insertPointcut()")
    public Object testAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("方法执行前");
        //可以获取到方法执行后的参数
        Object returnValue = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        AopTestEntity aopTestEntity = JSON.readValue(returnValue.toString(),AopTestEntity.class);
        aopTestEntity.setName("被修改了的名字");
        log.info("方法执行后");
        return JSON.writeValueAsString(aopTestEntity);
    }


    @AfterThrowing(value = "insertPointcut()",throwing = "e")
    public void testAfterThrowing(Throwable e){
        log.error("捕获了异常:{}",e.getMessage());
    }

    @After(value = "insertPointcut()")
    public void afterWithAround(JoinPoint joinPoint){
        log.info("After和Around比较优先级:{}",joinPoint.getArgs());
    }


    /**
     * 匹配AOP对象的目标对象为指定类型的方法,即 SpringAopTestService 的aop代理对象的方法
     */
    @Pointcut(value = "this(com.fchan.layui.service.SpringAopTestService)")
    public void testThis(){}

    /*@Before(value = "testThis()")
    public void testThisBefore(){
        System.out.println("使用Before拦截了这个SpringAopTestService类里的所有方法");
    }*/

    /**
     * 匹配实现 SpringAopTestInterface 接口的目标对象(而不是aop代理后的对象)的方法,这里即SpringAopTestService的方法
     */
    @Pointcut(value = "target(com.fchan.layui.service.SpringAopTestInterface)")
    public void testTarget(){}


    @Before(value = "testTarget()")
    public void testTargetBefore(){
        System.out.println("使用Before拦截了实现这个 SpringAopTestInterface 接口的类里的所有方法");
    }

    /**
     * 匹配所有以service结尾的bean里头的方法
     *//*
    @Pointcut(value = "bean(*Service)")
    public void testBean(){}*/


    /**
     * 匹配任何以find开头而且只有一个Long参数的方法
     */
    @Pointcut("execution(* *..find*(Long))")
    public void testArgs(){}


    /**
     * 匹配任何只有一个Long参数的方法
     */
    @Pointcut("args(Long)")
    public void testArgOne(){}

    /**
     * 匹配任何以find开头的而且第一个参数为Long型的方法
     */
    @Pointcut("execution( * *..find*(Long, ..))")
    public void testArgsOne(){}


    /**
     * 匹配第一个参数为Long型的所有方法
     */
    @Pointcut("args(Long, ..)")
    public void testArgFirstOne(){}


    /**
     * 匹配路径在com.fchan.layui.service下的SpringAopTestService中只有一个Long型参数的所有方法
     */
    @Pointcut("args(Long) && within(com.fchan.layui.service.SpringAopTestService))")
    public void testArgAndPackage(){}

    /**
     * 匹配路径在com.fchan.layui.service下的SpringAopTestService中返回值为String的所有方法
     */
    @Pointcut("execution(String com.fchan.layui.service.SpringAopTestService.*(..)))")
    public void testArgAndPackageOnlyReturnString(){}

    /**
     * 匹配路径在com.fchan.layui.service下的SpringAopTestService中无参并且返回值为String的所有方法
     */
    @Pointcut("execution(String com.fchan.layui.service.SpringAopTestService.*()))")
    public void testArgOnlyReturnStringAndVoid(){}


    /**
     * 匹配路径在com.fchan.layui.service下的SpringAopTestService中方法只有一个形参并且类型为Long并且返回值为String的所有方法
     */
    @Pointcut("execution(String com.fchan.layui.service.SpringAopTestService.*(Long)))")
    public void testArgOnlyReturnStringAndLong(){}


}
