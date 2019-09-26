package com.vic.ck.console.statistics.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.statistics.mapper.ActivitySurveyRedpacketMapper;
import com.vic.ck.entity.ActivitySurveyRedpacket;

/**
 * 当日红包概况
 *
 */
@Service
public class ActivitySurveyRedpacketService extends BaseService {

	@Resource
	private ActivitySurveyRedpacketMapper activitySurveyRedpacketMapper;

	/**
	 * 查询列表
	 */
	public PageInfo<ActivitySurveyRedpacket> list(Lookup lookup) {
		startPage(lookup);
		List<ActivitySurveyRedpacket> datas = activitySurveyRedpacketMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public ActivitySurveyRedpacket findById(int id) {
		return activitySurveyRedpacketMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(ActivitySurveyRedpacket entity) {
		return activitySurveyRedpacketMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(ActivitySurveyRedpacket entity) {
		return activitySurveyRedpacketMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return activitySurveyRedpacketMapper.delete(ids);
	}

	/**昨日的红包*/
	public ActivitySurveyRedpacket findYestodayRedpacket() {
		return activitySurveyRedpacketMapper.findYestodayRedpacket();
	}

}
