package com.vic.ck.console.platform.controller;

import com.vic.base.model.BaseModel;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.platform.lookup.CommodityManageLookup;
import com.vic.ck.console.platform.service.CommodityManageService;
import com.vic.ck.console.platform.service.PlatformCommodityCategoryService;
import com.vic.ck.entity.Commodity;
import com.vic.wroot.common.po.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hanyihu
 * @date 2019/4/17 10:51
 */
/**
 * 商品管理
 * 
 */
@Controller
@RequestMapping("/console/platform")
public class CommodityManageController extends BaseConsoleController {


    @Resource
    private CommodityManageService commodityManageService;


    @Resource
	private PlatformCommodityCategoryService platformCommodityCategoryService;


	@ModelAttribute
	private void bindSelect(Model model) {
		//一级分类

		model.addAttribute("categoryList", platformCommodityCategoryService.categoryList(null));

	}
	
	//二级分类
	@RequestMapping(value = "/commodity/subCategoryList", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse subCategoryList(@RequestParam(name="id")Integer pid){
		List<BaseModel> data = platformCommodityCategoryService.categoryList(pid);
		return BaseResponse.success(data);
	}

    /**列表页*/
	@RequestMapping(value = "/commodity/", method = RequestMethod.GET)
	public String page(Model model) {
		CommodityManageLookup lookup =(CommodityManageLookup) getLookup();

        PageInfo<Commodity> pager = commodityManageService.list(lookup);
        model.addAttribute("pager", pager);
		return "console/platform/commodityManage/list";
	}
	
	/**重写查询条件*/
	@Override
	protected Lookup instanceLookup() {
		return new CommodityManageLookup();
	}
	
	/**查询*/
	@RequestMapping(value = "/commodity/", method = RequestMethod.POST)
	public String search(CommodityManageLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/platform/commodity/";
	}
	
	/**前往新增页面*/
	@RequestMapping( value = "/commodity/0", method = RequestMethod.GET)
	public String add(Model model){
		Commodity entity = new Commodity();

		model.addAttribute("entity", entity);
		return "console/platform/commodityManage/form";
	}
	
	/**前往修改页面*/
	@RequestMapping( value = "/commodity/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model){
        Commodity entity = commodityManageService.findById(id);
        model.addAttribute("entity", entity);
		return "console/platform/commodityManage/form";
	}
	
	/**保存*/
	@RequestMapping( value = "/commodity/{id}", method = RequestMethod.POST)
	public String save(@PathVariable int id, Commodity entity, RedirectAttributes attr){
		entity.setId(id);
		if(id == 0) {//to add
            commodityManageService.insert(entity);
            attr.addFlashAttribute(Remind.success());
		}else if(id > 0 ){ //to update
            commodityManageService.update(entity);
            attr.addFlashAttribute(Remind.success());
		}else {
			attr.addFlashAttribute(Remind.danger());
		}
		return "redirect:/console/platform/commodity/";
	}
	
	/**单个删除*/
	@RequestMapping( value = "/commodity/{id}/remove", method = RequestMethod.GET)
	public String delete(@PathVariable int id, RedirectAttributes attributes){
        commodityManageService.delete(id);
        attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/commodity/";
	}

	/**批量删除*/
	@RequestMapping( value = "/commodity/remove", method = RequestMethod.POST)
	public String batchDelete(int[] ids, RedirectAttributes attributes){
        commodityManageService.delete(ids);
        attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/platform/commodity/";
	}

    /*下架商品*/
    @RequestMapping(value = "/commodity/{id}/lowerShelf", method = RequestMethod.GET)
    public String lowerShelf(@PathVariable int id, RedirectAttributes attributes, Commodity entity) {
        //把商品状态变成下架审核中
        entity.setId(id);
        entity.setEnabled(2);
        commodityManageService.update(entity);
        attributes.addFlashAttribute(Remind.success());
        return "redirect:/console/platform/commodity/";
    }


}
