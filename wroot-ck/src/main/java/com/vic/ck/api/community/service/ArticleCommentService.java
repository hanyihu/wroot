package com.vic.ck.api.community.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.community.mapper.ArticleCommentMapper;
import com.vic.ck.entity.ArticleComment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章评论表
 */
@Service
public class ArticleCommentService extends BaseService {

    @Resource
    private ArticleCommentMapper articleCommentMapper;

    /**
     * 查询列表
     */
    public PageInfo<ArticleComment> list(Lookup lookup) {
        startPage(lookup);
        List<ArticleComment> datas = articleCommentMapper.list(lookup);
        return PageInfo.instance(datas, lookup);
    }

    /**
     * 根据主键查询
     */
    public ArticleComment findById(int id) {
        return articleCommentMapper.findById(id);
    }

    /**
     * 查询一个文章的2条评论
     */
    public List<ArticleComment> find2ArticleComments(int articleId) {
        return articleCommentMapper.find2ArticleComments(articleId);
    }

    /**
     * 新增
     */
    public int insert(ArticleComment entity) {
        return articleCommentMapper.insert(entity);
    }

    /**
     * 修改
     */
    public int update(ArticleComment entity) {
        return articleCommentMapper.update(entity);
    }

    /***
     * 删除
     */
    public int delete(int... ids) {
        return articleCommentMapper.delete(ids);
    }

}
