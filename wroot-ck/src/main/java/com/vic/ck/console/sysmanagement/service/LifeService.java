package com.vic.ck.console.sysmanagement.service;

import com.vic.base.BaseService;
import com.vic.ck.console.sysmanagement.mapper.LifeMapper;
import com.vic.ck.entity.SysManagement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LifeService extends BaseService {
    @Resource
    private LifeMapper lifeMapper;

    public List<SysManagement> list(){
        return lifeMapper.list();
    }

    /**
     * 根据id查询生活服务管理信息
     * @param id
     * @return
     */
    public SysManagement findById(int id){
        return lifeMapper.findById(id);
    }

    public int update(SysManagement management){
        return lifeMapper.update(management);
    }
}
