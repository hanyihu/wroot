package com.vic.ck.console.sysmanagement.service;

import com.vic.base.BaseService;
import com.vic.ck.console.sysmanagement.mapper.IntegralMapper;
import com.vic.ck.entity.SysManagement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IntegralService extends BaseService {
    @Resource
    private IntegralMapper integralMapper;

    /**
     * 查询积分管理列表
     * @return
     */
    public List<SysManagement> list(){
        return integralMapper.list();
    }
    /**
     * 根据id查询积分管理信息
     * @param id
     * @return
     */
    public SysManagement findById(int id){
        return integralMapper.findById(id);
    }

    public int update(SysManagement management){
        return integralMapper.update(management);
    }
}
