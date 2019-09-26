package com.vic.ck.console.config.service;

import com.vic.ck.console.config.mapper.BasicConfigMapper;
import com.vic.ck.entity.BasicConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基础配置service层
 * @author houhaoran
 */
@Service
public class BasicConfigService {
    @Resource
    private BasicConfigMapper basicConfigMapper;

    /**
     * 查询基础配置列表
     * @return 所有基础配置数据list
     */
    public List<BasicConfig> list(){
        return basicConfigMapper.list();
    }
    /**
     * 根据id获取基础配置的详细信息
     * @param id 基础配置的id
     * @return 基础配置信息
     */
    public BasicConfig getBasicConfigById(Integer id){
        return basicConfigMapper.getBasicConfigById(id);
    }

    /**
     * 新增一条基础配置
     * @param basicConfig 基础配置对象
     * @return 1：成功  0：失败
     */
    public int insertBasicConfig(BasicConfig basicConfig){
        return basicConfigMapper.insertBasicConfig(basicConfig);
    }

    /**
     * 修改基础配置
     * @param basicConfig 基础配置对象
     * @return 1：成功  0：失败
     */
    public int updateBasicConfig(BasicConfig basicConfig){
        return basicConfigMapper.updateBasicConfig(basicConfig);
    }

    /**
     * 批量删除基础配置
     * @param ids 需要被删除的基础配置id
     * @return 1：成功  0：失败
     */
    public int deleteBasicConfig(int... ids){
        return basicConfigMapper.deleteBasicConfig(ids);
    }
}
