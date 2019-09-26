package com.vic.ck.console.platform.mapper;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.PlatformAppVersion;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * app版本管理

 * 
 * @author VIC
 */
@MybatisMapper
public interface PlatformAppVersionMapper extends BaseMapper<PlatformAppVersion> {
	/**当前最新版本*/
	PlatformAppVersion currentVersion(@Param("appType")int appType, @Param("machineType")int machineType);
	
}
