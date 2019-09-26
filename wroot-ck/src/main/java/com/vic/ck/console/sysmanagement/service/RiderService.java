package com.vic.ck.console.sysmanagement.service;

import com.vic.base.BaseService;
import com.vic.ck.console.sysmanagement.mapper.RiderMapper;
import com.vic.ck.entity.SysManagement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RiderService extends BaseService {
    @Resource
    private RiderMapper riderMapper;

    /**
     * 查询骑手端管理列表
     * @return
     */
    public List<SysManagement> list(){
        return riderMapper.list();
    }

    /**
     * 根据id查询骑手端管理信息
     * @param id
     * @return
     */
    public SysManagement findById(int id){
        return riderMapper.findById(id);
    }

    public int update(SysManagement management){
        return riderMapper.update(management);
    }
}
