package com.vic.ck.console.scheduling.controller;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.scheduling.lookup.PersonnelLookup;
import com.vic.ck.console.scheduling.service.PersonnelService;
import com.vic.ck.console.system.area.service.SysAreaService;
import com.vic.ck.entity.RiderPersonnel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 骑手调度中心-人员管理
 */
@Controller
@RequestMapping("/console/scheduling/dispatch")
public class DispatchCenterController extends BaseConsoleController {

    @Resource
    private PersonnelService personnelService;

    @Resource
    private SysAreaService sysAreaService;


    /**
     * 人员管理列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "/personnel",method = RequestMethod.GET)
    public String personnel(Model model){
        PageInfo<RiderPersonnel> riderPersonnels = personnelService.selectRiderPersonnel(getLookup());
        model.addAttribute("personnel", riderPersonnels);
        return "console/scheduling/personnel/list";
    }

    /** 重写查询条件 */
    protected Lookup instanceLookup() {
        return new PersonnelLookup();
    }
    /** 查询 */
    @RequestMapping(value = "/personnel", method = RequestMethod.POST)
    public String search(PersonnelLookup lookup) {
        setLookup(lookup);
        return "redirect:/console/scheduling/dispatch/personnel";
    }

    /**
     * 详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String details(@PathVariable String id,Model model){
        RiderPersonnel riderPersonnel = personnelService.selectById(Integer.parseInt(id));
        model.addAttribute("entity",riderPersonnel);
        return "console/scheduling/personnel/detail";
    }
}
