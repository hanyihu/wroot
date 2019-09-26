package com.vic.ck.console.platform.controller;



import com.alibaba.fastjson.JSON;
import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.platform.service.PlatformCommodityCategoryService;
import com.vic.ck.entity.PlatformCategoryAdPosition;
import com.vic.ck.entity.PlatformCommodityCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;


/*
* 商品分类
* */
@Controller
@RequestMapping("/console/platform/commodityCategory")
public class PlatformCommodityCategoryController extends BaseConsoleController {

     @Resource
     private PlatformCommodityCategoryService platformCommodityCategoryService;

    @ModelAttribute
    private void bindSelect(Model model) {
     // model.addAttribute("cityList", sysAreaService.opendCityList());
        model.addAttribute("categoryList", platformCommodityCategoryService.categoryList(null));
    }

    /**列表页*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page(Model model) {
        PageInfo<PlatformCommodityCategory> pager =  platformCommodityCategoryService.list(getLookup());
        model.addAttribute("pager",pager);
        return "console/platform/commodityCategory/list";
    }

    /**查询*/
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(CommonLookup lookup) {
        setLookup(lookup);
        return "redirect:/console/platform/commodityCategory/";
    }

    /**前往新增页面*/
    @RequestMapping( value = "/0", method = RequestMethod.GET)
    public String add(Model model){
        return "console/platform/commodityCategory/form";
    }

    /**前往修改页面*/
    @RequestMapping( value = "/{id}", method = RequestMethod.GET)
    public String editor(@PathVariable int id, Model model){
        PlatformCommodityCategory entity = platformCommodityCategoryService.findById(id);
        model.addAttribute("entity", entity);
        return "console/platform/commodityCategory/form";
    }

    /**保存*/
    @RequestMapping( value = "/{id}", method = RequestMethod.POST)
    public String save(@PathVariable int id, PlatformCommodityCategory entity, RedirectAttributes attr){
        entity.setId(id);
        if(id == 0) {//to add
            platformCommodityCategoryService.insert(entity);
            attr.addFlashAttribute(Remind.success());
        }else if(id > 0 ){ //to update
            platformCommodityCategoryService.update(entity);
            attr.addFlashAttribute(Remind.success());
        }else {
            attr.addFlashAttribute(Remind.danger());
        }
        return "redirect:/console/platform/commodityCategory/";
    }

    /**单个删除*/
    @RequestMapping( value = "/{id}/remove", method = RequestMethod.GET)
    public String delete(@PathVariable int id, RedirectAttributes attributes){
        platformCommodityCategoryService.delete(id);
        attributes.addFlashAttribute(Remind.success());
        return "redirect:/console/platform/commodityCategory/";
    }

    /**批量删除*/
    @RequestMapping( value = "/remove", method = RequestMethod.POST)
    public String batchDelete(int[] ids, RedirectAttributes attributes){
        platformCommodityCategoryService.delete(ids);
        attributes.addFlashAttribute(Remind.success());
        return "redirect:/console/platform/commodityCategory/";
    }

   /**启用禁用*/
    @RequestMapping( value = "/{id}/enabled", method = RequestMethod.GET)
    public String enabled(@PathVariable int id, boolean enabled, RedirectAttributes attributes){
        PlatformCommodityCategory entity = new PlatformCommodityCategory();
        entity.setId(id); entity.setEnabled(enabled);
        platformCommodityCategoryService.update(entity);
        attributes.addFlashAttribute(Remind.success());
        return "redirect:/console/platform/commodityCategory/";
    }


}
