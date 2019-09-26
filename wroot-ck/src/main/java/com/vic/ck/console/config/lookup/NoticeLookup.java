package com.vic.ck.console.config.lookup;

import com.vic.base.pager.CommonLookup;

/**
 * 封装公告管理查询条件
 * @author houhaoran
 */
public class NoticeLookup extends CommonLookup {
    /**
     * 公告名称
     */
    private String title;
    /**
     * 公告类型
     */
    private Integer notice_type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(Integer notice_type) {
        this.notice_type = notice_type;
    }
}
