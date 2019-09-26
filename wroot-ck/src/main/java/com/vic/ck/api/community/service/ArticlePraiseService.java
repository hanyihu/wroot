package com.vic.ck.api.community.service;

import com.vic.base.BaseService;
import com.vic.ck.api.community.mapper.ArticlePraiseMapper;
import com.vic.ck.entity.ArticlePraise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiachonggao
 * @Description: 文章点赞表业务层
 * @date 2019/4/2013:48
 */
@Service
public class ArticlePraiseService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(ArticlePraiseService.class);

    @Resource
    private ArticlePraiseMapper articlePraiseMapper;

    /**
     * 根据用户id和文章id,查询点赞信息
     *
     * @param p
     * @return
     */
    public ArticlePraise findPraiseId(ArticlePraise p) {
        return articlePraiseMapper.findPraiseById(p);
    }

    /**
     * 根据id,删除点赞信息
     *
     * @param id
     * @return
     */
    public int deletePraise(Integer id) {
        try {
            return articlePraiseMapper.deletePraiseById(id);
        } catch (Exception e) {
            logger.error(" delete praise error:{}", e.getMessage());
            return -1;
        }
    }

    /**
     * 新增文章点赞信息
     *
     * @param p
     * @return
     */
    public int insertPraise(ArticlePraise p) {
        try {
            return articlePraiseMapper.insertPraise(p);
        } catch (Exception e) {
            logger.error(" add praise error:{}", e.getMessage());
            return -1;
        }
    }
}
