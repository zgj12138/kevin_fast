package io.kevin.common.aspect;

import com.google.gson.Gson;
import io.kevin.common.annotation.SysLog;
import io.kevin.common.utils.HttpContextUtils;
import io.kevin.common.utils.IPUtils;
import io.kevin.common.utils.ShiroUtils;
import io.kevin.modules.sys.entity.SysLogEntity;
import io.kevin.modules.sys.entity.SysUserEntity;
import io.kevin.modules.sys.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 * @author ZGJ
 * @date 2017/7/6 0:13
 **/
@Aspect
@Component
public class SysLogAspect {
    private static Logger logger = LoggerFactory.getLogger(SysLogAspect.class);
    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(io.kevin.common.annotation.SysLog)")
    public void logPointCut() {

    }
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.proceed();
        //执行时长，毫秒
        long time = System.currentTimeMillis() - beginTime;
        
        //保存日志
        saveSysLog(joinPoint, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogEntity sysLogEntity = new SysLogEntity();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if(sysLog != null) {
            //注解上的描述
            sysLogEntity.setOperation(sysLog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogEntity.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = new Gson().toJson(args);
            sysLogEntity.setParams(params);
        } catch (Exception e) {
            logger.error("请求参数解析错误", e);
        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLogEntity.setIp(IPUtils.getIpAddr(request));

        //用户名
        String username = null;
        //如果是登录，就从参数中取
        if(className.equals("io.kevin.modules.sys.controller.SysLoginController") && methodName.equals("login")) {
            username = request.getParameter("username");
        } else {
            username = ShiroUtils.getUserEntity().getUsername();
        }
        sysLogEntity.setUsername(username);

        sysLogEntity.setCreateDate(new Date());
        sysLogService.save(sysLogEntity);
    }
}
