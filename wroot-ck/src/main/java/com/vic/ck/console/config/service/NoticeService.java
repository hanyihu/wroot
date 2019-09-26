package com.vic.ck.console.config.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.config.mapper.NoticeMapper;
import com.vic.ck.entity.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告管理Service层
 * @author houhaoran
 */
@Service
public class NoticeService extends BaseService {
    @Resource
    private NoticeMapper noticeMapper;

    /**
     * 查询公告管理列表
     * @param lookup 查询条件
     * @return 公告管理数据及页面信息
     */
    public PageInfo<Notice> list(Lookup lookup){
        startPage(lookup);
        List<Notice> datas = noticeMapper.list(lookup);
        return PageInfo.instance(datas,lookup);
    }

    /**
     * 根据主键查询公告对象
     * @param id
     * @return
     */
    public Notice getNoticeById(Integer id){
        return noticeMapper.findById(id);
    }

    /**
     * 新增一条公告管理
     * @param notice 公告对象
     * @return 新增的条数 0：失败 1：成功
     */
    public int insert(Notice notice){
        return noticeMapper.insert(notice);
    }

    /**
     * 修改公告管理信息
     * @param notice 公告对象
     * @return 0：失败 1：成功
     */
    public int update(Notice notice){
        return noticeMapper.update(notice);
    }

    /**
     * 批量删除
     * @param ids 需要被删除的公告id
     * @return 成功删除的条数
     */
    public int deleteNotice(int... ids){
        return noticeMapper.delete(ids);
    }
}
