package io.kevin.modules.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户token
 * @author ZGJ
 * @date 2017/7/30 11:01
 **/
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Date expireTIme;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTIme() {
        return expireTIme;
    }

    public void setExpireTIme(Date expireTIme) {
        this.expireTIme = expireTIme;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
