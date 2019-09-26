package com.vic.ck.console.entity;
/**
 * 按钮
 * @author VIC
 *
 */
public class Button {

	/**
	 * 按钮id
	 * */
	private int id;


	/**
	 * 按钮code
	 */
	private String code;
	
	/**
	 * 按钮名称
	 */
	private String name;
	
	private boolean checked;//是否选中
	
	/**
	 * 所属菜单
	 */
	private String menuName;
	
	/**
	 *所属菜单的url 
	 */
	private String menuUrl;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	

}
