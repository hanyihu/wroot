package com.vic.ck.api.merchant.controller;

import com.vic.base.BaseApiController;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.platform.lookup.CommodityLookup;
import com.vic.ck.api.platform.service.PlatformCommonService;
import com.vic.ck.vo.CommentVo;
import com.vic.wroot.common.po.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商家对评论的一些处理
 * @author VIC
 *
 */
@Api(tags = "商家端评论")
@RestController
@RequestMapping("/api/merchant/comment")
public class MerchantCommentController extends BaseApiController{


    @Resource
    private PlatformCommonService platformCommonService;

    /**
     *  5.3-01 商家评论列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping( value = "/comments", method =RequestMethod.GET)
    @ApiOperation(value = "商家评论列表", notes = "获取商家的评论列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PageInfo<CommentVo>> comments(CommodityLookup lookup) {
        PageInfo<CommentVo> data = platformCommonService.comments(lookup);
        return resultSuccess(data);
    }
    /**
     * 5.3-02 商家删除评论  没有重新计算评分
     */
    @RequestMapping( value = "/comment/{id}/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除评论", notes = "删除相应的评论", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object deleteComment(@PathVariable @ApiParam(value = "评论ID", required = true) int id) {
        platformCommonService.removeComment(id);
        return resultSuccess();
    }

    /**
     * 5.3-03 商家回复评论
     */
    @RequestMapping( value = "/comment/{id}/reply", method = RequestMethod.POST)
    @ApiOperation(value = "回复评论", notes = "回复相应的评论", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object replyComment(@PathVariable @ApiParam(value = "评论ID", required = true) int id, @RequestParam @ApiParam(value = "回复内容", required = true) String reply) {
        platformCommonService.replyComment(id, reply);
        return resultSuccess();
    }



}
