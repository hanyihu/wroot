package com.vic.ck.api.merchant.mapper;

import com.vic.base.BaseApiLookup;
import com.vic.ck.entity.Merchant;
import com.vic.ck.entity.MerchantAuthentication;
import com.vic.ck.entity.MerchantPhoto;
import com.vic.ck.entity.MerchantTraffic;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MybatisMapper
public interface MerchantMapper {

	/** 空就不插入数据库 了*/
	int insertMerchant(Merchant record);

	Merchant findMerchantByCustomerId(@Param("customerId") int customerId);
	
	Merchant findMerchantById(@Param("id") int id);
	
	boolean findMerchantByName(@Param("name") String name);
	
	boolean findMerchantByMobile(@Param("mobile") String mobile);
	
	/**空则不更新了 方便多出复用*/
	int updateMerchant(Merchant record);
	
	/**新增用户实名认证*/
	int insertMerchantAuthentication(MerchantAuthentication authentication);
	
	void updateQrcode(@Param("id") int id, @Param("qrcode") int qrcode);

	/**新增商家图片*/
	int insertPhoto(MerchantPhoto photo);

	/**删除商家图片*/
    void deletePhoto(@Param("id") int id);

	/**商家相册列表*/
	List<MerchantPhoto> merchantPhotos(BaseApiLookup lookup);

	/**商家交通信息列表*/
	List<MerchantTraffic> traffics(BaseApiLookup lookup);

	int insertTraffic(MerchantTraffic traffic);
	
	void deleteTraffic(@Param("id") int id);


    /**
     * 热门商家查询（精选）
     */
	List<Merchant> rmssList(BaseApiLookup lookup);

    /**
     * 附近商家
     */
	List<Merchant> fjsjList(@Param("maxjd") Double maxjd, @Param("minjd") Double minjd,@Param("maxwd") Double maxwd, @Param("minwd") Double minwd);
}