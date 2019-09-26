package com.vic.ck.console.community.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.community.mapper.ArticleCategoryMapper;
import com.vic.ck.entity.ArticleCategory;

/**
 * 文章分类表
 *
 */
@Service
public class ArticleCategoryService extends BaseService {

	@Resource
	private ArticleCategoryMapper articleCategoryMapper;

	/**
	 * 查询列表
	 */
	public PageInfo<ArticleCategory> list(Lookup lookup) {
		startPage(lookup);
		List<ArticleCategory> datas = articleCategoryMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	/**
	 * 全部分类
	 */
	public List<ArticleCategory> all(){
		return articleCategoryMapper.list(new CommonLookup());
	}

	/**
	 * 根据主键查询
	 */
	public ArticleCategory findById(int id) {
		return articleCategoryMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(ArticleCategory entity) {
		return articleCategoryMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(ArticleCategory entity) {
		return articleCategoryMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return articleCategoryMapper.delete(ids);
	}

}
