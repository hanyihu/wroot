package com.vic.ck.console.statistics.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.statistics.mapper.SettlementMapper;
import com.vic.ck.entity.Settlement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单统计报表
 *
 */
@Service
public class SettlementService extends BaseService {

    @Resource
    private SettlementMapper settlementMapper;



    /**
     * 查询列表
     */
    public PageInfo<Settlement> list(Lookup lookup) {
        startPage(lookup);
        List<Settlement> datas = settlementMapper.list(lookup);
        return PageInfo.instance(datas, lookup);
    }








}
