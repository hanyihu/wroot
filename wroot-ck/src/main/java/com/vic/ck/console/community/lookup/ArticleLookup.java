package com.vic.ck.console.community.lookup;
import com.vic.base.pager.CommonLookup;

/**
 * 封装文章表查询条件 
 * 
 * @author VIC
 */
public class ArticleLookup extends CommonLookup {
	
	private Integer articleId;

	public Integer getArticleId() {
		return articleId;
	}

	public ArticleLookup setArticleId(Integer articleId) {
		this.articleId = articleId;
		return this;
	}
}
