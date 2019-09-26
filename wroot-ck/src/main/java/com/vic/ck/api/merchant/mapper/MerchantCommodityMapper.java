package com.vic.ck.api.merchant.mapper;

import com.vic.base.BaseApiLookup;
import com.vic.ck.entity.MerchantGroupBuy;
import com.vic.ck.entity.MerchantHotel;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MybatisMapper
public interface MerchantCommodityMapper {

	/* ***************************团购************************************/
	/**团购列表*/
	List<MerchantGroupBuy> groupbuys(BaseApiLookup lookup);
	
	/**团购详情*/
	MerchantGroupBuy selectGroupbuyById(@Param("id") int id);
	
	/**新增团购*/
	int insertGroupbuy(MerchantGroupBuy groupbuy);
	
	/**新增团购销量*/
	void addGroupbuySellNum(@Param("id")int id, @Param("num")int num);

	
	/**修改团购*/
	int updateGroupbuy(MerchantGroupBuy groupbuy);

	/**标记删除团购*/
	int deleteGroupbuy(@Param("id") int id);

	/**上下架团购*/
	int updateGoupbuyStatus(@Param("id")int id, @Param("status")int status);
	
	
	
	/* ***************************酒店************************************/
	/**酒店列表*/
	List<MerchantHotel> hotels(BaseApiLookup lookup);
	
	/**酒店详情*/
	MerchantHotel selectHotelById(@Param("id") int id);
	
	/**新增酒店*/
	int insertHotel(MerchantHotel hotel);
	
	/**修改酒店*/
	int updateHotel(MerchantHotel hotel);

	/**标记删除酒店*/
	int deleteHotel(@Param("id") int id);

	/**上下架酒店*/
	int updateHotelStatus(@Param("id")int id, @Param("status")int status);

	
	/**新增商家销量*/
	void addMerchantSellNum(@Param("id")int id, @Param("num")int num);
	
}
