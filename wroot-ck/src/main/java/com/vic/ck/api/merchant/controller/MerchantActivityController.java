package com.vic.ck.api.merchant.controller;

import com.vic.base.BaseApiController;
import com.vic.base.BaseApiLookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchant.service.MerchantActivityService;
import com.vic.ck.entity.MerchantActivity;
import com.vic.wroot.common.po.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 五 商家端 3 活动相关
 * @author VIC
 *
 */
@Api(tags = "商家端活动")
@RestController
@RequestMapping("/api/merchant/activity/")
public class MerchantActivityController extends BaseApiController{

    @Resource
    private MerchantActivityService merchantActivityService;

    /**
     * 5.13 营销活动列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping( value = "activities", method = RequestMethod.GET)
    @ApiOperation(value = "营销活动列表", notes = "获取商家的营销活动列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PageInfo<MerchantActivity>> activities(BaseApiLookup lookup) {
        PageInfo<MerchantActivity> data = merchantActivityService.activities(lookup);
        return resultSuccess(data);
    }


    /**
     * 5.14 创建满减活动/折扣活动
     */
    @RequestMapping( value = "add", method = RequestMethod.POST)
    @ApiOperation(value = "创建活动", notes = "创建满减活动或折扣活动", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object addActivity(@RequestBody @ApiParam(value = "商家活动", required = true)  MerchantActivity activity) {
        merchantActivityService.insertActivity(activity);
        return resultSuccess();
    }

    /**
     * 5.15 修改活动
     */
    @RequestMapping( value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "修改活动", notes = "修改满减活动或折扣活动", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object updateActivity(@RequestBody @ApiParam(value = "商家活动", required = true) MerchantActivity activity) {
        merchantActivityService.updateActivity(activity);
        return resultSuccess();
    }

    /**
     * 5.16 删除活动
     */
    @RequestMapping( value = "{id}/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除活动", notes = "删除满减活动或折扣活动", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object deleteActivity(@PathVariable @ApiParam(value = "活动ID", required = true) int id) {
        merchantActivityService.deleteActivity(id);
        return resultSuccess();
    }

    /**
     * 5.17 发布活动
     */
    @RequestMapping( value = "{id}/publish", method = RequestMethod.POST)
    @ApiOperation(value = "发布活动", notes = "发布满减活动或折扣活动", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object publish(@PathVariable @ApiParam(value = "活动ID", required = true) int id) {
        merchantActivityService.updateStatus(id, 1);
        return resultSuccess();
    }

    /**
     * 5.18 关闭活动
     */
    @RequestMapping( value = "{id}/close", method = RequestMethod.POST)
    @ApiOperation(value = "关闭活动", notes = "关闭满减活动或折扣活动", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object close(@PathVariable @ApiParam(value = "活动ID", required = true) int id) {
        merchantActivityService.updateStatus(id, 2);
        return resultSuccess();
    }
}
