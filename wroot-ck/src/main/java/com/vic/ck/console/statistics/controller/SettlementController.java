package com.vic.ck.console.statistics.controller;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.statistics.lookup.OrderStatisticsLookup;
import com.vic.ck.console.statistics.service.SettlementService;
import com.vic.ck.entity.Settlement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/*
* 结算统计
* */
@Controller
@RequestMapping("/console/statistics/settlement")
public class SettlementController extends BaseConsoleController {

    @Resource
    private SettlementService settlementService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page(Model model) {
        PageInfo<Settlement> pager = settlementService.list(getLookup());
        model.addAttribute("pager", pager);
        return "console/statistics/settlement/list";
    }

/**重写查询条件*/

	protected Lookup instanceLookup() {
		return new OrderStatisticsLookup();
	}


/** 查询 */

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(OrderStatisticsLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/statistics/settlement/";
	}



}
