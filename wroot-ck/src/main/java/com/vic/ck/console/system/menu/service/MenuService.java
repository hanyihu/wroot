package com.vic.ck.console.system.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.entity.Button;
import com.vic.ck.console.entity.Menu;
import com.vic.ck.console.system.menu.mapper.MenuMapper;
import com.vic.ck.console.system.role.po.AuthorityTree;
import com.vic.wroot.common.po.AuthorityData;
import com.vic.wroot.common.util.CommonUtils;

@Service
public class MenuService {

	@Resource
	private MenuMapper menuMapper;

	/**
	 * 获得用户菜单树列表
	 * 2016年10月10日 by VIC
	 * @return
	 */
	public List<Menu> getUserMenus(int userId){
		return menuMapper.getUserMenus(userId);
	}
	
	/**
	 * 构建树形结构的菜单树
	 * 2016年10月10日 by VIC
	 * @return
	 */
	public List<Menu> getAuthorityMenu(int userId){
		List<Menu> list = getUserMenus(userId);
		return  buildMenuTree(list);
	}
	
	/**
	 * 构建菜单树方法
	 * @param list
	 * @return
	 */
	private List<Menu> buildMenuTree(List<Menu> list){
		Map<Integer, Menu> menuMap = new LinkedHashMap <Integer, Menu>();
		List<Menu> menuTree = new ArrayList<Menu>(); 
		for(Menu m : list) {
			menuMap.put(m.getId(), m);
		}
		for(Map.Entry<Integer, Menu> entry : menuMap.entrySet()) {
			Menu menu = entry.getValue();
			if(menu.isRoot()) {//根节点
				menuTree.add(menu);
			}else {//子节点
				Menu parent = menuMap.get(menu.getParentId());
				if(parent != null) {
					parent.addChildren(menu);
				}
			}
		}
		return  menuTree;
		
	}
	/**
	 *获得用户的菜单下的按钮列表 
	 * @return
	 */
	public List<Button> getUserBtn(int userId){
		return menuMapper.getUserBtn(userId);
	}
	
	/**
	 * 获得用户的菜单下的按钮Map URL--> Map<code,name>
	 * 2016年11月8日 by VIC
	 * @return
	 */
	public Map<String, Map<String, String>> getAuthorityBtn(int userId){
		List<Button> btns = getUserBtn(userId);
		Map<String, Map<String, String>> menuBtnMap = new LinkedHashMap<String, Map<String, String>>();
		for(Button btn :  btns) {
			String code = btn.getCode();
			if(StringUtils.isBlank(code)){
				continue;
			}
			String url = btn.getMenuUrl();
			Map<String, String> btnMap =  menuBtnMap.get(url);
			if(btnMap == null){
				btnMap = new HashMap<String, String>();
				menuBtnMap.put(url, btnMap);
			}
			
			btnMap.put(code, btn.getName());
		}
		return menuBtnMap;
	}

	//查询菜单列表（全部）
	public PageInfo<Menu> list(Lookup lookup) {
		List<Menu> datas = menuMapper.list(lookup);
		return PageInfo.instance(null, datas, lookup);
	}

	//检测菜单的名称是否重复
	public boolean checkName(Integer id, String name, Integer parentId) {
		return menuMapper.checkName(id, name, parentId);
	}

	//根据id获取菜单信息
	public Menu getMeunById(int id) {
		Menu menu = menuMapper.getMeunById(id);
		return menu;
	}
	//获得当前菜单全部按钮
	public List<Button> findMenuBtn(int menuId){
		return menuMapper.findMenuBtn(menuId);
	}

	//新增菜单 并维护菜单按钮关系
	@Transactional
	public void add(Menu entity) {
		int pid = entity.getParentId();
		int id = getId(pid);
		entity.setId(id);
		menuMapper.add(entity);
		
		String btns = entity.getBtns();
		if(pid > 0 && StringUtils.isNotBlank(btns) && btns.matches("(\\d+|\\d+,|\\d+,\\d+)+") ){//维护菜单按钮
			menuMapper.addMenuBtn(id,CommonUtils.toIntList(btns.split(",")));
		}
	}

	//修改菜单 并维护菜单按钮关系
	@Transactional
	public void update(Menu entity) {
		menuMapper.update(entity);
		String btns = entity.getBtns();
		if(StringUtils.isNotBlank(btns) && btns.matches("(\\d+|\\d+,|\\d+,\\d+)+")) {//
			AuthorityData authorityData = AuthorityData.instance(getMenuBtn(entity.getId()), CommonUtils.toIntList(btns.split(",")));
			//新建菜单和按钮关系
			if(!authorityData.getNeedAdd().isEmpty()){
				menuMapper.addMenuBtn(entity.getId(), authorityData.getNeedAdd());
			}
			//删除菜单和按钮关系
			if(!authorityData.getNeedDelete().isEmpty()){
				menuMapper.deleteMenuBtn(authorityData.getNeedDelete());
			}
		}else {//删除现有菜单的全部按钮
			menuMapper.deleteMenuBtnByMenuId(entity.getId());
		}
	}
	
	/**获得菜单的按钮关系id*/
	public List<Integer> getMenuBtn(int menuId){
		return menuMapper.getMenuBtn(menuId);
	}
	
	public int getId(int pId) {
		int id = menuMapper.maxId(pId);
		if(id==0) {
			id = Integer.parseInt(pId +"01");
		}else {
			id += 1;
		}
		return id;
	}

	public void delete(int... ids) {
		menuMapper.delete(ids);
	}
	
	
	public List<AuthorityTree> authorityTree(int roleId){
		return menuMapper.authorityTree(roleId);
	}
	
	

}
