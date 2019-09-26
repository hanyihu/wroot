package com.vic.ck.console.config.service;

import com.vic.base.BaseService;
import com.vic.ck.console.config.mapper.ThirdInterfaceMapper;
import com.vic.ck.entity.ThirdInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThirdInterfaceService extends BaseService {
    @Resource
    private ThirdInterfaceMapper thirdInterfaceMapper;

    /**
     * 查询第三方接口列表
     * @param interface_type 第三方接口类型
     * @return
     */
    public List<ThirdInterface> list(String interface_type){
        return thirdInterfaceMapper.list(interface_type);
    }
    /**
     * 根据id查询第三方接口
     */
    public ThirdInterface findById(int id){
        return thirdInterfaceMapper.findById(id);
    }
    /**
     * 修改第三方接口信息
     */
    public int update(ThirdInterface thirdInterface){
        return thirdInterfaceMapper.update(thirdInterface);
    }
}
