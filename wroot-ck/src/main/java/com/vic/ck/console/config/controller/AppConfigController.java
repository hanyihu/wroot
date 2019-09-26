package com.vic.ck.console.config.controller;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.config.lookup.AppConfigLookup;
import com.vic.ck.console.config.service.AppConfigService;
import com.vic.ck.entity.BasicConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * 小程序管理控制器
 * @author houhaoran
 */
@Controller
@RequestMapping("/console/config/appConfig")
public class AppConfigController extends BaseConsoleController {
    @Resource
    private AppConfigService appConfigService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model){
        PageInfo<BasicConfig> pager = appConfigService.list(getLookup());
        model.addAttribute("pager",pager);
        return "/console/config/appConfig/list";
    }
    /**
     * 重写查询条件
     * @return
     */
    protected Lookup instanceLookup(){
        return new AppConfigLookup();
    }

    /**
     * 查询
     * @param lookup 查询条件
     * @return 重定向到小程序管理列表页
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(AppConfigLookup lookup){
        setLookup(lookup);
        return "redirect:/console/config/appConfig/";
    }

    /**
     * 跳转到新增小程序管理页面
     * @param model
     * @return 小程序管理新增页面
     */
    @RequestMapping(value = "/update/0", method = RequestMethod.GET)
    public String add(Model model){
        return "console/config/appConfig/getUpdateAppConfig";
    }

    /**
     * 跳转到修改公告管理页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model){
            BasicConfig config = appConfigService.getAppConfigById(id);
            model.addAttribute("config",config);
        return "console/config/appConfig/getUpdateAppConfig";
    }
    /**
     * 保存新增/修改的小程序信息
     * @return 重定向到列表页面
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String save(@PathVariable("id") Integer id, BasicConfig config, RedirectAttributes attr){
            config.setId(id);
            if(id == 0){//insertPage
                appConfigService.insert(config);
                attr.addFlashAttribute(Remind.success());
            }else if(id > 0){//updatePage
                appConfigService.update(config);
                attr.addFlashAttribute(Remind.success());
            }
        return "redirect:/console/config/appConfig/";
    }

    /**
     * 单个删除
     * @param id
     * @param attr
     * @return
     */
    @RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id")Integer id, RedirectAttributes attr){
        appConfigService.delete(id);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/appConfig/";
    }

    /**
     * 批量删除
     * @param ids
     * @param attr
     * @return
     */
    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public String batchDelete(int[] ids,RedirectAttributes attr){
        appConfigService.delete(ids);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/appConfig/";
    }
}
