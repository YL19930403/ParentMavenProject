package cn.itcast.controller;

import cn.itcast.service.ISysLogService;
import itcast.domain.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;

@Component
@Aspect
public class LogAop {
    /**
     需要在web.xml中做如下配置
     <listener>
     <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
     </listener>
     */
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    /**
     * 开始时间
     */
    private Date visit_time;

    /**
     * 访问的类
     */
    private  Class clazz;

    /**
     * 访问的方法
     */
    private Method method;



    // 前置通知：获取开始时间、执行的是哪一个类、方法
    @Before("execution(* cn.itcast.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException{
        visit_time = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName();//获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数

        //获取具体执行的方法的Method对象
        if (null == args || args.length == 0){
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i=0; i<args.length;i++){
                classArgs[i] = args[i].getClass();
            }

            clazz.getMethod(methodName,classArgs);
        }
    }

    @After("execution(* cn.itcast.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception{
        long time = new Date().getTime() - visit_time.getTime(); //获取访问的时长

        String url = "";
        if (null != method && null != clazz || clazz != LogAop.class){
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if (null != classAnnotation){
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                if (null != annotation){
                    String[] methodValue = annotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取访问的ip
                    String ip = request.getRemoteAddr();
                    //获取当前操作的用户
                    SecurityContext securityContext = SecurityContextHolder.getContext();
//                    request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

                    User user = (User) securityContext.getAuthentication().getPrincipal();
                    String userName = user.getUsername();

                    //将日志相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecution_time(new Date(time));
                    sysLog.setIp(ip);
                    sysLog.setMethod(clazz.getName() + "/" + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUser_name(userName);
                    sysLog.setVisit_time(visit_time);

                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
