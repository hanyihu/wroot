package com.vic.ck.console.system.area.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.base.model.BaseModel;
import com.vic.ck.entity.SysArea;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 区域
 * 
 * @author VIC
 */
@MybatisMapper
public interface SysAreaMapper extends BaseMapper<SysArea> {

	List<BaseModel> provinces();

	/**全部开发的城市*/
	List<BaseModel> opendCityList();

	/***
	 * 获得子区域
	 */
	List<BaseModel> findChildren(@Param("parentId")int parentId);
	
}
