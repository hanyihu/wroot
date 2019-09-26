package com.vic.ck.api.community.mapper;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.ArticlePraise;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 文章点赞表
 *
 * @author jiachonggao
 */
@MybatisMapper
public interface ArticlePraiseMapper extends BaseMapper<ArticlePraise> {

    ArticlePraise findPraiseById(ArticlePraise articlePraise);

    int deletePraiseById(@Param("id") int id);

    int insertPraise(ArticlePraise articlePraise);
}
