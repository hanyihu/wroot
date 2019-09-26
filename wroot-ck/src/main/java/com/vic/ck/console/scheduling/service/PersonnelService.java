package com.vic.ck.console.scheduling.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.scheduling.mapper.PersonnelMapper;
import com.vic.ck.entity.RiderOrder;
import com.vic.ck.entity.RiderPersonnel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



@Service
public class PersonnelService extends BaseService {
    @Resource
    PersonnelMapper personnelMapper;

    public PageInfo<RiderPersonnel> selectRiderPersonnel(Lookup lookup){
        startPage(lookup);
        List<RiderPersonnel> personnels = personnelMapper.selectRiderPersonnel(lookup);
        return PageInfo.instance(personnels, lookup);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public RiderPersonnel selectById(int id){
        return personnelMapper.selectById(id);
    }

    /**
     * 所有订单
     * @param lookup
     * @return
     */
    public PageInfo<RiderOrder> selectAllOrder(Lookup lookup){
        startPage(lookup);
        List<RiderOrder> riderOrders = personnelMapper.selectAllOrder(lookup);
        return PageInfo.instance(riderOrders, lookup);
    }
}
