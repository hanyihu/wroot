package com.vic.ck.console.config.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.BasicConfig;
import com.vic.wroot.common.annotation.MybatisMapper;

import java.util.List;
@MybatisMapper
public interface FetchMapper extends BaseMapper<BasicConfig> {
    List<BasicConfig> list();
}
