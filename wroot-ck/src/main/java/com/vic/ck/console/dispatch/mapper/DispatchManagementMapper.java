package com.vic.ck.console.dispatch.mapper;

import com.vic.base.BaseMapper;
import com.vic.base.pager.Lookup;
import com.vic.ck.entity.RiderTask;
import com.vic.wroot.common.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author hanyihu
 * @date 2019/4/17 10:51
 */
@MybatisMapper
public interface DispatchManagementMapper extends BaseMapper<RiderTask> {

    List<RiderTask> manageList(Lookup lookup);

    int findByOrderNo(@Param("orderno") String orderno);
}
