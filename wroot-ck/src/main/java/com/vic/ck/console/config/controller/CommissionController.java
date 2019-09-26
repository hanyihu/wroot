package com.vic.ck.console.config.controller;

import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.config.service.CommissionService;
import com.vic.ck.entity.BasicConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/console/config/commission")
public class CommissionController extends BaseConsoleController {
    @Resource
    private CommissionService commissionService;

    /**
     * 跳转到分销管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model){
        List<BasicConfig> configs = commissionService.list();
        model.addAttribute("configs",configs);
        return "/console/config/commission/list";
    }
    /**
     * 跳转到编辑提现管理页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id")Integer id, Model model){
        BasicConfig config = commissionService.getCommissionById(id);
        model.addAttribute("config",config);
        return "/console/config/commission/getUpdateCommission";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String save(@PathVariable("id")Integer id, BasicConfig config, RedirectAttributes attr){
        config.setId(id);
        commissionService.update(config);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/config/commission/";
    }
}
