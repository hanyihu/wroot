package com.vic.ck.console.system.button.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.pager.Lookup;
import com.vic.ck.console.entity.Button;
import com.vic.wroot.common.annotation.MybatisMapper;
@MybatisMapper
public interface ButtonMapper {
	
	public List<Button> ListButton(Lookup lookUp);	

	public boolean checkName(@Param("id")Integer id, @Param("name")String name);
	
	public Button getButtonid(@Param("id")int id);

	public void addButton(Button entity);
	
	public void updateButton(Button entity);

	public void deleteButton(@Param("ids")int[] ids);
	
	public int checkID(@Param("ids")int[] ids);
}
