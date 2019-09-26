package com.vic.ck.console.config.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.ThirdInterface;
import com.vic.wroot.common.annotation.MybatisMapper;

import java.util.List;

@MybatisMapper
public interface ThirdInterfaceMapper extends BaseMapper<ThirdInterface> {
    /**
     * 查询第三方接口列表
     * @param interface_type 第三方接口类型
     * @return
     */
    public List<ThirdInterface> list(String interface_type);
}
