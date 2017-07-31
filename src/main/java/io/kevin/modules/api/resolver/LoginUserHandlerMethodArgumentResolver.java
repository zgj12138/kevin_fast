package io.kevin.modules.api.resolver;

import io.kevin.modules.api.annotation.LoginUser;
import io.kevin.modules.api.entity.UserEntity;
import io.kevin.modules.api.interceptor.AuthorizationInterceptor;
import io.kevin.modules.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@loginUser注解的方法参数，注入当前登录用户
 * @author ZGJ
 * @date 2017/7/31 23:40
 **/
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserService userService;


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(UserEntity.class)
                && methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        //获取用户ID
        Object object = nativeWebRequest.getAttribute(AuthorizationInterceptor.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if(object == null) {
            return null;
        }

        //获取用户信息
        UserEntity user = userService.queryObject((Long) object);
        return user;
    }
}
