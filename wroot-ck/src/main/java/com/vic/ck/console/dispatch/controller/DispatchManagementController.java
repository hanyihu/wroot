package com.vic.ck.console.dispatch.controller;

import com.vic.base.pager.CommonLookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.dispatch.service.DispatchManagementService;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.RiderTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hanyihu
 * @date 2019/4/17 10:47
 *
 * 骑手端调度中心 调度管理
 */
@Controller
@RequestMapping("/console/scheduling/dispatch/management")
public class DispatchManagementController extends BaseConsoleController {

   @Resource
   private DispatchManagementService dispatchManagementService;

    /**列表页*/
   /* @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page(Model model) {
        PageInfo<RiderTask> pager =  dispatchManagementService.list(getLookup());
        model.addAttribute("pager",pager);
        return "console/dispatch/management/list";
    }*/

   /*列表页*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page(Model model) {
        PageInfo<RiderTask> pager =  dispatchManagementService.manageList(getLookup());
        model.addAttribute("pager",pager);
        return "console/dispatch/management/list";
    }

    /**查询*/
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(CommonLookup lookup) {
        setLookup(lookup);
        return "redirect:/console/dispatch/management/";
    }

    /*查询详情*/
    @RequestMapping(value = "/{id}/riderDetail", method = RequestMethod.GET)
    public String riderDetail(@PathVariable int id, Model model) {
        List<Customer> pager =  dispatchManagementService.customerList(id);

        model.addAttribute("pager",pager);
        return "console/dispatch/management/rider_detail";
    }


    /**重写查询条件*/
   /* @Override
    protected Lookup instanceLookup() {
        return new CommodityLookup();
    }*/
    /*计算商家与骑手的距离*/
   /* @RequestMapping(value = "/distance/{merchantId}",method = RequestMethod.GET)
    public String distance(@PathVariable  int merchantId ,Model model){
       List<Customer> pager = dispatchManagementService.distance(merchantId);
        System.out.println("骑手信息+"+ JSON.toJSONString(pager));
        model.addAttribute("pager",pager);
        return "console/dispatch/management/distance";
    }*/


}
