package com.vic.ck.api.merchant.controller;

import com.vic.base.BaseApiController;
import com.vic.base.BaseApiLookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.ResultMsgEnum;
import com.vic.ck.api.merchant.service.MerchantBalanceService;
import com.vic.ck.api.personal.service.CustomerGroupTicketService;
import com.vic.ck.entity.CustomerGroupTicket;
import com.vic.ck.entity.MerchantBalance;
import com.vic.ck.entity.MerchantBalanceRecord;
import com.vic.ck.vo.CompareBillVo;
import com.vic.wroot.common.po.BaseResponse;
import io.swagger.annotations.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 余额和代金券相关
 * 5-2
 *
 * @author VIC
 */
@Api(tags = "商家端余额和代金券")
@RestController
@RequestMapping("/api/merchant/money")
public class MerchantMoneyController extends BaseApiController {


    @Resource
    private MerchantBalanceService merchantBalanceService;


    @Resource
    private CustomerGroupTicketService customerGroupTicketService;

    @Override
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 5.2-01 对账
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/bill", method = RequestMethod.GET)
    @ApiOperation(value = "商家对账", notes = "获取商家的对账信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object compareBill(
            @RequestParam @ApiParam(value = "商家ID", required = true) int merchantId,
            @RequestParam @ApiParam(value = "开始时间（yyyy-MM-dd）", required = true) Date startDate,
            @RequestParam @ApiParam(value = "结束时间（yyyy-MM-dd）", required = true) Date endDate) {
        CompareBillVo data = merchantBalanceService.findMerchantBill(merchantId, startDate, endDate);
        return resultSuccess(data);
    }

    /**
     * 5.2.02 商户余额
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    @ApiOperation(value = "商户余额", notes = "获取商家的余额信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object balance(
            @RequestParam @ApiParam(value = "商家ID", required = true) int merchantId) {
        MerchantBalance b = merchantBalanceService.getMerchantBalance(merchantId);
        return resultSuccess(b == null ? 0 : b.getBalance());
    }

    /**
     * 5.2-03 余额明细
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/balances", method = RequestMethod.GET)
    @ApiOperation(value = "余额明细", notes = "获取商家的余额明细列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object balances(BaseApiLookup lookup) {
        PageInfo<MerchantBalanceRecord> data = merchantBalanceService.balanceRecords(lookup);
        return resultSuccess(data);
    }

    /**
     * 5.2-04 验证团购券
     */
    @RequestMapping(value = "/checkTicket", method = RequestMethod.POST)
    @ApiOperation(value = "验证团购券", notes = "验证所使用的团购卷并修改团购卷和订单的状态", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 813, message = "不存在的团购券"),
            @ApiResponse(code = 906, message = "团购券已使用"),
            @ApiResponse(code = 1005, message = "团购券不属于本商户")
    })
    public Object checkGroupTicket(
            @RequestParam @ApiParam(value = "商家ID", required = true) int merchantId,
            @RequestParam @ApiParam(value = "团购券码", required = true) String ticketNo) {
        CustomerGroupTicket ticket = customerGroupTicketService.findByTicketNo(ticketNo);
        if (ticket == null) {
            return resultError(ResultMsgEnum.NOTEXISTED_GROUP_TICKET);
        }
        if (ticket.getStatus() != 0) {
            return resultError(ResultMsgEnum.ALREADY_USED_GROUP_TICKET);
        }
        if (ticket.getMerchantId() != merchantId) {
            return resultError(ResultMsgEnum.GROUP_TICKET_NOT_MATCH);
        }

        //过期情况暂时不加验证

        //使用团购券 修改订单状态
        customerGroupTicketService.useGroupTicket(ticket);
        return resultSuccess();
    }

    /**
     * 5.2-05 已经验证的团购券列表
     *
     * @param lookup
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/groupTickets", method = RequestMethod.GET)
    @ApiOperation(value = "已经验证的团购券列表", notes = "获取已经验证过的商家团购券列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PageInfo<CustomerGroupTicket>> groupTickets(BaseApiLookup lookup) {
        PageInfo<CustomerGroupTicket> data = customerGroupTicketService.list(lookup);
        return resultSuccess(data);
    }

    /**
     * 5.2-06 删除团购券
     */
    @RequestMapping(value = "/groupTicket/{id}/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除团购券", notes = "删除团购券", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object deleteGroupTicket(@PathVariable @ApiParam(value = "团购券ID", required = true) int id) {
        customerGroupTicketService.merchantDelete(id);
        return resultSuccess();
    }


}
