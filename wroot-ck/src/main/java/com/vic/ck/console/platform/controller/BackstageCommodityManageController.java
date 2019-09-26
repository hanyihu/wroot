package com.vic.ck.console.platform.controller;

import com.vic.base.model.BaseModel;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.platform.lookup.CommodityManageLookup;
import com.vic.ck.console.platform.service.BackstageCommodityManageService;
import com.vic.ck.console.platform.service.PlatformCommodityCategoryService;
import com.vic.ck.entity.Commodity;
import com.vic.wroot.common.po.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;


/**
 * 商品管理
 * 
 */
@Controller
@RequestMapping("/console/platform")
public class BackstageCommodityManageController extends BaseConsoleController {
	

	@Resource
	private BackstageCommodityManageService backstagecommodityManageService;


	@Resource
	private PlatformCommodityCategoryService platformCommodityCategoryService;


	/*@ModelAttribute*/
	/*private void bindSelect(Model model) {
		//一级分类

		List<BaseModel> baseModels = platformCommodityCategoryService.categoryList(null);
        if (baseModels.isEmpty()){
			System.out.println("11111111122222222222");
		}

		System.out.println("111111111"+baseModels.isEmpty());

		model.addAttribute("categoryList", platformCommodityCategoryService.categoryList(null));

	}
*/
	//二级分类
	@RequestMapping(value = "/backstagecommodity/subCategoryList", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse subCategoryList(@RequestParam(name="id")Integer pid){
		List<BaseModel> data = platformCommodityCategoryService.categoryList(pid);
		return BaseResponse.success(data);
	}

    /**列表页*/
	@RequestMapping(value = "/backstagecommodity/", method = RequestMethod.GET)
	public String page(Model model) {

		CommodityManageLookup lookup =(CommodityManageLookup) getLookup();
		PageInfo<Commodity> pager =  backstagecommodityManageService.list(lookup);
		model.addAttribute("pager",pager);
		return "console/platform/backstagecommodityManage/list";
	}
	
	/**重写查询条件*/
	@Override
	protected Lookup instanceLookup() {
		return new CommodityManageLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/backstagecommodity/", method = RequestMethod.POST)
	public String search(CommodityManageLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/backstagecommodity/";
	}

	

	/*审核通过*/
	@RequestMapping(value = "/backstagecommodity/{id}/examine",method =RequestMethod.GET )
	@Transactional
	public String examinePass(@PathVariable int id,RedirectAttributes attributes,Commodity entity){

		//通过ID判断商品的上下架状态
		Integer enabled = backstagecommodityManageService.findById(id).getEnabled();

		entity.setId(id);
		entity.setExamine(2);//审核通过
		if(enabled==3){
			entity.setEnabled(1);//审核通过时，把商品由待上架变为上架
		}
		else if(enabled==2){
			entity.setEnabled(0);//审核通过时，把商品由待下架变为下架
		}

		backstagecommodityManageService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/backstagecommodity/";
	}

	/*审核不通过*/
	@RequestMapping(value = "/backstagecommodity/{id}/examineNoPass_1",method =RequestMethod.GET )
	public String examineNoPass(@PathVariable int id,Model model){

		Commodity entity = backstagecommodityManageService.findById(id);
		model.addAttribute("entity",entity);
		return "/console/platform/backstagecommodityManage/examineNoPass";
	}

	/*保存审核不通过*/
	@RequestMapping(value = "/backstagecommodity/{id}/examineNoPass_1",method =RequestMethod.POST )
	@Transactional
	public String saveExamineNoPass(@PathVariable int id,RedirectAttributes attributes,Commodity entity){

		entity.setId(id);
		entity.setExamine(1);
		backstagecommodityManageService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/backstagecommodity/";
	}

}
