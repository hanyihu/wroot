package com.vic.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.vic.ck.console.entity.Menu;
import com.vic.ck.console.entity.User;
import com.vic.ck.vo.ConsoleBellTip.TipFlag;
import com.vic.ck.vo.ConsoleBellTip.UrlInfo;
import com.vic.wroot.common.util.CommonUtils;


/**
 * 存放用户信息的对象
 *  可能包括用户基本信息  登录信息 菜单信息 按钮信息等
 * @author VIC
 *
 */
public class Principal {

	/**
	 * userInfo
	 */
	private User user;
	
	/**
	 * menu tree
	 */
	private List<Menu> menus;
	
	/**
	 * menu Map--> 但是包含父子结构 URL-->menu
	 */
	private Map<String, Menu> menuIndex = new HashMap<String, Menu>();
	
	/**当前位置用于页面展示*/
	private Set<Menu> position = new TreeSet<Menu>();
	
	/**
	 * current URL
	 */
	private String location;
	
	/**当前用户所拥有的按钮 URL-->Map<code, name>*/
	private Map<String, Map<String, String>> btns = new HashMap<String, Map<String, String>>();
	
	/**一些数量提醒*/
	private List<UrlInfo> tips = new ArrayList<UrlInfo>();
	
	/**
	 * 2016年11月8日 by VIC
	 * @return
	 */
	public String getBtnJson(){
		return CommonUtils.toJson(getBtns());
	}
	
	public Principal(){}
	
	public Principal(User user) {
		this.user = user;
	}
	
	public int getUserId(){
		return user != null ? user.getId() : 0;
	}
	
	public String getNickname(){
		return user != null ? user.getNickname() : "";
	}
	public String getUsername(){
		return user != null ? user.getUsername() : "";
	}
	
	public Integer getCityId(){
		return user != null ? user.getCityId() : null;
	}
	
	public Principal(User user, List<Menu> menus) {
		this.user = user;
		this.menus = menus;
		locationIndex(menus);
	}

	/**把树形菜单还原成map*/
	private void locationIndex(List<Menu> menus) {
		if(menus == null) {
			return;
		}
		for(Menu m :menus) {
			menuIndex.put(m.getUrl(), m);
			List<Menu> subMenus = m.getChildren();
			if(subMenus !=null) {
				locationIndex(subMenus);
			}
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
		locationIndex(menus);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Map<String, Map<String, String>> getBtns() {
		return btns;
	}

	public void setBtns(Map<String, Map<String, String>> btns) {
		this.btns = btns;
	}

	public Map<String, Menu> getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(Map<String, Menu> menuIndex) {
		this.menuIndex = menuIndex;
	}

	public Set<Menu> getPosition() {
		return position;
	}

	public void setPosition(Set<Menu> position) {
		this.position = position;
	}
	
	public void setPosition(String url) {
		Menu menu = menuIndex.get(url);
		if(menu != null) {
			position.clear();
			addMenuToPosition(menu);
		}
	}
	
	private void addMenuToPosition(Menu menu){
		position.add(menu);
		if(menu.getParent() != null) {
			addMenuToPosition(menu.getParent());
		}
	}

	public List<UrlInfo> getTips() {
		return tips;
	}

	public void setTips(List<UrlInfo> tips) {
		this.tips = tips;
	}
	
	/**
	 * 删除某个标识的提醒
	 * @param flag
	 */
	public void removeTip(TipFlag flag){
		if(this.getTips() != null) {
			for(UrlInfo info : this.getTips()) {
				if(info.getFlag() == flag) {
					this.getTips().remove(info);
					break;
				}
			}
		}
	}

	

}
