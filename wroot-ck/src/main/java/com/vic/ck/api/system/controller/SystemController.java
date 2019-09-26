package com.vic.ck.api.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vic.base.BaseApiController;
import com.vic.base.model.BaseModel;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.platform.service.PlatformCommonService;
import com.vic.ck.api.system.service.SystemService;
import com.vic.ck.console.platform.service.PlatformAppVersionService;
import com.vic.ck.entity.Area;
import com.vic.ck.entity.Banner;
import com.vic.ck.entity.BootPage;
import com.vic.ck.entity.PlatformAppVersion;
import com.vic.ck.entity.PlatformFeedback;
import com.vic.ck.entity.SysConfig;

@RestController
@RequestMapping("/api/system")
public class SystemController extends BaseApiController {

	@Resource
	private SystemService systemService;

	@Resource
	private PlatformCommonService platformCommonService;
	
	@Resource
	private PlatformAppVersionService platformAppVersionService;

	/**
	 * 1.01 可使用银行列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/banks", method = RequestMethod.GET)
	public Object banks() {
		List<BaseModel> data = systemService.banks();
		return resultSuccess(data);
	}

	/**
	 * 1.02 启动页列表
	 */
	@RequestMapping(value = "/bootPages", method = RequestMethod.GET)
	public Object bootPages() {
		List<BootPage> data = systemService.bootPages();
		return resultSuccess(data);
	}

	/**
	 * 所有城市查询
	 */
	@RequestMapping(value = "/allCities", method = RequestMethod.GET)
	public Object allCities() {
		List<Area> data = systemService.allCities();
		return resultSuccess(data);
	}

	
	/**
	 * 1.03 城市匹配查询
	 */
	@RequestMapping(value = "/matchCity", method = RequestMethod.GET)
	public Object matchCity(@RequestParam(defaultValue = "") int id, @RequestParam(defaultValue = "") String name) {
		Area a = systemService.matchCity(id, name);

		if (a == null || !a.isOpened()) {
			return resultError(ResultMsgEnum.UNOPEN_CITY);
		}
		return resultSuccess(a);
	}

	/**
	 * 1.04 热门城市
	 */
	@RequestMapping(value = "/hotCities", method = RequestMethod.GET)
//		public Object hotCities(@RequestParam(defaultValue = "0") int id, @RequestParam(defaultValue = "") String name) {
	public Object hotCities( ) {
		List<Area> data = systemService.hotCities();
		return resultSuccess(data);
	}

	/**
	 * 1.05 已开放城市
	 */
	@RequestMapping(value = "/openedCities", method = RequestMethod.GET)
//		public Object openedCities(@RequestParam(defaultValue = "0") int id, @RequestParam(defaultValue = "") String name) {
	public Object openedCities( ) {
		List<Area> data = systemService.openedCities();
		return resultSuccess(data);
	}

	/**
	 * 1.06 省市区下拉查询
	 */
	@RequestMapping(value = "/selectCities", method = RequestMethod.GET)
	public Object selectCities(@RequestParam(defaultValue = "1") int levelType,
			@RequestParam(defaultValue = "100000") int parentId) {
		if (levelType == 1)
			parentId = 100000;
		List<Area> data = systemService.selectCities(levelType, parentId);
		return resultSuccess(data);
	}

	/**
	 * 1.07 轮播图
	 */
	@RequestMapping(value = "/banners", method = RequestMethod.GET)
	public Object banners(@RequestParam(defaultValue = "1") int type, @RequestParam(defaultValue = "0") int cityId) {
		List<Banner> data = systemService.banners(type, cityId);
		return resultSuccess(data);
	}

	/**
	 * 1.08 提交意见反馈
	 */
	@RequestMapping(value = "/feedback/add", method = RequestMethod.POST)
	public Object addFeedBack(PlatformFeedback feedback) {
		platformCommonService.insertFeedback(feedback);
		return resultSuccess();
	}

	/**
	 * 1.09 关于我们
	 * 
	 * @param type
	 *            1- 客户端 2-商家端
	 */
	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public Object aboutus(@RequestParam(defaultValue = "1") int type) {
		String code = type == 1 ? FinalFiledParams.SYS_CONFIG_ABOUTUS_CUSTOMER
				: (type == 2 ? FinalFiledParams.SYS_CONFIG_ABOUTUS_MERCHANT : "unknow");
		SysConfig config = systemService.getSysConfig(code);
		return resultSuccess(config == null ? "" : config.getContent());
	}

	/**
	 * 1.10 商家分类
	 */
	@RequestMapping(value = "/merchantCategories", method = RequestMethod.GET)
	public Object merchantCategories(@RequestParam(defaultValue = "false") boolean tree) {
		Object resut = tree ? platformCommonService.merchantCategoriesTree()
				: platformCommonService.merchantCategories();
		return resultSuccess(resut);
	}

	/**
	 * 1.11 分享镗镗锣
	 * type 类型0-平台 1-团购 2-商家
	 * 
	 */
	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public Object share(@RequestParam int userId, @RequestParam(defaultValue="0")int type,  @RequestParam(defaultValue="")String target) {
		
		int shareNum = platformCommonService.findSharedNum(userId);
		if(shareNum >= systemService.getSysConfigInt(FinalFiledParams.SYS_CONFIG_SHARED_NUMBER)) {
			return resultSuccess(0);
		}
		int score = platformCommonService.share(userId, type, target);
		return resultSuccess(score);
	}
	
	/**
	 * 1.12 最新版本
	 * @param appType 1-用户端 2-商家端
	 * @param machineType 1-安卓 2-IOS
	 */
	@RequestMapping(value = "/version", method = {RequestMethod.GET, RequestMethod.POST})
	public Object version(@RequestParam(defaultValue="1") int appType, @RequestParam(defaultValue="1")int machineType) {
		PlatformAppVersion data = platformAppVersionService.currentVersion(appType, machineType);
		return resultSuccess(data);
	}
	
	/**
	 * 1.13 联系客服
	 */
	@RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
	public Object contact() {
		String contact = systemService.getSysConfigString(FinalFiledParams.SYS_CONFIG_CONTACT);
		return resultSuccess(contact);
	}

}
