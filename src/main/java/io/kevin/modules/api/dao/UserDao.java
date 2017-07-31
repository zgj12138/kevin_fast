package io.kevin.modules.api.dao;

import io.kevin.modules.api.entity.UserEntity;
import io.kevin.modules.sys.dao.BaseDao;

/**
 * @author ZGJ
 * @date 2017/7/30 14:48
 **/
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}
