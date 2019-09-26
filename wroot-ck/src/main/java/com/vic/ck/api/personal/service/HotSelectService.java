package com.vic.ck.api.personal.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchant.mapper.MerchantMapper;
import com.vic.ck.api.personal.mapper.CommodityCategoryMapper;
import com.vic.ck.api.platform.lookup.CommodityLookup;
import com.vic.ck.api.platform.lookup.MerchantLookup;
import com.vic.ck.entity.Commodity;
import com.vic.ck.entity.CommodityCategory;
import com.vic.ck.entity.Merchant;

@Service
public class HotSelectService  extends BaseService {

	
	@Resource
	private MerchantMapper hotSelectMapper;
	@Resource
	private CommodityCategoryMapper ccMapper;

	
	
	/**
	 * 商家热门搜索查询 
	 * @param lookup
	 * @return
	 */
	public PageInfo<Merchant> rmssService(BaseApiLookup lookup){
		Page<Merchant> page = startPage(lookup);
		List<Merchant> datas = hotSelectMapper.rmssList(lookup);
		return PageInfo.instance(page, datas, lookup);
	}

	
	
	/**
	 * 商品分类+模块商品分类+平台特色分类
	 * @param lookup
	 * @return
	 */
	public PageInfo<CommodityCategory> spflService(BaseApiLookup lookup){
		Page<CommodityCategory> page = startPage(lookup);
		List<CommodityCategory> datas = ccMapper.spflList(lookup);
		return PageInfo.instance(page, datas, lookup);
	}
	
	/**
	 * 商品分类+模块商品分类+平台特色分类
	 * @param lookup
	 * @return
	 */
	public PageInfo<CommodityCategory> mkspflService(BaseApiLookup lookup){
		Page<CommodityCategory> page = startPage(lookup);
		List<CommodityCategory> datas = ccMapper.mkspfList(lookup);
		return PageInfo.instance(page, datas, lookup);
	}
	
	/**
	 * 商品分类+模块商品分类+平台特色分类 
	 * @param lookup
	 * @return
	 */
	public PageInfo<CommodityCategory> tsflService(BaseApiLookup lookup){
		Page<CommodityCategory> page = startPage(lookup);
		List<CommodityCategory> datas = ccMapper.tsflList(lookup);
		return PageInfo.instance(page, datas, lookup);
	}
 
	
	/**
	 *  商品查询  ——优惠产品：在优惠券时间内的
	 * @param lookup
	 * @return
	 */
	public PageInfo<Commodity> yhcpService(BaseApiLookup lookup){
		Page<Commodity> page = startPage(lookup);
		List<Commodity> datas = ccMapper.yhcpList(lookup);
		return PageInfo.instance(page, datas, lookup);
	}
	
	
	
	/**
	 * 附近商家列表
	 * 查询所有商家获取坐标
	 * @param lookup
	 * @return
	 */
	public PageInfo<Merchant> fjsjService(MerchantLookup lookup){
		//获取用户 经度
		BigDecimal longitude = lookup.getLongitude();
		//获取用户 纬度
		BigDecimal latitude = lookup.getLatitude();
		
		Map<String, Double> dtFwSj = getDtFwSj(longitude.doubleValue(), latitude.doubleValue(), 5000.00);
        //{maxjd=116.65517814663899, maxwd=35.46496608029594, minjd=116.544821853361, minwd=35.375033919704066}
		Double maxjd = dtFwSj.get("maxjd");
		Double minjd = dtFwSj.get("minjd");
		Double maxwd = dtFwSj.get("maxwd");
		Double minwd = dtFwSj.get("minwd");
		
		Page<Merchant> page = startPage(lookup);
		List<Merchant> datas = hotSelectMapper .fjsjList(maxjd,minjd,maxwd,minwd);
		return PageInfo.instance(page, datas, lookup);
	}

/**
     * @title
     * @date 2017年3月15日
     * @author niuchuang
     * @param longitude 经度
     * @param latitude 纬度
     * @param dis 距离 米
     * @return
     */
    public static Map<String,Double> getDtFwSj(Double longitude, Double latitude, Double dis) {
        Map<String,Double> _map=new HashMap<String,Double>();
        if (longitude!=null && latitude!=null && dis!=null) {
            // 先计算查询点的经纬度范围
            double r = 6371;// 地球半径千米
            // double dis = 0.5;// 0.5千米距离
            dis = dis / 1000;// 米转换千米
            double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude * Math.PI / 180));
            dlng = dlng * 180 / Math.PI;// 角度转为弧度
            /*
            double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude));
            // 转换弧度
            dlng = dlng * (180/Math.PI);
            */
            double dlat = dis / r;
            // 转换弧度
            dlat = dlat * 180 / Math.PI;
            // 处理余弦值为负数
            double minlng = dlng > 0 ? longitude - dlng : longitude + dlng;
            double maxlng = dlng > 0 ? longitude + dlng : longitude - dlng;
            double minlat = dlat > 0 ? latitude - dlat : latitude + dlat;
            double maxlat = dlat > 0 ? latitude + dlat : latitude - dlat;
            _map.put("minjd", minlng);
            _map.put("maxjd", maxlng);
            _map.put("minwd", minlat);
            _map.put("maxwd", maxlat);
        }
        return _map;
        //{maxjd=116.65517814663899, maxwd=35.46496608029594, minjd=116.544821853361, minwd=35.375033919704066}
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
    public static void main(String[] args) {
    	
    	Double longitude = 116.60,   latitude = 35.42,   dis = 5000.00;
    	
        Map<String,Double> _map=new HashMap<String,Double>();
        if (longitude!=null && latitude!=null && dis!=null) {
            // 先计算查询点的经纬度范围
            double r = 6371;// 地球半径千米
            // double dis = 0.5;// 0.5千米距离
            dis = dis / 1000;// 米转换千米
            double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude * Math.PI / 180));
            dlng = dlng * 180 / Math.PI;// 角度转为弧度
            /*
            double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude));
            // 转换弧度
            dlng = dlng * (180/Math.PI);
            */
            double dlat = dis / r;
            // 转换弧度
            dlat = dlat * 180 / Math.PI;
            // 处理余弦值为负数
            double minlng = dlng > 0 ? longitude - dlng : longitude + dlng;
            double maxlng = dlng > 0 ? longitude + dlng : longitude - dlng;
            double minlat = dlat > 0 ? latitude - dlat : latitude + dlat;
            double maxlat = dlat > 0 ? latitude + dlat : latitude - dlat;
            _map.put("minjd", minlng);
            _map.put("maxjd", maxlng);
            _map.put("minwd", minlat);
            _map.put("maxwd", maxlat);
        }
        System.out.println("1");
        System.out.println(_map);
    	//{maxjd=116.65517814663899, maxwd=35.46496608029594, minjd=116.544821853361, minwd=35.375033919704066}

	}
}
