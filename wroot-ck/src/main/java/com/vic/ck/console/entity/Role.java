package com.vic.ck.console.entity;

public class Role extends BaseEntity{

	private String name;
	
	private boolean checked;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
