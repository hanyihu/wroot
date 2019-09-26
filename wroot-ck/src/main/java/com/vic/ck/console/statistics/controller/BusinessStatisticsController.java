package com.vic.ck.console.statistics.controller;

import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.statistics.service.ReportOrderService;
import com.vic.ck.entity.BusinessStatistics;
import com.vic.ck.entity.MemberStatistics;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商家数据统计
 */
@Controller
@RequestMapping("/console/statistics/summary")
public class BusinessStatisticsController extends BaseConsoleController {

    @Resource
    ReportOrderService reportOrderService;

    /**
     * 商家统计
     * @param date 今日时间
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String businessStatistics(String date, Model model){
        if (date==null){
            if (date==null){
                List<BusinessStatistics> lists=new ArrayList<>();
                String day = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                List<Integer> list = reportOrderService.merchantsWithOrders(day);
                for (int i=0;i<list.size();i++){
                    BusinessStatistics businessStatistics = reportOrderService.selectMerchantsByTime(day,list.get(i));
                    lists.add(businessStatistics);
                }
                model.addAttribute("businessData",lists);
            }
        }else {
            List<BusinessStatistics> arr=new ArrayList<>();
            List<Integer> list = reportOrderService.merchantsWithOrders(date);
            for (int j=0;j<list.size();j++){
                BusinessStatistics businessStatistics = reportOrderService.selectMerchantsByTime(date,list.get(j));
                arr.add(businessStatistics);
            }
            model.addAttribute("businessData",arr);
        }

        return "console/statistics/order/form";
    }

    /**
     * 会员统计
     *
     * @return
     */
    @RequestMapping(value = "/members",method = RequestMethod.GET)
    public String memberStatistics(Model model){
        int type = 9;
        List<MemberStatistics> arr=new ArrayList<>();
        List<Integer> list = reportOrderService.judgeState();
        for (int i=0;i<list.size();i++){
            MemberStatistics memberStatistics1 = reportOrderService.refundAmounts(list.get(i), type);
            MemberStatistics memberStatistics = reportOrderService.selectMembers(list.get(i));
            if (memberStatistics1 == null) {
                memberStatistics.setRefundAmounts(BigDecimal.ZERO);
            } else {
                memberStatistics.setRefundAmounts(memberStatistics1.getRefundAmounts());
            }
            arr.add(memberStatistics);
        }
        model.addAttribute("businessData",arr);

            return "console/statistics/order/members";

    }
}
