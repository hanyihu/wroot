package com.vic.ck.api.merchantapi.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchantapi.mapper.CommodityManageApiMapper;
import com.vic.ck.entity.Commodity;
import com.vic.ck.entity.MerchantCorresponding;
import com.vic.wroot.common.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by: hanyihu
 * create time: 2019/4/29 8:59
 * 商家端
 */
@Service
public class CommodityManageApiService extends BaseService{

	@Resource
	private CommodityManageApiMapper commodityManageApiMapper;


	@Resource
	private AttachmentService attachmentService;

	/*查询商品信息*/
	public PageInfo<Commodity> findList(int id, Lookup lookup){
		startPage(lookup);

		List<Commodity> datas = commodityManageApiMapper.findList(id, lookup);
		return PageInfo.instance(datas,lookup);
	}

	//商家端 订单统计
	/*public OrderStatisticsVo orderStatistics(int id, Integer createTime) {
		Integer orderNum = commodityManageApiMapper.orderNum(id,createTime);//接单数
		Integer completeOrder = commodityManageApiMapper.completeOrder(id,createTime);//完成订单数
		Integer cancelOrders= commodityManageApiMapper.cancelOrders(id,createTime);//取消订单数
		BigDecimal profit= commodityManageApiMapper.profit(id,createTime);//收益
		OrderStatisticsVo entity=new OrderStatisticsVo();
		entity.setOrderNum(orderNum);
		entity.setCompleteOrder(completeOrder);
		entity.setCancelOrders(cancelOrders);
		if(profit==null){
			entity.setProfit(BigDecimal.ZERO);
		} else{
			entity.setProfit(profit);
		}

		return entity;
		return null;
	}*/

	/**
	 * 根据主键查询
	 */
	@Transactional
	public Commodity findById(int id) {
		return commodityManageApiMapper.findById(id);
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(Commodity entity) {
		attachmentService.addAttachmengFromObj(entity);
		return commodityManageApiMapper.insert(entity);
	}

	/*
	 * 插入商品和商家信息到数据表中

	 * */
	public int insertCommodity(MerchantCorresponding entity) {
		return commodityManageApiMapper.insertCommodity(entity);
	}


	/**
	 * 修改
	 */
	@Transactional
	public int update(Commodity entity) {
		Commodity old = findById(entity.getId());
		attachmentService.HandleOldAndNowAttachment(old, entity);
		return commodityManageApiMapper.update(entity);
	}

	/***
	 * 删除
	 */
	@Transactional
	public int delete(int... ids) {
		attachmentService.deleteAttachmengFromObj(commodityManageApiMapper.findByIds(ids));
		return commodityManageApiMapper.delete(ids);
	}

	/**
	 * create by: hanyihu
	 * description: TODO
	 * create time: 2019/4/29 8:59
	 *  商家端  商品筛选（在售中，已售罄，已下架）
	 * @Param: null
	 * @return
	 */
	public PageInfo<Commodity> screen(int id, int type, Lookup lookup) {
		startPage(lookup);
		List<Commodity> datas = commodityManageApiMapper.screen(id, type, lookup);
		return PageInfo.instance(datas, lookup);
	}


}
