package com.vic.ck.api.community.controller;

import com.vic.base.BaseApiController;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.community.service.ArticleCommentService;
import com.vic.ck.api.community.service.ArticlePraiseService;
import com.vic.ck.api.community.service.ArticleService;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.platform.service.PlatformCommonService;
import com.vic.ck.console.community.lookup.ArticleLookup;
import com.vic.ck.entity.Article;
import com.vic.ck.entity.ArticleComment;
import com.vic.ck.entity.ArticlePraise;
import com.vic.ck.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 社区模块--吃喝玩乐 文章相关
 * @author VIC
 *
 */
@RestController
@RequestMapping("/api/community")
public class ArticleController extends BaseApiController{

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Resource
	private ArticleService articleService;
	
	@Resource
	private ArticleCommentService articleCommentService;
	
	@Resource
	private PlatformCommonService platformCommonService;

    @Autowired
    private ArticlePraiseService articlePraiseService;
	
	
	/**
	 *  4.1 -01 文章列表
	 */
	@RequestMapping( value = "/articles", method = RequestMethod.GET)
	public Object articles(ArticleLookup lookup) {
		lookup.setEnabled(1);
		PageInfo<Article> data = articleService.list(lookup);
		return resultSuccess(data);
	}
	
	/**
	 * 4.1-02 文章详情 + 2条评论
	 */
	@RequestMapping( value = "/article/{id}", method = RequestMethod.GET)
	public Object articleDetail(@PathVariable int id, @RequestParam(defaultValue = "0")int userId) {
		Article data = articleService.findPersonalArticle(id, userId);
		if(data != null) {
			data.setComments(articleCommentService.find2ArticleComments(id));
			
			//详情图片宽度设置
			data.setContent(CommonUtils.ueditorImgWigth(data.getContent()));
		}
		return resultSuccess(data);
	}
	
	/**
	 * 4.1-03 文章评论列表
	 */
	@RequestMapping( value = "/article/comments", method = RequestMethod.GET)
	public Object comments(ArticleLookup lookup) {
		PageInfo<ArticleComment> data = articleCommentService.list(lookup);
		return resultSuccess(data);
	}
	
	/**
     * 4.1-04 新增文章评论
	 */
	@RequestMapping( value = "/article/comment/add", method = RequestMethod.POST)
	public Object comment(ArticleComment comment) {
        //文章评论表-新增文章评论内容信息
		articleCommentService.insert(comment);
        //文章表修改评论数量
		articleService.addCommentNum(comment.getArticleId(), 1);
		return resultSuccess();
	}
	
	/**
	 * 4.1-05 点赞/取消点赞文章
     * 已修改，参考下面方法
	 */
/*	@RequestMapping( value = "/article/praise", method = RequestMethod.POST)
	@Transactional
	public Object praise(int userId, int articleId) {
		CommentPraise p = new CommentPraise(userId, articleId, 2);
		Integer id = platformCommonService.findPraiseId(p);
		if(id != null) { //取消点赞
			platformCommonService.deletePraise(id);
			articleService.addPraiseNum(articleId, -1);
		}else {
			platformCommonService.insertPraise(p);//新增点赞
			articleService.addPraiseNum(articleId, 1);
			
		}
		return resultSuccess();
		
	}*/

    /**
     * 4.1-05 点赞/取消点赞文章
     *
     * @author: jiachonggao
     * @desc: 区别于上面方法
     * @date: 2019-4-20 14:40:32
     */
    @RequestMapping(value = "/article/praise", method = RequestMethod.POST)
    @Transactional
    public Object praise(@RequestParam("consumerId") int userId, @RequestParam("articleId") int articleId) {
        logger.info("request param userId:{},articleId:{}", userId, articleId);
        ArticlePraise p = new ArticlePraise(userId, articleId, 2);
        ArticlePraise articlePraise = articlePraiseService.findPraiseId(p);
        //取消点赞
        if (articlePraise != null) {
            logger.info(" praise obj data:{}", articlePraise.toString());
            int i = articlePraiseService.deletePraise(articlePraise.getId());
            if (i < 0) {
                return resultError(ResultMsgEnum.FAIL);
            }
            articleService.addPraiseNum(articleId, -1);
        } else {
            //新增点赞
            int i = articlePraiseService.insertPraise(p);
            if (i < 0) {
                return resultError(ResultMsgEnum.FAIL);
            }
            articleService.addPraiseNum(articleId, 1);

        }
        return resultSuccess();

    }
	
}
