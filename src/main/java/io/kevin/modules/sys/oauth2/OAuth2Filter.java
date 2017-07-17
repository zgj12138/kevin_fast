package io.kevin.modules.sys.oauth2;

import com.google.gson.Gson;
import io.kevin.common.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * oauth2 过滤器
 * @author ZGJ
 * @date 2017/7/16 11:23
 **/
public class OAuth2Filter extends AuthenticatingFilter {
    private static final Logger logger = LoggerFactory.getLogger(OAuth2Filter.class);
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) servletRequest);

        if(StringUtils.isBlank(token)) {
            return null;
        }
        return new OAuth2Token(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求token,如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if(StringUtils.isBlank(token)) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletRequest;
            String json = new Gson().toJson(Result.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));
            httpServletResponse.getWriter().print(json);

            return false;
        }
        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Throwable throwable = e.getCause() == null ? e : e.getCause();
        Result result = Result.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());

        String json = new Gson().toJson(result);
        try {
            httpServletResponse.getWriter().print(json);
        } catch (IOException e1) {
            logger.error("网络出错，请检查原因");
        }
        return false;
    }

    /**
     * 获取请求的token
     * @param httpServletRequest
     * @return
     */
    private String getRequestToken(HttpServletRequest httpServletRequest) {
        //从header中获取token
        String token = httpServletRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)) {
            token = httpServletRequest.getParameter("token");
        }
        return token;
    }
}
