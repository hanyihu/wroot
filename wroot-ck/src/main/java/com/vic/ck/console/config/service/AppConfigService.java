package com.vic.ck.console.config.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.config.mapper.AppConfigMapper;
import com.vic.ck.entity.BasicConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppConfigService extends BaseService {
    @Resource
    private AppConfigMapper appConfigMapper;
    /**
     * 查询小程序管理列表
     * @param lookup 查询条件
     * @return 小程序管理数据及页面信息
     */
    public PageInfo<BasicConfig> list(Lookup lookup){
        startPage(lookup);
        List<BasicConfig> datas = appConfigMapper.list(lookup);
        return PageInfo.instance(datas,lookup);
    }

    /**
     * 根据id查询小程序信息
     * @param id
     * @return
     */
    public BasicConfig getAppConfigById(int id){
        return appConfigMapper.findById(id);
    }

    /**
     * 新增小程序信息
     * @param config
     * @return
     */
    public int insert(BasicConfig config){
        return appConfigMapper.insert(config);
    }
    /**
     * 修改小程序信息
     */
    public int update(BasicConfig config){
        return appConfigMapper.update(config);
    }

    /**
     * 批量删除
     * @param ids 需要被删除的小程序id
     * @return 成功删除的条数
     */
    public int delete(int... ids){
        return appConfigMapper.delete(ids);
    }
}
