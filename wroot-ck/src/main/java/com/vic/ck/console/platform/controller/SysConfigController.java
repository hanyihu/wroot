package com.vic.ck.console.platform.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.Remind;
import com.vic.ck.api.system.service.SystemService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.entity.SysConfig;
import com.vic.wroot.common.attachment.service.AttachmentService;
import com.vic.wroot.common.po.BaseResponse;

/**
 * APP的一些全局配置
 * @author VIC
 *
 */
@Controller
@RequestMapping(value="/console/platform/config")
public class SysConfigController extends BaseConsoleController{
	
	@Resource
	private SystemService systemService;
	
	@Resource
	private AttachmentService attachmentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		List<SysConfig> datas = systemService.sysConfigs();
		model.addAttribute("datas", datas);
		return "console/platform/config/list";
	}
	
	/**
	 * 异步修改配置
	 */
	@RequestMapping(value = "/async", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse update(SysConfig entity) {
		systemService.updateSysConfig(entity);
		return BaseResponse.success();
	}
	/**
	 * 前往编辑富文本的值
	 */
	@RequestMapping( value = "/{code}/update", method = RequestMethod.GET)
	public String detail(@PathVariable String code, Model model, RedirectAttributes attributes) {
		SysConfig entity = systemService.getSysConfig(code);
		if(entity == null || entity.getType() != 3) {
			attributes.addAttribute(Remind.warning().setMessage("不可编辑"));
			return "redirect:/console/platform/config/";
		}
		model.addAttribute("entity", entity);
		return "console/platform/config/form";
	}
	/**
	 * 同步修改配置
	 */
	@RequestMapping( value = "/{code}/update", method = RequestMethod.POST)
	public String update(SysConfig entity, RedirectAttributes attributes){
		SysConfig old = systemService.getSysConfig(entity.getCode());
		attachmentService.HandleOldAndNowAttachment(old, entity);
		systemService.updateSysConfig(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/config/";
	}
	
}
