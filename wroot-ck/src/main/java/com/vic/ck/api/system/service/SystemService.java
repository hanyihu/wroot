package com.vic.ck.api.system.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.model.BaseModel;
import com.vic.ck.api.system.mapper.SystemMapper;
import com.vic.ck.entity.Area;
import com.vic.ck.entity.Banner;
import com.vic.ck.entity.BootPage;
import com.vic.ck.entity.SysConfig;

@Service
public class SystemService{

	@Resource
	private SystemMapper systemMapper;
	
	/**
	 * 可使用银行列表
	 */
	public List<BaseModel> banks() {
		return systemMapper.banks();
	}

	/**
	 * 启动页
	 * @return
	 */
	public List<BootPage> bootPages() {
		return systemMapper.bootPages();
	}

	
	
	// 城市匹配查询 --
	public Area matchCity(int id, String short_name) {
		return systemMapper.matchCity(id, short_name);
	}
	
	//所有城市
	public List<Area> allCities() {
		return systemMapper.allCities();
	}
	//热门城市
	public List<Area> hotCities() {
		return systemMapper.hotCities();
	}
	//已开放城市
	public List<Area> openedCities() {
		return systemMapper.openedCities();
	}

	//省市区下拉查询
	public List<Area> selectCities(int levelType, int parentId) {
		return systemMapper.selectCities(levelType, parentId);
	}
	
	//查询所有省市区
	public List<Area> selectAllCities(){
		return systemMapper.selectAllCities();
	}
	
	public List<Area> selectCity(int parentId){
		return systemMapper.selectCity(parentId);
	}
	
	/**启动页*/
	public List<Banner> banners(int type, int cityId) {
		return systemMapper.banners(type, cityId);
	}
	
	/**
	 * 系统配置 原准备用EHCACHE个缓存的 (若是分布式的话 建议使用REDIS做MYBATIS的二级缓存)
	 * 但是考虑到使用的场景并不多暂且不做
	 */
	public SysConfig getSysConfig(String code) {
		return systemMapper.getSysConfig(code);
	}

	/**系统配置INT*/
	public int getSysConfigInt(String code) {
		SysConfig s = getSysConfig(code);
		return s == null ? 0 : s.getIntContent();
	}
	
	/**系统配置String*/
	public String getSysConfigString(String code) {
		SysConfig s = getSysConfig(code);
		return s == null ? "" : s.getContent();
	}
	
	/**系统配置BigDecimal*/
	public BigDecimal getSysConfigBigDecimal(String code) {
		SysConfig s = getSysConfig(code);
		return s == null ? BigDecimal.ZERO : s.getBigDecimalContent();
				
	}
	
	/**全局配置列表*/
	public List<SysConfig> sysConfigs(){
		return systemMapper.sysConfigs();
	}
	
	/**修改系统配置*/
	public void updateSysConfig(SysConfig entity){
		systemMapper.updateSysConfig(entity);
	}
}
