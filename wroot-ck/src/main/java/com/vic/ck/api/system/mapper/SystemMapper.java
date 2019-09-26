package com.vic.ck.api.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.model.BaseModel;
import com.vic.ck.entity.Area;
import com.vic.ck.entity.Banner;
import com.vic.ck.entity.BootPage;
import com.vic.ck.entity.SysConfig;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface SystemMapper {

	List<BaseModel> banks();

	List<BootPage> bootPages();

	List<Banner> banners(@Param("type")int type, @Param("cityId")int cityId);

	Area matchCity(@Param("id")int id, @Param("name")String name);

	List<Area> hotCities();
	
	List<Area> allCities();

	List<Area> openedCities();

	List<Area> selectCities(@Param("levelType")int levelType, @Param("parentId")int parentId);
	
	List<Area> selectAllCities();
	
	SysConfig getSysConfig(@Param("code")String code);

	/**全部的CONFIG 富文本的值不查询*/
	List<SysConfig> sysConfigs();
	
	void updateSysConfig(SysConfig entity);

	List<Area> selectCity(@Param("parentId")int parentId);

}
