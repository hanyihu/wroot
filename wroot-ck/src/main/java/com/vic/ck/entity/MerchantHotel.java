package com.vic.ck.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import com.vic.wroot.common.annotation.AttachmentFlag.AttachmenType;
/**
 * 商家酒店预约
 * @author VIC
 *
 */
public class MerchantHotel {
    /**
     * 
     */
    private Integer id;

  
	/**
     * 
     */
    private Integer merchantId;
    
    @JsonIgnore
    private String merchantName;
    
    @JsonIgnore
    private String merchantMobile;

    /**
     * 图标
     */
    @AttachmentFlag(AttachmenType.SIGN)
    private Integer icon;

    /**
     * 图片
     */
    @AttachmentFlag(AttachmenType.SIGNS)
    private String images;

    /**
     * 房间标题
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存数
     */
    private Integer stock;

    /**
     * 退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
     */
    private Integer unsubscribeWay;

    /**
     * 订单确认时间 1-1小时内确认 2-立即确认
     */
    private Integer verifyWay;

    /**
     * 床型 1-单人床 2-豪华大床 3-双人床 4-三人床
     */
    private Integer roomBed;

    /**
     * 可住人数
     */
    private Integer roomPersonNum;

    /**
     * 宽带 1-无网络 2-有线宽带 3-无线WIFI
     */
    private Integer roomNetwork;

    /**
     * 窗户 1-无窗 2-有窗 3-部分有窗
     */
    private Integer roomWindow;

    /**
     * 面积
     */
    private Double roomArea;

    /**
     * 楼层
     */
    private Integer roomFloor;

    /**
     * 电话 1-无电话 2-免费电话 3-收费电话
     */
    private Integer roomTel;

    /**
     * 窗景：1-无 2-风景3-城景 4-花园景 5-地标景 6-无敌海景
     */
    private Integer roomWindowmView;

    /**
     * 早餐 1-无早餐 2-含早餐 3-双份早餐
     */
    private Integer roomBreakfast;

    /**
     * 可否加床
     */
    private Boolean roomAddBeded;

    /**
     * 无烟 1- 可吸烟2-该房无烟处理
     */
    private Integer roomSmoke;

    /**
     * 床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床
     */
    private Integer roomBedWide;
    
    
    @JsonIgnore
    private Integer isDelete;
    
    @JsonIgnore
    private Date createTime;

    public String getIconUrl() {
    	return CommonUtils.getImageUrl(icon);
    }
    
    public List<String> getImageUtls(){
    	return CommonUtils.getImageUrls(images);
    }
    
    
    /**
     * 0-待上线 1-已上线 2-已下线 
     */
    private int status = 0;

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
     * 
     * @return merchat_id 
     */
    public Integer getMerchantId() {
        return merchantId;
    }

    /**
     * 
     * @param merchatId 
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 图标
     * @return icon 图标
     */
    public Integer getIcon() {
        return icon;
    }

    /**
     * 图标
     * @param icon 图标
     */
    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    /**
     * 图片
     * @return images 图片
     */
    public String getImages() {
        return images;
    }

    /**
     * 图片
     * @param images 图片
     */
    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    /**
     * 房间标题
     * @return name 房间标题
     */
    public String getName() {
        return name;
    }

    /**
     * 房间标题
     * @param name 房间标题
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 价格
     * @return price 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 价格
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 库存数
     * @return stock 库存数
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 库存数
     * @param stock 库存数
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
     * @return unsubscribe_way 退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
     */
    public Integer getUnsubscribeWay() {
        return unsubscribeWay;
    }

    /**
     * 退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
     * @param unsubscribeWay 退订政策 1-当天18：00前可免费取消订单 2 预定成功后 不可变更取消
     */
    public void setUnsubscribeWay(Integer unsubscribeWay) {
        this.unsubscribeWay = unsubscribeWay;
    }

    /**
     * 订单确认时间 1-1小时内确认 2-立即确认
     * @return verify_way 订单确认时间 1-1小时内确认 2-立即确认
     */
    public Integer getVerifyWay() {
        return verifyWay;
    }

    /**
     * 订单确认时间 1-1小时内确认 2-立即确认
     * @param verifyWay 订单确认时间 1-1小时内确认 2-立即确认
     */
    public void setVerifyWay(Integer verifyWay) {
        this.verifyWay = verifyWay;
    }

    /**
     * 床型 1-单人床 2-豪华大床 3-双人床 4-三人床
     * @return room_bed 床型 1-单人床 2-豪华大床 3-双人床 4-三人床
     */
    public Integer getRoomBed() {
        return roomBed;
    }

    /**
     * 床型 1-单人床 2-豪华大床 3-双人床 4-三人床
     * @param roomBed 床型 1-单人床 2-豪华大床 3-双人床 4-三人床
     */
    public void setRoomBed(Integer roomBed) {
        this.roomBed = roomBed;
    }

    /**
     * 可住人数
     * @return room_person_num 可住人数
     */
    public Integer getRoomPersonNum() {
        return roomPersonNum;
    }

    /**
     * 可住人数
     * @param roomPersonNum 可住人数
     */
    public void setRoomPersonNum(Integer roomPersonNum) {
        this.roomPersonNum = roomPersonNum;
    }

    /**
     * 宽带 1-无网络 2-有线宽带 3-无线WIFI
     * @return room_network 宽带 1-无网络 2-有线宽带 3-无线WIFI
     */
    public Integer getRoomNetwork() {
        return roomNetwork;
    }

    /**
     * 宽带 1-无网络 2-有线宽带 3-无线WIFI
     * @param roomNetwork 宽带 1-无网络 2-有线宽带 3-无线WIFI
     */
    public void setRoomNetwork(Integer roomNetwork) {
        this.roomNetwork = roomNetwork;
    }

    /**
     * 窗户 1-无窗 2-有窗 3-部分有窗
     * @return room_window 窗户 1-无窗 2-有窗 3-部分有窗
     */
    public Integer getRoomWindow() {
        return roomWindow;
    }

    /**
     * 窗户 1-无窗 2-有窗 3-部分有窗
     * @param roomWindow 窗户 1-无窗 2-有窗 3-部分有窗
     */
    public void setRoomWindow(Integer roomWindow) {
        this.roomWindow = roomWindow;
    }

    /**
     * 面积
     * @return room_area 面积
     */
    public Double getRoomArea() {
        return roomArea;
    }

    /**
     * 面积
     * @param roomArea 面积
     */
    public void setRoomArea(Double roomArea) {
        this.roomArea = roomArea;
    }

    /**
     * 楼层
     * @return room_floor 楼层
     */
    public Integer getRoomFloor() {
        return roomFloor;
    }

    /**
     * 楼层
     * @param roomFloor 楼层
     */
    public void setRoomFloor(Integer roomFloor) {
        this.roomFloor = roomFloor;
    }

    /**
     * 电话 1-无电话 2-免费电话 3-收费电话
     * @return room_tel 电话 1-无电话 2-免费电话 3-收费电话
     */
    public Integer getRoomTel() {
        return roomTel;
    }

    /**
     * 电话 1-无电话 2-免费电话 3-收费电话
     * @param roomTel 电话 1-无电话 2-免费电话 3-收费电话
     */
    public void setRoomTel(Integer roomTel) {
        this.roomTel = roomTel;
    }

    /**
     * 窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景
     * @return room_windowm_view 窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景
     */
    public Integer getRoomWindowmView() {
        return roomWindowmView;
    }

    /**
     * 窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景
     * @param roomWindowmView 窗景：1-无 2-风景2-城景 3-花园景 4-地标景 5-无敌海景
     */
    public void setRoomWindowmView(Integer roomWindowmView) {
        this.roomWindowmView = roomWindowmView;
    }

    /**
     * 早餐 1-无早餐 2-含早餐 3-双份早餐
     * @return room_breakfast 早餐 1-无早餐 2-含早餐 3-双份早餐
     */
    public Integer getRoomBreakfast() {
        return roomBreakfast;
    }

    /**
     * 早餐 1-无早餐 2-含早餐 3-双份早餐
     * @param roomBreakfast 早餐 1-无早餐 2-含早餐 3-双份早餐
     */
    public void setRoomBreakfast(Integer roomBreakfast) {
        this.roomBreakfast = roomBreakfast;
    }

    /**
     * 可否加床
     * @return room_add_beded 可否加床
     */
    public Boolean getRoomAddBeded() {
        return roomAddBeded;
    }

    /**
     * 可否加床
     * @param roomAddBeded 可否加床
     */
    public void setRoomAddBeded(Boolean roomAddBeded) {
        this.roomAddBeded = roomAddBeded;
    }

    /**
     * 无烟 1- 可吸烟2-该房无烟处理
     * @return room_smoke 无烟 1- 可吸烟2-该房无烟处理
     */
    public Integer getRoomSmoke() {
        return roomSmoke;
    }

    /**
     * 无烟 1- 可吸烟2-该房无烟处理
     * @param roomSmoke 无烟 1- 可吸烟2-该房无烟处理
     */
    public void setRoomSmoke(Integer roomSmoke) {
        this.roomSmoke = roomSmoke;
    }

    /**
     * 床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床
     * @return room_bed_wide 床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床
     */
    public Integer getRoomBedWide() {
        return roomBedWide;
    }

    /**
     * 床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床
     * @param roomBedWide 床宽1-圆形床 2-1.5米大床 3-1.8米大床 3-2.0米大床
     */
    public void setRoomBedWide(Integer roomBedWide) {
        this.roomBedWide = roomBedWide;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	  public String getMerchantName() {
			return merchantName;
		}

		public void setMerchantName(String merchantName) {
			this.merchantName = merchantName;
		}

		public String getMerchantMobile() {
			return merchantMobile;
		}

		public void setMerchantMobile(String merchantMobile) {
			this.merchantMobile = merchantMobile;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}




}