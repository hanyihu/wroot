package com.vic.ck.console.scheduling.controller;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.platform.service.OrderService;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.scheduling.lookup.OrderLookup;
import com.vic.ck.console.scheduling.service.PersonnelService;
import com.vic.ck.entity.RiderOrder;
import com.vic.ck.vo.OrderVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @author xbw
 * @time 2019/4/17 15:33
 * 骑手调度中心-订单查询
 */
@Controller
@RequestMapping("/console/scheduling/dispatch")
public class RiderOrderController extends BaseConsoleController {

    @Resource
    PersonnelService personnelService;

    @Resource
    OrderService orderService;

    /**
     * 列表页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String orderQuery(Model model) {
        PageInfo<RiderOrder> riderOrderPageInfo = personnelService.selectAllOrder(getLookup());
        model.addAttribute("orders", riderOrderPageInfo);
        return "console/scheduling/riderOrder/list";
    }

    /**
     * 重写查询条件
     */
    protected Lookup instanceLookup() {
        return new OrderLookup();
    }

    /**
     * 条件查询
     *
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String search(OrderLookup lookup) {
        setLookup(lookup);
        return "redirect:/console/scheduling/dispatch/query";
    }

    /**
     * 详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}/query")
    public String orderDetail(@PathVariable Integer id, Model model) {
        OrderVo orderVos = orderService.orderById(id);
        model.addAttribute("entity", orderVos);
        return "console/scheduling/riderOrder/detail";
    }
}
