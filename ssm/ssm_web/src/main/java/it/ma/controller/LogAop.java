package it.ma.controller;

import it.ma.domain.SysLog;
import it.ma.domain.Users;
import it.ma.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    private Date startTime;
    private  Class executionClass;
    private  Method executionMethod;
     @Before("execution(* it.ma.controller.*.*(..))")
    public  void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
              startTime=new Date();
           executionClass = joinPoint.getTarget().getClass();
           String methodName = joinPoint.getSignature().getName();
           Object[] args = joinPoint.getArgs();
            if(args==null||args.length==0){
                executionMethod= executionClass.getMethod(methodName);
            }
            else {
                Class[] classes=new Class[args.length];
                for (int i=0;i<args.length;i++){
                     classes[i]=args[i].getClass();
                }
                executionMethod=  executionClass.getMethod(methodName,classes);
            }
     }
     @After("execution(* it.ma.controller.*.*(..))")
     public  void  doAfter(JoinPoint joinPoint) throws Exception {
            long time=new Date().getTime()-startTime.getTime();
            String url="";
            if(executionClass!=null&&executionMethod!=null&&executionClass!=LogAop.class){
                RequestMapping requestMappingAnnotation =(RequestMapping) executionClass.getAnnotation(RequestMapping
                        .class);
                  if(requestMappingAnnotation!=null){
                      String[] classValue = requestMappingAnnotation.value();
                      RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                      if (methodAnnotation!=null){
                          String[] methodValue = methodAnnotation.value();
                          url=classValue[0]+methodValue[0];
                          String ip = request.getRemoteAddr();
                          SecurityContext context = SecurityContextHolder.getContext();
                          User user =(User) context.getAuthentication().getPrincipal();
                          String username = user.getUsername();
                          SysLog sysLog=new SysLog();
                              sysLog.setExecutionTime(time);
                              sysLog.setIp(ip);
                              sysLog.setMethod("[className]"+executionClass.getName()+"[methodName] "+executionMethod.getName());
                                 sysLog.setUrl(url);
                                 sysLog.setUsername(username);
                                 sysLog.setVisitTime(startTime);
                          System.out.println
                                  ("-------------------------------------------------------------------------------------");
                          System.out.println(time);
                          System.out.println(ip);
                          System.out.println("[className]"+executionClass.getName()+"[methodName] "+executionMethod.getName());
                          System.out.println(url);
                          System.out.println(username);
                          System.out.println(startTime);
                          System.out.println
                                  ("-------------------------------------------------------------------------------------");
                         sysLogService.save(sysLog);
                      }
                  }
            }
     }
}

















