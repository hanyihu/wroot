package com.vic.wroot.common.craw;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
/**
 * 爬虫基类
 * @author VIC
 *
 */
public abstract class BaseCrawl {
    /**
     * 连接网页
     * @param url
     * @return
     */
    protected CrawlConnect con(String url){
        Connection conn = Jsoup.connect(url).ignoreContentType(true).timeout(getTimeout());
        return new CrawlConnect(conn);
    }
    
    /**
     * 连接超时时间
     * @return
     */
    protected abstract int getTimeout();
}
