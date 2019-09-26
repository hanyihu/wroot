package com.vic.ck.console.system.area.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.model.BaseModel;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.SysArea;
import com.vic.wroot.common.po.BaseResponse;



/**
 * 区域
 * 
 */
@Controller
@RequestMapping("/console/system/area")
public class SysAreaController extends BaseConsoleController{
	

	@Resource
	private SysAreaService sysAreaService;
	
    /**列表页*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<SysArea> pager =  sysAreaService.list(getLookup());
		model.addAttribute("pager",pager);
		model.addAttribute("provinces", sysAreaService.provinces());
		return "console/system/area/list";
	}
	
	/**重写查询条件*/
	protected Lookup instanceLookup() {
		return new SysAreaLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(SysAreaLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/system/area/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/0", method = RequestMethod.GET)
	public String add(Model model){
		return "console/system/area/form"; 
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
		SysArea entity = sysAreaService.findById(id);
		model.addAttribute("entity", entity);
		return "console/system/area/form"; 
	}
	
	/**保存*/
	@RequestMapping( value = "/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, SysArea entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
			sysAreaService.insert(entity);
			attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
			sysAreaService.update(entity);
			attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/system/area/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
		sysAreaService.delete(id);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/system/area/";
	}
	
	/**批量删除*/
	@RequestMapping( value = "/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
		sysAreaService.delete(ids);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/system/area/";
	}
	
	/**设为热门或者非热门*/
	@RequestMapping( value = "/{id}/hot", method = RequestMethod.GET)
	public String hot(@PathVariable int id, SysArea entity, RedirectAttributes attr) {
		entity.setId(id);
		sysAreaService.update(entity);
		attr.addFlashAttribute(Remind.success());
		return "redirect:/console/system/area/";
	}
	
	/**关闭或者开放城市*/
	@RequestMapping( value = "/{id}/opened", method = RequestMethod.GET)
	public String opened(@PathVariable int id, SysArea entity, RedirectAttributes attr) {
		entity.setId(id);
		sysAreaService.update(entity);
		attr.addFlashAttribute(Remind.success());
		return "redirect:/console/system/area/";
	}
	
	/**
	 * 获得子区域
	 */
	@RequestMapping(value = "/children")
	@ResponseBody
	public BaseResponse findChildren(@RequestParam int id){
		List<BaseModel> list= sysAreaService.findChildren(id);
		return BaseResponse.success(list);
	}
	
	
}
