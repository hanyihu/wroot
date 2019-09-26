package com.vic.ck.api.statistics.controller;

import com.vic.base.BaseApiController;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.statistics.service.SettlementService;
import com.vic.ck.entity.Settlement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*结算统计相关接口*/

@RestController
@RequestMapping("/api/statistics/settlement")
public class SettlementApiController extends BaseApiController {


    @Resource
    private SettlementService settlementService;

    /*结算统计*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object settlement(Lookup lookup)  {
        PageInfo<Settlement> pager = settlementService.list(lookup);

        return resultSuccess(pager);
    }


}
