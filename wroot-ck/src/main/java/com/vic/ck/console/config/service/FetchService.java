package com.vic.ck.console.config.service;

import com.vic.base.BaseService;
import com.vic.ck.console.config.mapper.FetchMapper;
import com.vic.ck.entity.BasicConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 提现管理service
 */
@Service
public class FetchService extends BaseService {
    @Resource
    private FetchMapper fetchMapper;

    /**
     * 查询提现管理列表
     * @return
     */
    public List<BasicConfig> list(){
        return fetchMapper.list();
    }

    /**
     * 根据ID查询提现管理数据
     * @param id
     * @return
     */
    public BasicConfig getFetchById(int id){
        return fetchMapper.findById(id);
    }

    /**
     * 修改提现管理数据
     * @param config
     * @return
     */
    public int update(BasicConfig config){
        return fetchMapper.update(config);
    }
}
