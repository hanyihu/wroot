package com.vic.ck.console.customer.mapper;

import com.vic.base.BaseMapper;
import com.vic.base.pager.Lookup;
import com.vic.ck.entity.Customer;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表
 * 
 * @author VIC
 */
@MybatisMapper
public interface CustomerMapper extends BaseMapper<Customer> {

    /*根据经纬度查询用户信息*/
    List<Customer> findByLat(@Param("lng") String lng, @Param("lat") String lat);

    List<Customer> findByLats(@Param("lng") String lng, @Param("lat") String lat, Lookup lookup);
}
