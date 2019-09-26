package com.vic.ck.api.merchant.service;

import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.AttachmentModuleEnum;
import com.vic.ck.api.merchant.mapper.MerchantMapper;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.entity.*;
import com.vic.wroot.common.attachment.service.AttachmentService;
import com.vic.wroot.common.po.AjaxResponse;
import com.vic.wroot.common.util.CommonUtils;
import com.vic.wroot.common.util.QRCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerchantService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(MerchantService.class);

	@Resource
	private MerchantMapper merchantMapper;

	@Resource
	private AttachmentService attachmentService;

	@Resource
	private MerchantBalanceService merchantBalanceService;

	@Resource
	private AuthService authService;

	public Merchant findMerchantByCustomerId(int customerId) {

		return merchantMapper.findMerchantByCustomerId(customerId);
	}

	/**
	 * 新增商家审核信息
	 * 
	 * @param record
	 * @return
	 */
	@Transactional
	public int insertMerchant(Merchant record) {
		//attachmentService.updateTemporary(false, record.findAllImages());
		attachmentService.addAttachmengFromObj(record);// 修改附件为持久态
		Customer c = authService.findCustomerById(record.getCustomerId());
		if (c != null) {
			record.setRecommendId(c.getRecommendId());
		}
		int result = merchantMapper.insertMerchant(record);
		generateQrcode(record);
		merchantBalanceService.insertMerchantBalance(record.getId());
		return result;
	}

	/**
	 * 修改商家信息
	 */
	public int updateMerchant(Merchant merchant) {
		Merchant old = merchantMapper.findMerchantById(merchant.getId());
		// 根据status 判断是否修改基本信息还是
		Integer status = merchant.getStatus();
		if (status == null) {// 修改商家信息.只有商家详情里包含图片
			attachmentService.updateTemporary(true, old.getDescribe());
			attachmentService.updateTemporary(false, merchant.getDescribe());
		} else if (status == 1) {// 包含商家详情外的的全部图片
			attachmentService.HandleOldAndNowAttachment(old, merchant);
		}

		return merchantMapper.updateMerchant(merchant);
	}

	/**
	 * 提交用户身份证信息
	 */
	@Transactional
	public int insertMerchantAuthentication(MerchantAuthentication authentication) {
		return merchantMapper.insertMerchantAuthentication(authentication);
	}

	/**
	 * 删除商家图片
	 */
	@Transactional
	public void deletePhoto(int id) {
		merchantMapper.deletePhoto(id);
		attachmentService.updateTemporary(true, id);
	}

	@Transactional
	public int insertPhoto(MerchantPhoto photo) {
		attachmentService.updateTemporary(false, photo.getImage());
		return merchantMapper.insertPhoto(photo);
	}

	@Transactional
	public PageInfo<MerchantPhoto> photos(BaseApiLookup lookup) {
		startPage(lookup);
		List<MerchantPhoto> datas = merchantMapper.merchantPhotos(lookup);
		return PageInfo.instance(datas);
	}

	/** 获取商户信息 */
	public Merchant getMerchantById(int merchantId) {
		return merchantMapper.findMerchantById(merchantId);
	}

	/** 根据企业名称查询商户是否存在*/
	public boolean merchantByName(String merchantName) {
		return merchantMapper.findMerchantByName(merchantName);
	}

	/** 根据手机号码查询商户是否存在 */
	public boolean merchantByMobile(String merchantMobile) {
		return merchantMapper.findMerchantByMobile(merchantMobile);
	}

	/** 商家交通信息列表 */
	public PageInfo<MerchantTraffic> traffics(BaseApiLookup lookup) {
		startPage(lookup);
		List<MerchantTraffic> datas = merchantMapper.traffics(lookup);
		return PageInfo.instance(datas);
	}

	/** 新增商家交通信息 */
	public int insertTraffic(MerchantTraffic traffic) {
		return merchantMapper.insertTraffic(traffic);

	}

	/** 删除商家交通信息 */
	public void deleteTraffic(int id) {
		merchantMapper.deleteTraffic(id);
	}

	/**
	 * 生成商户收款二维码
	 */
	public void generateQrcode(final Merchant merchant) {

		logger.info("start 生成商户收款二维码{}", merchant.getName());
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					final String filePath = "qrcode";
					Map<String, Object> contentMap = new HashMap<String, Object>();
					contentMap.put("merchantId", merchant.getId());
					contentMap.put("merchantName", merchant.getName());
					File f = QRCodeUtil.getQrCode(filePath, CommonUtils.toJson(contentMap));
					// 上传到附件项目
					AjaxResponse response = attachmentService.upload(f.getName(), new FileInputStream(f),
							AttachmentModuleEnum.QRCODE.getModule() + "");
					if (response != null && response.getCode() == 0) {
						int qrcode = response.getId();
						logger.info("生成商户收款二维码 qrcode= {}", qrcode);
						attachmentService.updateTemporary(false, qrcode);
						// 修改商户收款二维码
						Merchant m = new Merchant();
						m.setId(merchant.getId());
						m.setQrcode(qrcode);
						merchantMapper.updateMerchant(m);
						f.delete();
					}
				} catch (Exception e) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
			}

		}).start();

	}

	/**
	 * 查询省市区
     *
     * @author 杜家川
     * @return areas(三级联动后的集合)
	 */
	public List<Area> selectAllCities(List<Area> cities) {

		List<Area> areas = new ArrayList<Area>();

		// zero
		for (int i = 0; i < cities.size(); i++) {
			if (cities.get(i).getLevelType() == 0) {
				areas.add(cities.get(i));
			}
		}

		// first
		for (int i = 0; i < areas.size(); i++) {

			for (Area citiy : cities) {
				if (citiy.getParentId() == areas.get(i).getId()) {
					if (areas.get(i).getArea() == null) {
						areas.get(i).setArea(new ArrayList<Area>());
					} else {
						areas.get(i).getArea().add(citiy);
					}
				}
			}

		}

		// second
		for (int i = 0; i < areas.size(); i++) {

			for (int j = 0; j < areas.get(i).getArea().size(); j++) {
				for (Area citiy : cities) {
					if (citiy.getParentId() == areas.get(i).getArea().get(j).getId()) {
						if (areas.get(i).getArea().get(j).getArea() == null) {
							areas.get(i).getArea().get(j).setArea(new ArrayList<Area>());
						} else {
							areas.get(i).getArea().get(j).getArea().add(citiy);
						}
					}
				}
			}
		}

		// third
		for (int i = 0; i < areas.size(); i++) {

			for (int j = 0; j < areas.get(i).getArea().size(); j++) {

				for (int k = 0; k < areas.get(i).getArea().get(j).getArea().size(); k++) {
					for (Area citiy : cities) {
						if (citiy.getParentId() == areas.get(i).getArea().get(j).getArea().get(k).getId()) {
							if (areas.get(i).getArea().get(j).getArea().get(k).getArea() == null) {
								areas.get(i).getArea().get(j).getArea().get(k).setArea(new ArrayList<Area>());
							} else {
								areas.get(i).getArea().get(j).getArea().get(k).getArea().add(citiy);
							}
						}
					}
				}
			}
		}

		return areas;
	}

	/**
	 * 查询经营品类
     *
     * @author 杜家川
     * @return categorys(三级联动后的集合)
	 */
	public List<BusinessCategory> selectAllCategory(List<BusinessCategory> categoryList) {

		List<BusinessCategory> categorys = new ArrayList<BusinessCategory>();

		// first
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getLevel() == 1) {
				categorys.add(categoryList.get(i));
			}
		}

		// second
		for (int i = 0; i < categorys.size(); i++) {
			for (BusinessCategory category : categoryList) {
				if (category.getParentId() == categorys.get(i).getId()) {
					if (categorys.get(i).getCategorys() == null) {
						categorys.get(i).setCategorys(new ArrayList<BusinessCategory>());
					} else {
						categorys.get(i).getCategorys().add(category);
					}
				}
			}
		}

//		for (int i = 0; i < categorys.size(); i++) {
//			for (int j = 0; j < categorys.get(i).getCategorys().size(); j++) {
//				for (BusinessCategory category : categoryList) {
//					if (category.getParentId() == categorys.get(i).getCategorys().get(j).getId()) {
//						if (categorys.get(i).getCategorys().get(j).getCategorys() == null) {
//							categorys.get(i).getCategorys().get(j).setCategorys(new ArrayList<BusinessCategory>());
//						} else {
//							categorys.get(i).getCategorys().get(j).getCategorys().add(category);
//						}
//					}
//				}
//			}
//		}

		return categorys;
	}

	/**
	 * 验证图形码
     *
     * @author 杜家川
     * @param picCode 前台传过来的图形码
     * @param picValue redis中的图形码
     * @return ture(验证成功) false(不成功)
	 */
	public boolean picCodeValidate(String picCode, String picValue) {
		if (!StringUtils.equalsIgnoreCase(picValue, picCode)) {
			return false;
		}
		return true;
	}

	/**
	 * 验证手机验证码
     *
     * @author 杜家川
     * @param smsCode 前台传过来的手机验证码
     * @param smsValue redis中的手机验证码
     * @return ture(验证成功) false(不成功)
	 */
	public boolean smsCodeValidate(String smsValue, String smsCode) {
		if (!StringUtils.equalsIgnoreCase(smsValue, smsCode)) {
			return false;
		}
		return true;
	}

}
