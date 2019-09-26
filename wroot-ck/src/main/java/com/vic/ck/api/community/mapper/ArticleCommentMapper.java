package com.vic.ck.api.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.ArticleComment;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 文章评论表
 * 
 * @author VIC
 */
@MybatisMapper
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

	/**查询一个文章的2条评论*/
	List<ArticleComment> find2ArticleComments(@Param("articleId")int articleId);
	
}
