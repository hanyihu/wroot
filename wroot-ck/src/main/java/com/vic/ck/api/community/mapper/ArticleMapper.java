package com.vic.ck.api.community.mapper;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.Article;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 文章表
 * 
 * @author VIC
 */
@MybatisMapper
public interface ArticleMapper extends BaseMapper<Article> {

	
	Article findPersonalArticle(@Param("id")int id, @Param("customerId")int customerId);
	
	void addCommentNum(@Param("id") int id, @Param("num") int num);
	
	void addPraiseNum(@Param("id") int id, @Param("num") int num);
	
}
