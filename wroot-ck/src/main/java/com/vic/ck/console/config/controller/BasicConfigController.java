package com.vic.ck.console.config.controller;

import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.config.service.BasicConfigService;
import com.vic.ck.entity.BasicConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基础配置控制器
 * @author houhaoran
 */
@Controller
@RequestMapping(value = "/console/config/basicConfig")
public class BasicConfigController extends BaseConsoleController {
    private static Log log = LogFactory.getLog(BasicConfigController.class);
    @Resource
    private BasicConfigService basicConfigService;

    /**
     * 查询所有基础配置
     * @param model
     * @return 基础配置页面
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String page(Model model){
        try{
            List<BasicConfig> list = basicConfigService.list();
            model.addAttribute("configs",list);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "console/config/basicConfig/list";
    }

    /**
     * 跳转到新增基础配置页面
     * @return 新增基础配置页面
     */
    @RequestMapping(value = "/update/0",method = RequestMethod.GET)
    public String insertPage(){
        return "console/config/basicConfig/getUpdateBasicConfig";
    }

    /**
     * 跳转到编辑基础配置页面
     * @param id 基础配置id
     * @param model
     * @return 编辑基础配置页面
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String updatePage(@PathVariable("id") Integer id, Model model){
        try{
            BasicConfig basicConfig = basicConfigService.getBasicConfigById(id);
            model.addAttribute("config",basicConfig);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "console/config/basicConfig/getUpdateBasicConfig";
    }

    /**
     * 保存新增/修改的基础配置信息
     * @return 重定向到列表页面
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String save(@PathVariable("id") Integer id, BasicConfig config, RedirectAttributes attr){
        try{
            config.setId(id);
            if(id == 0){//insertPage
                basicConfigService.insertBasicConfig(config);
                attr.addFlashAttribute(Remind.success());
            }else if(id > 0){//updatePage
                basicConfigService.updateBasicConfig(config);
                attr.addFlashAttribute(Remind.success());
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "redirect:/console/config/basicConfig/";
    }

    /**
     * 单个删除
     * @param id
     * @param attr
     * @return 重定向到列表页面
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id, RedirectAttributes attr){
        basicConfigService.deleteBasicConfig(id);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/basicConfig/";
    }

    /**
     * 批量删除
     * @param ids
     * @param attr
     * @return 重定向到列表页面
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String batchDelete(int[] ids, RedirectAttributes attr){
        basicConfigService.deleteBasicConfig(ids);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/basicConfig/";
    }

}
