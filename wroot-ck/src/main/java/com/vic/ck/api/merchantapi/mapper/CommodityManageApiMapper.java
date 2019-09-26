package com.vic.ck.api.merchantapi.mapper;

import com.vic.base.BaseMapper;
import com.vic.base.pager.Lookup;
import com.vic.ck.entity.Commodity;
import com.vic.ck.entity.MerchantCorresponding;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MybatisMapper
public interface CommodityManageApiMapper extends BaseMapper<Commodity> {

  List<Commodity> findList(@Param("merchantId") int merchantId, Lookup lookup);
/*
  //订单数
  Integer orderNum(@Param("id") int id, @Param("createTime") Integer createTime);

  //完成订单数
  Integer completeOrder(@Param("id") int id, @Param("createTime") Integer createTime);

  //取消订单数
  Integer cancelOrders(@Param("id") int id, @Param("createTime") Integer createTime);

  //收益
  BigDecimal profit(@Param("id") int id, @Param("createTime") Integer createTime);*/

  //商品筛选（在售中，已售罄，已下架）
  List<Commodity> screen(@Param("id") int id, @Param("type") int type, Lookup lookup);

  //插入信息到数据库中
  int insertCommodity(MerchantCorresponding entity);

}