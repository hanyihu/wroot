package com.vic.ck.console.sysmanagement.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.sysmanagement.mapper.EvaluationMapper;
import com.vic.ck.entity.CommodityEvaluation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EvaluationService extends BaseService {
    @Resource
    private EvaluationMapper evaluationMapper;

    /**
     * 查询商品评价列表
     * @param lookup
     * @return
     */
    public PageInfo<CommodityEvaluation> list(Lookup lookup){
        startPage(lookup);
        List<CommodityEvaluation> datas = evaluationMapper.list(lookup);
        return PageInfo.instance(datas,lookup);
    }

    /**
     * 根据主键查询评价管理对象
     * @param id
     * @return
     */
    public CommodityEvaluation findById(int id){
        return evaluationMapper.findById(id);
    }

    /**
     * 修改商品评价管理
     * @param evaluation
     * @return
     */
    public int update(CommodityEvaluation evaluation){
        return evaluationMapper.update(evaluation);
    }
}
