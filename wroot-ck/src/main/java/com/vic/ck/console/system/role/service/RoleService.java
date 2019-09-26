package com.vic.ck.console.system.role.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.entity.Role;
import com.vic.ck.console.system.menu.mapper.MenuMapper;
import com.vic.ck.console.system.role.mapper.RoleMapper;
import com.vic.wroot.common.po.AuthorityData;
import com.vic.wroot.common.util.CommonUtils;
/**
 * 角色管理的service
 * @author Administrator
 *
 */
@Service
public class RoleService extends BaseService{

	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private MenuMapper menuMapper;
	/**
	 * 
	 * @param lookup
	 * @return
	 */
	public PageInfo<Role> list(Lookup lookup){
		Page<Role> page =  startPage(lookup);
		List<Role> list = roleMapper.listRole(lookup);
		return PageInfo.instance(page, list, lookup);
	}
	
	public boolean checkName(Integer id, String name) {
		
		return roleMapper.checkName(id,name);
	}

	public int checkRole(int... ids) {
		return roleMapper.checkRole(ids);
	}

	public Role getRole(int id) {
		
		return roleMapper.getRole(id);
	}


	public void add(Role entity) {
		roleMapper.add(entity);
	}
	
	public void update(Role entity) {
		roleMapper.update(entity);
		
	}


	public void delete(int... ids) {
		roleMapper.delete(ids);
		
	}

	/**
	 * 保存角色的权限
	 * @param id 角色id
	 * @param menuIds 菜单id,id
	 * @param btnIds菜单按钮关系表id,id
	 */
	@Transactional
	public void saveAuthority(int roleId, String menuIds, String menuBtnIds) {
		//存在的就不在建立  不存在的就建立 另外的则删除
		
		if(StringUtils.isNotBlank(menuIds) && menuIds.matches("(\\d|\\d+,|\\d+,\\d+)+")) {//构建 角色和菜单的关系 
			AuthorityData menuAuthorityData = AuthorityData.instance(getRoleMenuIds(roleId), CommonUtils.toIntList(menuIds.split(",")));
			//新建角色和菜单关系
			if(!menuAuthorityData.getNeedAdd().isEmpty()){
				roleMapper.addRoleMenu(roleId, menuAuthorityData.getNeedAdd());
			}
			
			//删除角色菜单关系
			if(!menuAuthorityData.getNeedDelete().isEmpty()){
				roleMapper.deleteRoleMenu(roleId, menuAuthorityData.getNeedDelete());
			}
		}else{//删除角色和菜单的关系 
			roleMapper.deleteRoleMenuByRoleId(roleId);
		}
		
		if(StringUtils.isNotBlank(menuBtnIds) && menuBtnIds.matches("(\\d|\\d+,|\\d+,\\d+)+")) {
			AuthorityData btnAuthorityData = AuthorityData.instance(getRoleMenuBtnIds(roleId), CommonUtils.toIntList(menuBtnIds.split(",")));
			//新建角色和菜单按钮关系
			if(!btnAuthorityData.getNeedAdd().isEmpty()){
				roleMapper.addRoleMenuBtn(roleId, btnAuthorityData.getNeedAdd());
			}
			//删除角色菜单按钮关系
			if(!btnAuthorityData.getNeedDelete().isEmpty()){
				roleMapper.deleteRoleMenuBtn(roleId,btnAuthorityData.getNeedDelete());
			}
		}else {
			roleMapper.deleteRoleMenuBtnByRoleId(roleId);
		}
	}
	
	
	/**获得当前角色的角色菜单id*/
	public List<Integer> getRoleMenuIds(int roleId) {
		return menuMapper.getRoleMenuIds(roleId);
	}
	
	/**获得当前角色的角色菜单按钮id*/
	public List<Integer> getRoleMenuBtnIds(int roleId) {
		return menuMapper.getRoleMenuBtnIds(roleId);
	}

	public List<Role> findUserRole(int userId) {
		return roleMapper.findUserRole(userId);
	}

	/**  修改用户的角色*/
	@Transactional
	public void updateUserRole(int userId, int[] roles) {
		Integer[] roleIds = ArrayUtils.toObject(roles);
		AuthorityData data = AuthorityData.instance(getUserRoles(userId), Arrays.asList(roleIds));
		//新建用户和角色按钮关系
		if(!data.getNeedAdd().isEmpty()){
			roleMapper.addUserRole(userId, data.getNeedAdd());
		}
		//删除用户和角色按钮关系
		if(!data.getNeedDelete().isEmpty()){
			roleMapper.deleteUserRole(userId,data.getNeedDelete());
		}
	}
	
	/**获得用户的角色*/
	public List<Integer> getUserRoles(int userId){
		return roleMapper.getUserRoles(userId);
	}
	
}
