package com.vic.ck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vic.wroot.common.annotation.AttachmentFlag;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*@author oyml
*@Description commodity实体类
*@date 2222-2-22
*/

public class Commodity implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private Integer id;

	/*商品名称*/
	@ApiModelProperty(value = "商品名称")
	private String name;

	
	private Integer type;

	@ApiModelProperty(value = "商品图标")
	private Integer icon;

	/*图片*/
	@ApiModelProperty(value = "商品图片")
	@AttachmentFlag(AttachmentFlag.AttachmenType.SIGNS)
	private String images;

	/*单价*/
	@ApiModelProperty(value = "单价")
	private BigDecimal price;

	
	private BigDecimal discount_price;

	
	private Integer sell_num;

	
	private Integer category_id;

	
	private Integer sub_category_id;

	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date coupon_start_date;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date coupon_end_date;
	
	private BigDecimal coupon_amount;

	
	private String coupon_url;

	
	private String summary;

	/*详情*/
	@ApiModelProperty(value = "商品详细描述")
	private String content;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date create_time;

	
	private Integer today_recommended;

	/*商品所属的商家id*/
	private Integer brand_seller_id;

	
	private Integer module;

	
	private Integer module_category_id;

	
	private Integer need_buyed;

	/*0-下架1-上架  2下架审核中  3上架审核中 4待上架 5待下架   */
	private Integer enabled;

	
	private String item_id;

	
	private BigDecimal commision;

	
	private Integer free_ship;

	
	private String tao_token;

	
	private String out_icon;

	
	private String outer_images;

	
	private String detail_url;

	
	private Integer flag;

	
	private Integer handle_status;

	
	private Integer special_category_id;

	private int examine;

	/*审核不通过说明*/
	private String examineRemarks;


	/*单位*/
	@ApiModelProperty(value = "单位")
	private String company;

	/*规格*/
	@ApiModelProperty(value = "规格")
	private String spec;

	/*库存*/
	@ApiModelProperty(value = "库存")
	private Integer stock;

	/*属性*/
	@ApiModelProperty(value = "属性")
	private String attribute;

	/*是否团购  0否  1是*/
	private Integer isgroupbuy;


	/*商品所属的一级分类名*/
	private String categoryName;

	/*商品所属的商家名称*/
	private String merchantName;

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getIcon() {
		return icon;
	}


	public void setIcon(Integer icon) {
		this.icon = icon;
	}


	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public BigDecimal getDiscount_price() {
		return discount_price;
	}


	public void setDiscount_price(BigDecimal discount_price) {
		this.discount_price = discount_price;
	}


	public Integer getSell_num() {
		return sell_num;
	}


	public void setSell_num(Integer sell_num) {
		this.sell_num = sell_num;
	}


	public Integer getCategory_id() {
		return category_id;
	}


	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}


	public Integer getSub_category_id() {
		return sub_category_id;
	}


	public void setSub_category_id(Integer sub_category_id) {
		this.sub_category_id = sub_category_id;
	}


	public Date getCoupon_start_date() {
		return coupon_start_date;
	}


	public void setCoupon_start_date(Date coupon_start_date) {
		this.coupon_start_date = coupon_start_date;
	}


	public Date getCoupon_end_date() {
		return coupon_end_date;
	}


	public void setCoupon_end_date(Date coupon_end_date) {
		this.coupon_end_date = coupon_end_date;
	}


	public BigDecimal getCoupon_amount() {
		return coupon_amount;
	}


	public void setCoupon_amount(BigDecimal coupon_amount) {
		this.coupon_amount = coupon_amount;
	}


	public String getCoupon_url() {
		return coupon_url;
	}


	public void setCoupon_url(String coupon_url) {
		this.coupon_url = coupon_url;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public Integer getToday_recommended() {
		return today_recommended;
	}


	public void setToday_recommended(Integer today_recommended) {
		this.today_recommended = today_recommended;
	}


	public Integer getBrand_seller_id() {
		return brand_seller_id;
	}


	public void setBrand_seller_id(Integer brand_seller_id) {
		this.brand_seller_id = brand_seller_id;
	}


	public Integer getModule() {
		return module;
	}


	public void setModule(Integer module) {
		this.module = module;
	}


	public Integer getModule_category_id() {
		return module_category_id;
	}


	public void setModule_category_id(Integer module_category_id) {
		this.module_category_id = module_category_id;
	}


	public Integer getNeed_buyed() {
		return need_buyed;
	}


	public void setNeed_buyed(Integer need_buyed) {
		this.need_buyed = need_buyed;
	}


	public Integer getEnabled() {
		return enabled;
	}


	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


	public String getItem_id() {
		return item_id;
	}


	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}


	public BigDecimal getCommision() {
		return commision;
	}


	public void setCommision(BigDecimal commision) {
		this.commision = commision;
	}


	public Integer getFree_ship() {
		return free_ship;
	}


	public void setFree_ship(Integer free_ship) {
		this.free_ship = free_ship;
	}


	public String getTao_token() {
		return tao_token;
	}


	public void setTao_token(String tao_token) {
		this.tao_token = tao_token;
	}


	public String getOut_icon() {
		return out_icon;
	}


	public void setOut_icon(String out_icon) {
		this.out_icon = out_icon;
	}


	public String getOuter_images() {
		return outer_images;
	}


	public void setOuter_images(String outer_images) {
		this.outer_images = outer_images;
	}


	public String getDetail_url() {
		return detail_url;
	}


	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}


	public Integer getFlag() {
		return flag;
	}


	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	public Integer getHandle_status() {
		return handle_status;
	}


	public void setHandle_status(Integer handle_status) {
		this.handle_status = handle_status;
	}


	public Integer getSpecial_category_id() {
		return special_category_id;
	}


	public void setSpecial_category_id(Integer special_category_id) {
		this.special_category_id = special_category_id;
	}


	public void setExamine(int examine) {
		this.examine = examine;
	}

	public int getExamine() {
		return examine;
	}


	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Integer getIsgroupbuy() {
		return isgroupbuy;
	}

	public void setIsgroupbuy(Integer isgroupbuy) {
		this.isgroupbuy = isgroupbuy;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getExamineRemarks() {
		return examineRemarks;
	}

	public void setExamineRemarks(String examineRemarks) {
		this.examineRemarks = examineRemarks;
	}
}

