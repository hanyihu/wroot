package com.vic.ck.other.recommend;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vic.ck.api.handler.conver.MutliAttachmentConver;
import com.vic.ck.api.handler.conver.SingleAttachmentConver;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.entity.Customer;
import com.vic.wroot.common.util.JedisUtil;

/**
 * 推荐注册相关
 * @author VIC
 *
 */
@Controller
@RequestMapping("/other/recommend")
public class RecommendController extends BaseConsoleController{

	@Resource
	private AuthService authService;
	
	
	/**
	 * 前往推荐注册页面
	 */
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String recommend(Model model) {
		model.addAttribute("errorMsg", "");
		return "other/recommend/register";
	}
	
	/**
	 * 推荐注册
	 */
	@RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
	public String recommend( String mobile, String password, String smscode,@RequestParam(defaultValue="") String recommend,Model model
			) {
		//1 判断验证码
		String code = JedisUtil.getObject(mobile) + "";
		if(!StringUtils.equalsIgnoreCase(code, smscode)){
			model.addAttribute("errorMsg", "验证码错误");
			return "other/recommend/register";
		}
		Customer c = authService.findCustomerByMobile(mobile);
		if(c != null) {
			model.addAttribute("errorMsg", "当前手机号已经注册，无需重新注册");
			return "other/recommend/register";
		}
		Customer customer = authService.register(mobile, password, recommend);
		if(customer != null) {
			return "redirect:/other/recommend/download";
		}else {
			model.addAttribute("errorMsg", "注册失败");
			return "other/recommend/register";
		}
		
	}
	
	/**
	 * 前往下载页面
	 */
	@RequestMapping(value = {"/download"}, method = RequestMethod.GET)
	public String recommend() {
		return "other/recommend/download";
	}
	
	/**
	 * 前往协议页面
	 */
	@RequestMapping(value = {"/protocol"}, method = RequestMethod.GET)
	public String protocol() {
		return "other/recommend/protocol";
	}
	
	@RequestMapping(value = {"/test"}, method = RequestMethod.GET)
	@ResponseBody
	public Object test(@RequestParam(defaultValue="") String recommend) {
		Test t = new Test(1, 2, "3,4,5",recommend);
		return t;
	}
	
}


class  Test {
	
	@JsonSerialize(using=SingleAttachmentConver.class)
	private int id;
	@JsonSerialize(using=SingleAttachmentConver.class)
	private Integer icon;
	@JsonSerialize(using=MutliAttachmentConver.class)
	private String images;
	
	private String recommend;
	
	

	public Test(int id, Integer icon, String images, String recommend) {
		super();
		this.id = id;
		this.icon = icon;
		this.images = images;
		this.recommend =recommend;
	}
	
	public String[] getImage(){
		return images.split(",");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	
	
	
}
