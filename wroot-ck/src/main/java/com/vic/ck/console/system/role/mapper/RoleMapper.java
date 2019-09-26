package com.vic.ck.console.system.role.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.pager.Lookup;
import com.vic.ck.console.entity.Role;
import com.vic.wroot.common.annotation.MybatisMapper;


@MybatisMapper
public interface RoleMapper {

	List<Role> listRole(Lookup lookup);
	
	boolean checkName(@Param("id")Integer id, @Param("name")String name);
	
	int checkRole(@Param("ids")int[] ids);

	Role getRole(@Param("id")int id);

	void add(Role entity);
	
	void update(Role entity);

	void delete(@Param("ids")int[] ids);

	//新建角色和菜单关系
	void addRoleMenu(@Param("roleId")int roleId, @Param("ids")List<Integer> needAdd);

	//删除角色菜单关系
	void deleteRoleMenu(@Param("roleId")int roleId, @Param("ids")List<Integer> needDelete);

	//新建角色和菜单按钮关系
	void addRoleMenuBtn(@Param("roleId")int roleId, @Param("ids")List<Integer> needAdd);

	//删除角色菜单按钮关系
	void deleteRoleMenuBtn(@Param("roleId")int roleId,  @Param("ids")List<Integer> needDelete);

	List<Integer> getUserRoles(@Param("userId")int userId);

	void addUserRole(@Param("userId")int userId,  @Param("ids")List<Integer> needAdd);

	void deleteUserRole(@Param("userId")int userId,  @Param("ids")List<Integer> needDelete);

	List<Role> findUserRole(@Param("userId")int userId);

	void deleteRoleMenuByRoleId(@Param("roleId")int roleId);

	void deleteRoleMenuBtnByRoleId(@Param("roleId")int roleId);
}
