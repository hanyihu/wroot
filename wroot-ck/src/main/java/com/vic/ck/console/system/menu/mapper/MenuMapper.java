package com.vic.ck.console.system.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.pager.Lookup;
import com.vic.ck.console.entity.Button;
import com.vic.ck.console.entity.Menu;
import com.vic.ck.console.system.role.po.AuthorityTree;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface MenuMapper {

	List<Menu> getUserMenus(@Param("userId")int userId);

	List<Button> getUserBtn(@Param("userId")int userId);

	List<Menu> list(Lookup lookup);

	boolean checkName(@Param("id")Integer id, @Param("name")String name, @Param("parentId")Integer parentId);

	Menu getMeunById(@Param("id")int id);
	
	//获得当前菜单全部按钮的选择状态 id  name checked
	List<Button> findMenuBtn(@Param("menuId") int menuId);

	void add(Menu entity);

	void update(Menu entity);
	
	int maxId(@Param("pId") int id);

	void delete(@Param("ids")int[] ids);

	/**新增菜单按钮关系*/
	void addMenuBtn(@Param("mid")int id, @Param("bids")List<Integer> bids);
	
	/**删除菜单按钮关系*/
	void deleteMenuBtn(@Param("ids")List<Integer> id);
	
	/**构建一个角色的权限菜单按钮的简单对象*/
	List<AuthorityTree>  authorityTree(@Param("roleId") int roleId);

	List<Integer> getRoleMenuIds(@Param("roleId")int roleId);

	List<Integer> getRoleMenuBtnIds(@Param("roleId")int roleId);

	List<Integer> getMenuBtn(@Param("mid")int mid);

	void deleteMenuBtnByMenuId(@Param("mid")int id);

	
}
