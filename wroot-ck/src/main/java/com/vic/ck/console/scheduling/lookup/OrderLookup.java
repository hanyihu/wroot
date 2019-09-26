package com.vic.ck.console.scheduling.lookup;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.base.pager.CommonLookup;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xbw
 * @time 2019/4/18 10:48
 */
public class OrderLookup extends CommonLookup {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
