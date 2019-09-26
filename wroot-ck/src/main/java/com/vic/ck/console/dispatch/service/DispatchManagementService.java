package com.vic.ck.console.dispatch.service;

import com.alibaba.fastjson.JSON;
import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.customer.mapper.CustomerMapper;
import com.vic.ck.console.dispatch.mapper.DispatchManagementMapper;
import com.vic.ck.console.dispatch.util.DistanceUtils;
import com.vic.ck.console.merchant.mapper.ConsoleMerchantMapper;
import com.vic.ck.entity.ConsoleMerchant;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.RiderTask;
import com.vic.wroot.common.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hanyihu
 * @date 2019/4/17 10:51
 */
@Service
public class DispatchManagementService extends BaseService {

    @Resource
    private DispatchManagementMapper dispatchManagementMapper;

    @Resource
    private AttachmentService attachmentService;

    @Resource
    private CustomerMapper customerMapper;


    @Resource
    private ConsoleMerchantMapper consoleMerchantMapper;
    /**
     * 查询列表
     */
  /* public PageInfo<RiderTask> list(Lookup lookup) {
        startPage(lookup);
        List<RiderTask> datas = dispatchManagementMapper.list(lookup);
        return PageInfo.instance(datas, lookup);
    }*/

    public PageInfo<RiderTask> manageList(Lookup lookup) {
        startPage(lookup);
        List<RiderTask> datas = dispatchManagementMapper.manageList(lookup);//获取接单任务信息
        for (RiderTask riderTask: datas) {
            int merchantId = findByOrderNo(riderTask.getOrderno());

            List<Customer> distance = distance(merchantId);
           riderTask.setRiderNum(distance.size());
        }

        return PageInfo.instance(datas, lookup);
    }

    /*根据订单号查找商户id*/
    public int findByOrderNo(String orderno){
        return dispatchManagementMapper.findByOrderNo(orderno);
    }

    /*详情*/
   public List<Customer> customerList(int id){

        List<Customer> distance = distance(id);
        return  distance;
    }

    /**
     * 根据主键查询
     */
    public RiderTask findById(int id) {
        return dispatchManagementMapper.findById(id);
    }

   /**/
   public List<Customer> distance(int merchantId){

       //根据商家id获取商家的经纬度位置
       ConsoleMerchant consoleMerchant = consoleMerchantMapper.findById(merchantId);
       double longitude=0;
       double latitude=0;
       if(consoleMerchant!=null){
           longitude = consoleMerchant.getLongitude();//经度
           latitude = consoleMerchant.getLatitude();//纬度
       }
       System.out.println("商家经度"+longitude+"   维度"+latitude);

       //截取商家的经纬度并作为查询条件 查询附近骑手的位置和数量
       String subLongitude = DistanceUtils.calculateProfit(longitude);

       String sublatitude = DistanceUtils.calculateProfit(latitude);
       List<Customer> datas = customerMapper.findByLat(subLongitude, sublatitude);
       int customerNum=0;
       if(datas.size()!=0)
       {
           customerNum=datas.size();
           //遍历骑手，并获取经纬度
           for (Customer  customer : datas) {
               customer.setDiatance(DistanceUtils.getDistance(longitude,latitude,customer.getLng(),customer.getLat()));
               customerMapper.update(customer);
           }
       }
       System.out.println("符合规范电话"+ JSON.toJSONString(datas));
       System.out.println("商家附近骑手数量=="+customerNum);

       return  datas;
   }
/*

    public PageInfo<Customer> distances(int merchantId,Lookup lookup){
        startPage(lookup);
        //根据商家id获取商家的经纬度位置
        ConsoleMerchant consoleMerchant = consoleMerchantMapper.findById(merchantId);
        double longitude=0;
        double latitude=0;
        if(consoleMerchant!=null){
            longitude = consoleMerchant.getLongitude();//经度
            latitude = consoleMerchant.getLatitude();//纬度
        }
        System.out.println("商家经度"+longitude+"   维度"+latitude);

        //截取商家的经纬度并作为查询条件 查询附近骑手的位置和数量
        String subLongitude = DistanceUtils.calculateProfit(longitude);

        String sublatitude = DistanceUtils.calculateProfit(latitude);
        List<Customer> datas = customerMapper.findByLats(subLongitude, sublatitude,lookup);
        int customerNum=0;
        if(datas.size()!=0)
        {
            customerNum=datas.size();
            //遍历骑手，并获取经纬度
            for (Customer  customer : datas) {
                customer.setDiatance(DistanceUtils.getDistance(longitude,latitude,customer.getLng(),customer.getLat()));
            }
        }
        System.out.println("符合规范电话ffffffff"+ JSON.toJSONString(datas));
        System.out.println("商家附近骑手数量fffffffff=="+customerNum);

        return PageInfo.instance(datas, lookup);
    }
*/

}
