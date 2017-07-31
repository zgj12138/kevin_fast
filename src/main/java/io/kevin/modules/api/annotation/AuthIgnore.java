package io.kevin.modules.api.annotation;

import java.lang.annotation.*;

/**
 * API接口，忽略token验证
 * @author ZGJ
 * @date 2017/7/30 10:54
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {
}
