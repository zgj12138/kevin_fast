package io.kevin.modules.api.dao;

import io.kevin.modules.api.entity.TokenEntity;
import io.kevin.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZGJ
 * @date 2017/7/30 11:00
 **/
@Mapper
public interface TokenDao extends BaseDao<TokenEntity> {

    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
}
