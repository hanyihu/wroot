package com.vic.ck.api.community.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.community.mapper.ArticleMapper;
import com.vic.ck.entity.Article;
import com.vic.wroot.common.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章表
 */
@Service
public class ArticleService extends BaseService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private AttachmentService attachmentService;

    /**
     * 查询列表
     */
    public PageInfo<Article> list(Lookup lookup) {
        startPage(lookup);
        List<Article> datas = articleMapper.list(lookup);
        return PageInfo.instance(datas, lookup);
    }

    /**
     * 根据主键查询
     */
    public Article findById(int id) {
        return articleMapper.findById(id);
    }

    /**
     * 查询文章详情
     */
    public Article findPersonalArticle(int id, int userId) {
        return articleMapper.findPersonalArticle(id, userId);
    }

    /**
     * 新增
     */
    public int insert(Article entity) {
        attachmentService.addAttachmengFromObj(entity);
        return articleMapper.insert(entity);
    }

    /**
     * 修改
     */
    @Transactional
    public int update(Article entity) {
        Article old = findById(entity.getId());
        //修改新旧附件相关状态
        attachmentService.HandleOldAndNowAttachment(old, entity);
        return articleMapper.update(entity);
    }

    /***
     * 删除
     */
    public int delete(int... ids) {
        attachmentService.deleteAttachmengFromObj(articleMapper.findByIds(ids));
        return articleMapper.delete(ids);
    }

    /**
     * 新增评论数
     */
    public void addCommentNum(int id, int num) {
        articleMapper.addCommentNum(id, num);
    }

    public void addPraiseNum(int id, int num) {
        articleMapper.addPraiseNum(id, num);
    }

}
