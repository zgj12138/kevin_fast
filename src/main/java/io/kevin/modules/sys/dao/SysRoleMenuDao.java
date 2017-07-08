package io.kevin.modules.sys.dao;


import io.kevin.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色与菜单的对应关系
 * @author ZGJ
 * @date 2017/7/3 23:56
*/
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
