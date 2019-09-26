package com.vic.ck.console.system.role.po;
/**
 * 构建权限树的简单对象
 * @author VIC
 *
 */
public class AuthorityTree {
	
	private String id;//m-id 或者b-id
	
	private String name;
	
	private String parentId;
	
	private boolean checked;
	
	private int role;///1菜单 2按钮 
	
	private String iconSkin;//自定义图标
	
	private Integer relId;//角色菜单关系 或者 角色菜单按钮关系

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getIconSkin() {
		if(this.id.startsWith("b")){
			return "icon01";
		}else if(this.id.startsWith("m")){
			return "pIcon01";
		}
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	

	
}
