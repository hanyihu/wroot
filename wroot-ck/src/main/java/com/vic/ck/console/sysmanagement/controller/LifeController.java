package com.vic.ck.console.sysmanagement.controller;

import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.sysmanagement.service.LifeService;
import com.vic.ck.entity.SysManagement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/console/sysmanagement/life")
public class LifeController extends BaseConsoleController {
    @Resource
    private LifeService lifeService;

    /**
     * 跳转到生活服务管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model){
        List<SysManagement> list = lifeService.list();
        model.addAttribute("managements",list);
        return "/console/sysmanagement/life/list";
    }

    /**
     * 跳转到骑手端编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String toUpdateLife(@PathVariable("id")Integer id, Model model){
        SysManagement sysManagement = lifeService.findById(id);
        model.addAttribute("management",sysManagement);
        return "/console/sysmanagement/life/updateLife";
    }

    /**
     * 修改骑手端管理信息
     * @param id
     * @param management
     * @param attr
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id")Integer id, SysManagement management, RedirectAttributes attr){
        management.setId(id);
        lifeService.update(management);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/sysmanagement/life/";
    }
}
