package com.vic.ck.console.sysmanagement.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.SysManagement;
import com.vic.wroot.common.annotation.MybatisMapper;

import java.util.List;

@MybatisMapper
public interface IntegralMapper extends BaseMapper<SysManagement> {
    List<SysManagement> list();
}
