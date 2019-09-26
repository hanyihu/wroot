package com.vic.ck.console.config.mapper;

import com.vic.ck.entity.BasicConfig;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基础配置mapper层
 */
@MybatisMapper
public interface BasicConfigMapper {
    /**
     * 查询基础配置的列表
     * @return 所有基础配置信息
     */
    List<BasicConfig> list();

    /**
     * 根据id获取基础配置的详细信息
     * @param id 基础配置的id
     * @return 基础配置信息
     */
    BasicConfig getBasicConfigById(Integer id);

    /**
     * 新增基础配置
     * @param basicConfig 基础配置对象
     * @return 1：成功  0：失败
     */
    int insertBasicConfig(BasicConfig basicConfig);

    /**
     * 修改基础配置
     * @param basicConfig 基础配置对象
     * @return 1：成功  0：失败
     */
    int updateBasicConfig(BasicConfig basicConfig);

    /**
     * 批量删除基础配置
     * @param ids
     * @return
     */
    int deleteBasicConfig(@Param("ids") int[] ids);

}
