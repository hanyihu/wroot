package com.vic.ck.console.activity.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.activity.mapper.FullLimitnMapper;
import com.vic.ck.entity.PlatformActivityPulllimitn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 营销中心----满额优惠= 减、 送
 */
@Service
public class FullLimitnService extends BaseService {


    @Resource
    private FullLimitnMapper mj;


    /**
     * 查询列表
     */
    public PageInfo<PlatformActivityPulllimitn> list(Lookup lookup) {
        startPage(lookup);
        List<PlatformActivityPulllimitn> datas = mj.list(lookup);
        return PageInfo.instance(datas, lookup);
    }

    /**
     * 根据主键查询
     */
    public PlatformActivityPulllimitn findById(int id) {
        return mj.findById(id);
    }

    /**
     * 新增
     */
    @Transactional
    public int insert(PlatformActivityPulllimitn entity) {
        int result = mj.insert(entity);
        return result;
    }

    /**
     * 修改
     */
    @Transactional
    public int update(PlatformActivityPulllimitn entity) {
        return mj.update(entity);
    }

    /***
     * 删除
     */
    @Transactional
    public int delete(int... ids) {
        return mj.delete(ids);
    }


}
