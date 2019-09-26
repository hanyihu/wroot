package com.vic.ck.api.rider.dispatch.controller.service;


import com.vic.ck.api.rider.dispatch.controller.mapper.DispatchMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DispatchService {

   @Resource
   private DispatchMapper dispatchMapper;

    /**
     * 骑手数量查询
     * @param s
     */
    public int  riderNum(Double lng,Double lat) {
        return dispatchMapper.riderNum(lng,lat);

    }



}
