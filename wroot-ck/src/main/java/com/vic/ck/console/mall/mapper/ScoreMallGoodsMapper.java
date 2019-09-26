package com.vic.ck.console.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.BaseMapper;
import com.vic.ck.entity.ScoreMallGoods;
import com.vic.wroot.common.annotation.MybatisMapper;

/**
 * 积分商城商品
 * 
 * @author VIC
 */
@MybatisMapper
public interface ScoreMallGoodsMapper extends BaseMapper<ScoreMallGoods> {
	
	List<ScoreMallGoods> findByIds(@Param("ids") int[] ids );
}
