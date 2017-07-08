package io.kevin.modules.sys.dao;


import io.kevin.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 * @author ZGJ
 * @date 2017/7/3 23:55
*/
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
