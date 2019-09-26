package com.vic.ck.console.sysmanagement.controller;

import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.sysmanagement.service.RiderService;
import com.vic.ck.entity.SysManagement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * 骑手端管理控制器
 */
@Controller
@RequestMapping(value = "/console/sysmanagement/rider")
public class RiderController extends BaseConsoleController {
    @Resource
    private RiderService riderService;

    /**
     * 跳转到骑手端管理列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(Model model){
        List<SysManagement> managements = riderService.list();
        model.addAttribute("managements",managements);
        return "/console/sysmanagement/rider/list";
    }

    /**
     * 跳转到骑手端编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String toUpdateRider(@PathVariable("id")Integer id,Model model){
        SysManagement sysManagement = riderService.findById(id);
        model.addAttribute("management",sysManagement);
        return "/console/sysmanagement/rider/updateRider";
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
        riderService.update(management);
        attr.addFlashAttribute(Remind.success());
        return "redirect:/console/sysmanagement/rider/";
    }
}
