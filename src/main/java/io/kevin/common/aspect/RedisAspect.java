package io.kevin.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Redis切面处理类
 * @author ZGJ
 * @date 2017/8/5 10:55
 **/
@Aspect
@Configuration
public class RedisAspect {
    private static Logger logger = LoggerFactory.getLogger(RedisAspect.class);
    /**
     * 是否开启redis缓存，true开启，false关闭
     */
    @Value("${spring.redis.open: false}")
    private boolean open;

    @Around("execution(* io.kevin.common.utils.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        if(open) {
            try {
                result = joinPoint.proceed();
            } catch (Exception e) {
                logger.error("Redis服务异常", e);
            }
        }
        return result;
    }
}
