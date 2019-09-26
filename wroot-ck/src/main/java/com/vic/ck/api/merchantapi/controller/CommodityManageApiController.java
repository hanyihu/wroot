package com.vic.ck.api.merchantapi.controller;

import com.vic.base.BaseApiController;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchantapi.service.CommodityManageApiService;
import com.vic.ck.console.platform.service.CommodityManageService;
import com.vic.ck.entity.Commodity;
import com.vic.ck.entity.MerchantCorresponding;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author hanyihu
 * 商家端
 *
 */
@Api(tags = "商家端")
@RestController
@RequestMapping(value = "/api/merchantApi")
public class CommodityManageApiController extends BaseApiController {

    @Resource
    private CommodityManageApiService commodityManageApiService;

    @Resource
    private CommodityManageService commodityManageService;

    /*查询商品信息*/
    /**
     * create by: hanyihu
     * description: TODO
     * create time: 2019/4/28 17:33
     * @return
     * @Param: null
     */
    @ApiOperation(value = "商家端 查询商品信息", notes = "商家端 查询商品信息 商家id ， ", produces = "application/josn")
    @RequestMapping(value = "/getCommodity", method = RequestMethod.GET)
    public Object getCommodity(int id, Lookup lookup) {
        PageInfo<Commodity> commodity = commodityManageApiService.findList(id, lookup);
        return resultSuccess(commodity);
    }

    /*商品下架*/
    /**
     * create by: hanyihu
     * description: TODO
     * create time: 2019/4/28 17:33
     *
     * @return
     * @Param: null
     */
    @ApiOperation(value = "商家端 商品下架", notes = "商家端 商品下架 商品id ， ", produces = "application/josn")
    @RequestMapping(value = "/lowerShelf", method = RequestMethod.GET)
    public Object lowerShelf(Commodity entity) {
        //把商品状态变成下架审核中
        entity.setEnabled(2);
        int i = commodityManageApiService.update(entity);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /*商家端 订单统计（今日/历史）*/
    /**
     * create by: hanyihu
     * description: TODO
     * create time: 2019/4/28 17:32
     *
     * @Param: null
     * @return
     */
    /*@ApiOperation(value = "商家端 订单统计（今日/历史）", notes = "商家端 订单统计  商家id ;createTime=1：今日订单   createTime=2：历史订单  接单：orderNum;  完成订单：completeOrder;  取消订单：cancelOrders;" +
            "收益：profit ", produces = "application/josn")
    @RequestMapping(value = "/orderStatistics", method = RequestMethod.GET)
    public Object orderStatistics(int id, int createTime) {
        //id为骑手id， createTime=1为今日统计，否则为历史统计
        OrderStatisticsVo data = commodityManageApiService.orderStatistics(id, createTime);
        return resultSuccess(data);

    }
*/
    /**
     * create by: hanyihu
     * description: TODO
     * create time: 2019/4/28 17:30
     * 添加商品
     * @Param: null
     * @return
     */
    @ApiOperation(value = "添加商品", notes = "商家端 添加商品 ，商家id：merchantID，" +
            "{商品图片：images}，{商品名称：name}，{规格：spec}，{单价：price}，{单位：company}，{库存：stock}" +
            "，{属性：attribute}，{商品详细描述：content}", produces = "application/josn")
    @RequestMapping(value = "/addCommodity",method = RequestMethod.GET)
    @Transactional
    public Object addCommodity(int merchantID, Commodity entity) {

        logger.info("插入前的数据id===={}", entity.getId());
        int i = commodityManageService.insert(entity);
          if(i>0){

              logger.info("刚插入的数据的id为======{}", entity.getId());
              MerchantCorresponding merchantCorresponding = new MerchantCorresponding();
              merchantCorresponding.setCommodityId(entity.getId());
              merchantCorresponding.setMerchantId(merchantID);

              commodityManageApiService.insertCommodity(merchantCorresponding);
              return true;
          }
        return false;
    }

    /**
     * create by: hanyihu
     * description: TODO
     * create time: 2019/4/29 8:51
     * 商家端 商品筛选（在售中，已售罄，已下架）
     * @Param: null
     * @return
     */
    @ApiOperation(value = "商家端 商品筛选（在售中，已售罄，已下架） ", notes = "商品筛选  商家id：id; 类型：type =（1在售中，2已售罄，3已下架）", produces = "application/json")
    @RequestMapping(value = "/screen", method = RequestMethod.GET)
    public Object screen(int id, int type, Lookup lookup) {
        PageInfo<Commodity> data = commodityManageApiService.screen(id, type, lookup);
        return resultSuccess(data);
    }

    /**/

}
