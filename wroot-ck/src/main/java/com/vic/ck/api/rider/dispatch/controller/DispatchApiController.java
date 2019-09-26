package com.vic.ck.api.rider.dispatch.controller;


import com.vic.base.BaseApiController;
import com.vic.ck.api.rider.dispatch.controller.service.DispatchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/*骑手端接单中心 派单相关接口*/
@RestController
@RequestMapping("/api/rider/receiving")
public class DispatchApiController extends BaseApiController {

    @Resource
    private DispatchService dispatchService;

/*派单 根据经纬度查询骑手数量*/
@RequestMapping(value = "/dispatch",method = RequestMethod.GET)
public void dispatch (Double lng,Double lat){

    int num = dispatchService.riderNum(lng, lat);

    //判断骑手数量是否充足，数量大于等于2为足
    if(num>=2){
        //骑手数量充足 TODO
    }
    //否则骑手数量不足，进入派单模式中 TODO

}

}
