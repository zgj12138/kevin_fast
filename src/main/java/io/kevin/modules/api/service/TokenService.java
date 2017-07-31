package io.kevin.modules.api.service;

import io.kevin.modules.api.entity.TokenEntity;

import java.util.Map;

/**
 * @author ZGJ
 * @date 2017/7/30 15:16
 **/
public interface TokenService {

    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);

    void save(TokenEntity token);

    void update(TokenEntity token);

    /**
     * 生成token
     * @param userId 用户ID
     * @return 返回token相关的信息
     */
    Map<String, Object> createToken(Long userId);
}
