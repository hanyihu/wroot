package com.vic.ck.console.sysmanagement.controller;

import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.sysmanagement.service.IntegralService;
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
@RequestMapping(value = "/console/sysmanagement/integral")
public class IntegralController extends BaseConsoleController {
    @Resource
    private IntegralService integralService;

    /**
     * 跳转到积分管理列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model){
        List<SysManagement> managements = integralService.list();
        model.addAttribute("managements",managements);
        return "/console/sysmanagement/integral/list";
    }

    /**
     * 跳转到积分管理编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String toUpdateLife(@PathVariable("id")Integer id, Model model){
        SysManagement sysManagement = integralService.findById(id);
        model.addAttribute("management",sysManagement);
        return "/console/sysmanagement/integral/updateIntegral";
    }

    /**
     * 修改积分管理信息
     * @param id
     * @param management
     * @param attr
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id")Integer id, SysManagement management, RedirectAttributes attr){
        management.setId(id);
        integralService.update(management);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/sysmanagement/integral/";
    }
}
