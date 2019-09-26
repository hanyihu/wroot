package com.vic.ck.console.config.service;

import com.vic.base.BaseService;
import com.vic.ck.console.config.mapper.CommissionMapper;
import com.vic.ck.entity.BasicConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommissionService extends BaseService {
    @Resource
    private CommissionMapper commissionMapper;

    /**
     * 查询分销管理列表
     * @return
     */
    public List<BasicConfig> list(){
        return commissionMapper.list();
    }

    /**
     * 根据ID查询分销管理数据
     * @param id
     * @return
     */
    public BasicConfig getCommissionById(int id){
        return commissionMapper.findById(id);
    }

    /**
     * 修改分销管理数据
     * @param config
     * @return
     */
    public int update(BasicConfig config){
        return commissionMapper.update(config);
    }
}
