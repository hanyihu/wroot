package com.vic.ck.console.platform.mapper;

import com.vic.base.BaseMapper;
import com.vic.base.model.BaseModel;
import com.vic.ck.entity.PlatformAdPosition;
import com.vic.ck.entity.PlatformCommodityCategory;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类
 * 
 * @author VIC
 */
@MybatisMapper
public interface PlatformCommodityCategoryMapper extends BaseMapper<PlatformCommodityCategory> {

    List<BaseModel> categoryList(@Param("pid") Integer pid);
}
