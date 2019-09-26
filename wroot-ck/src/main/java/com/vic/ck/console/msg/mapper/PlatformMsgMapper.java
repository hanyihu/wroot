package com.vic.ck.console.msg.mapper;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.PlatformMsg;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 系统的信息表
 * 
 * @author VIC
 */
@MybatisMapper
public interface PlatformMsgMapper extends BaseMapper<PlatformMsg> {

	void pushMsg(@Param("id")int id);
	
}
