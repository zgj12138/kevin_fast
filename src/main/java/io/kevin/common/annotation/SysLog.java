package io.kevin.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @author ZGJ
 * @date 2017/7/6 0:10
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
