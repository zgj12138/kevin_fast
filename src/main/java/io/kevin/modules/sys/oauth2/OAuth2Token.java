package io.kevin.modules.sys.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * oauth2 token
 * @author ZGJ
 * @date 2017/7/16 11:35
 **/
public class OAuth2Token implements AuthenticationToken{

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
