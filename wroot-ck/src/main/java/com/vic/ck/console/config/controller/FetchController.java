package com.vic.ck.console.config.controller;

import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.config.service.FetchService;
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
 * 提现管理控制器
 */
@Controller
@RequestMapping(value = "/console/config/fetch")
public class FetchController extends BaseConsoleController {
    @Resource
    private FetchService fetchService;

    /**
     * 跳转到提现管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model){
        List<BasicConfig> configs = fetchService.list();
        model.addAttribute("configs",configs);
        return "/console/config/fetch/list";
    }

    /**
     * 跳转到编辑提现管理页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id")Integer id, Model model){
        BasicConfig config = fetchService.getFetchById(id);
        model.addAttribute("config",config);
        return "/console/config/fetch/getUpdateFetch";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String save(@PathVariable("id")Integer id, BasicConfig config, RedirectAttributes attr){
        config.setId(id);
        fetchService.update(config);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/fetch/";
    }

}
