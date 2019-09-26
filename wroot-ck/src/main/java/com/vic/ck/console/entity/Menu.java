package com.vic.ck.console.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * 菜单对象
 * 对应表sys_menu
 * @author VIC
 *
 */
public class Menu extends BaseEntity implements Comparable<Menu>{

	private String name;
	
	
	private String url;
	
	private String label;
	
	private Integer parentId;
	
	private String parentName;

	private int sort;
	
	private String btns;// 按钮 按钮1，按钮2，...
	
	private List<Button> btnList;

	/**父级菜单*/
	private Menu parent;
	
	/**子菜单*/
	private List<Menu> children;	
	
	
	public Menu(){}
	
	public Menu(Integer parentId, String parentName) {
		this.parentId = parentId;
		this.parentName = parentName;
	}

	@Override
	public int compareTo(Menu o) {
		if(o.getParentId()== this.parentId){//如果两个同属于一级则判断 sort
			return this.sort - o.sort;
		}
		return this.getId()-o.getId() ;
		
	}
	
	/**当前菜单是根节点吗*/
	public boolean isRoot(){
		return this.getParentId() == 0;
	}
	
	/**添加子菜单*/
	public void  addChildren(Menu menu){
		if(this.children == null) {
			this.children = new ArrayList<Menu>();
		}
		this.children.add(menu);
		menu.setParent(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getParentId() {
		return parentId == null ? 0 : parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}




	public Menu getParent() {
		return parent;
	}




	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getBtns() {
		return btns;
	}

	public void setBtns(String btns) {
		this.btns = btns;
	}

	public List<Button> getBtnList() {
		return btnList;
	}

	public void setBtnList(List<Button> btnList) {
		this.btnList = btnList;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


}