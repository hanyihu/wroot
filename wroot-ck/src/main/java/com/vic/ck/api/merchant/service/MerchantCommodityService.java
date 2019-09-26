package com.vic.ck.api.merchant.service;

import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchant.mapper.MerchantCommodityMapper;
import com.vic.ck.entity.MerchantGroupBuy;
import com.vic.ck.entity.MerchantHotel;
import com.vic.wroot.common.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MerchantCommodityService extends BaseService{

	@Resource
	private MerchantCommodityMapper merchantCommodityMapper;
	
	@Resource
	private AttachmentService attachmentService;

	
	/* ******************团购*********************************************/
	
	/**团购列表*/
	public PageInfo<MerchantGroupBuy> groupbuys(BaseApiLookup lookup) {
		startPage(lookup);
		List<MerchantGroupBuy> datas = merchantCommodityMapper.groupbuys(lookup);
		return PageInfo.instance(datas);
	} 
	
	/**新增团购*/
	@Transactional
	public int insertGroupbuy(MerchantGroupBuy groupbuy) {
		attachmentService.addAttachmengFromObj(groupbuy);
		return merchantCommodityMapper.insertGroupbuy(groupbuy);
	}
	
	/**修改团购*/
	@Transactional
	public int updateGroupbuy(MerchantGroupBuy groupbuy) {
		MerchantGroupBuy old = selectGroupbuyById(groupbuy.getId());
		attachmentService.HandleOldAndNowAttachment(old, groupbuy);
		return merchantCommodityMapper.updateGroupbuy(groupbuy);
	}
	
	/**团购详情*/
	
	public MerchantGroupBuy selectGroupbuyById(int id) {
		return merchantCommodityMapper.selectGroupbuyById(id);
	}
	
	/**标记删除团购*/
	public int deleteGroupbuy(int id) {
		return merchantCommodityMapper.deleteGroupbuy(id);
	}
	
	/**上下架团购*/
	public int updateGoupbuyStatus(int id, int status) {
		return merchantCommodityMapper.updateGoupbuyStatus(id, status);
	}
	
	/* *****************酒店*********************************************/
	
	/**酒店列表*/
	public PageInfo<MerchantHotel> hotels(BaseApiLookup lookup) {
		startPage(lookup);
		List<MerchantHotel> datas = merchantCommodityMapper.hotels(lookup);
		return PageInfo.instance(datas);
	} 
	
	/**酒店*/
	@Transactional
	public int insertHotel(MerchantHotel hotel) {
		attachmentService.addAttachmengFromObj(hotel);
		return merchantCommodityMapper.insertHotel(hotel);
	}
	
	
	/**酒店详情*/
	public MerchantHotel selectHotelById(int id) {
		return merchantCommodityMapper.selectHotelById(id);
	}
	
	/**修改酒店*/
	@Transactional
	public int updateHotel(MerchantHotel hotel) {
		MerchantHotel old = selectHotelById(hotel.getId());
		attachmentService.HandleOldAndNowAttachment(old, hotel);
		return merchantCommodityMapper.updateHotel(hotel);
	}
	
	
	/**标记删除酒店*/
	public int deleteHotel(int id) {
		return merchantCommodityMapper.deleteHotel(id);
	}
	
	/**上下架酒店*/
	public int updateHotelStatus(int id, int status) {
		return merchantCommodityMapper.updateHotelStatus(id, status);
	}
	
	
}
