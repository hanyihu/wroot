package com.vic.ck.api.merchant.controller;

import com.vic.base.BaseApiController;
import com.vic.base.BaseApiLookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchant.service.MerchantService;
import com.vic.ck.entity.MerchantPhoto;
import com.vic.wroot.common.po.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 五 商家端 2 相册相关
 * @author VIC
 *
 */
@Api(tags = "商家端相册")
@RestController
@RequestMapping("/api/merchant/photo")
public class MerchantPhotoController extends BaseApiController{

    @Resource
    private MerchantService merchantService;

    /**
     * 5.10 商家相册列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping( value="/photos", method = RequestMethod.GET)
    @ApiOperation(value = "相册列表", notes = "获取商家的相册列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PageInfo<MerchantPhoto>> authentication (BaseApiLookup lookup) {
        PageInfo<MerchantPhoto> data = merchantService.photos(lookup);
        return resultSuccess(data);
    }

    /**
     * 5.11 新增相册图片
     */
    @RequestMapping( value="/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增相册图片", notes = "新增选中相册的图片", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object add(
            @RequestParam @ApiParam(value = "商家ID", required = true) int merchantId,
            @RequestParam @ApiParam(value = "图像ID", required = true) int image,
            @RequestParam(defaultValue="1") @ApiParam(value = "类型(1-外景 2-内景 3-其他 4-酒店)")int type ) {
        merchantService.insertPhoto(new MerchantPhoto(merchantId, image, type)	);
        return resultSuccess();
    }

    /**
     * 5.12 删除相册图片
     */
    @RequestMapping( value="/{id}/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除相册图片", notes = "删除选中相册的图片", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@PathVariable int id) {
        merchantService.deletePhoto(id);
        return resultSuccess();
    }
}
