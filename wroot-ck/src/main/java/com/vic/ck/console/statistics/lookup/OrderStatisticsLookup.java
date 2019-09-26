package com.vic.ck.console.statistics.lookup;

import com.vic.base.pager.CommonLookup;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrderStatisticsLookup extends CommonLookup {

    /*
    * 商品订单查询条件
    * */

    /*订单状态查询*/
    private Integer status;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
