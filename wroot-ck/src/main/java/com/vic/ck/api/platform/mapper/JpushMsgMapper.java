package com.vic.ck.api.platform.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.JpushMsg;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 推送消息表
 * 
 * @author VIC
 */
@MybatisMapper
public interface JpushMsgMapper extends BaseMapper<JpushMsg> {

	int insertCity(JpushMsg entity);

	int insertAll(JpushMsg entity);
	
}
