package com.vic.ck.entity;

import java.math.BigDecimal;

/**
 * 商家交通
 * @author VIC
 *
 */
public class MerchantTraffic {
    /**
     * 
     */
    private Integer id;

    /**
     * 出发点
     */
    private String startPoint;

    /**
     * 商家id
     */
    private Integer merchantId;

    /**
     * 交通工具
     */
    private String tool;

    /**
     * 距离 千米
     */
    private BigDecimal distance;

    /**
     * 时间分钟
     */
    private BigDecimal times;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 出发点
     * @return start_point 出发点
     */
    public String getStartPoint() {
        return startPoint;
    }

    /**
     * 出发点
     * @param startPoint 出发点
     */
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint == null ? null : startPoint.trim();
    }

    /**
     * 商家id
     * @return merchant_id 商家id
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * 商家id
     * @param merchantId 商家id
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 交通工具
     * @return tool 交通工具
     */
    public String getTool() {
        return tool;
    }

    /**
     * 交通工具
     * @param tool 交通工具
     */
    public void setTool(String tool) {
        this.tool = tool == null ? null : tool.trim();
    }

    /**
     * 距离 千米
     * @return distance 距离 千米
     */
    public BigDecimal getDistance() {
        return distance;
    }

    /**
     * 距离 千米
     * @param distance 距离 千米
     */
    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    /**
     * 时间分钟
     * @return times 时间分钟
     */
    public BigDecimal getTimes() {
        return times;
    }

    /**
     * 时间分钟
     * @param times 时间分钟
     */
    public void setTimes(BigDecimal times) {
        this.times = times;
    }
}