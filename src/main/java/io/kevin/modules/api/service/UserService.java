package io.kevin.modules.api.service;

import io.kevin.modules.api.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * @author ZGJ
 * @date 2017/7/30 15:17
 **/
public interface UserService {

    UserEntity queryObject(Long userId);

    List<UserEntity> queryList(Map<String, Object> map);

    int queryTotal (Map<String, Object> map);

    void save(String mobile, String passowrd);

    void update(UserEntity user);

    void delete(Long userId);

    void deleteBatch(long[] userIds);

    UserEntity queryByMobile(String mobile);

    /**
     * 用户登录
     * @param mobile 手机号
     * @param password 密码
     * @return  返回用户ID
     */
    long login(String mobile, String password);
}
