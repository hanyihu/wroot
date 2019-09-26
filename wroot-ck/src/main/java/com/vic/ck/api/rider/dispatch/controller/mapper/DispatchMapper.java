package com.vic.ck.api.rider.dispatch.controller.mapper;

import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

@MybatisMapper
public interface DispatchMapper  {

	//查询骑手数量
  int  riderNum(@Param("lng")Double lng, @Param("lat")Double lat);


}
