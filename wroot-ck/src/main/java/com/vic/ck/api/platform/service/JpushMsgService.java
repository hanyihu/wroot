package com.vic.ck.api.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.jpush.service.CustomerJpushService;
import com.vic.ck.api.jpush.service.MerchantJpushService;
import com.vic.ck.api.platform.mapper.JpushMsgMapper;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.JpushMsg;

/**
 * 推送消息表
 *
 */
@Service
public class JpushMsgService extends BaseService {

	@Resource
	private JpushMsgMapper jpushMsgMapper;

	@Resource
	private CustomerJpushService customerJpushService;

	@Resource
	private MerchantJpushService merchantJpushService;

	@Resource
	private AuthService authService;

	/**
	 * 推送给用户 并存入推送消息表
	 */
	public boolean pushToCustomer(int customerId, String content) {
		Customer c = authService.findCustomerById(customerId);
		if (c == null || StringUtils.isEmpty(c.getMobileCode()))
			return false;
		return pushToCustomer(customerId, content, c.getMobileCode());
	}

	/**
	 * 推送给用户 并存入推送消息表
	 */
	public boolean pushToCustomer(int customerId, String content, String registrationId) {
		JpushMsg entity = new JpushMsg(customerId, 1, content, 1);
		insert(entity);
		return customerJpushService.create(content).data("id", entity.getId()).to(registrationId).push();
	}

	/**
	 * 推送给商户 并存入推送消息表
	 */
	public boolean pushToMerchant(int customerId, String content) {
		Customer c = authService.findCustomerById(customerId);
		if (c == null || StringUtils.isEmpty(c.getMerchantMobileCode()))
			return false;
		return pushToMerchant(customerId, content, c.getMerchantMobileCode());
	}

	/**
	 * 推送给商户 并存入推送消息表
	 */
	public boolean pushToMerchant(int customerId, String content, String registrationId) {
		JpushMsg entity = new JpushMsg(customerId, 2, content, 1);
		insert(entity);
		return merchantJpushService.create(content).data("id", entity.getId()).to(registrationId).push();
	}

	/**
	 * 查询列表
	 */
	public PageInfo<JpushMsg> list(Lookup lookup) {
		startPage(lookup);
		List<JpushMsg> datas = jpushMsgMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public JpushMsg findById(int id) {
		jpushMsgMapper.update(new JpushMsg().setId(id).setReaded(1));
		return jpushMsgMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(JpushMsg entity) {
		return jpushMsgMapper.insert(entity);
	}

	/**
	 * 新增某个城市的全部人员的
	 */
	public int insertCity(JpushMsg entity) {
		return jpushMsgMapper.insertCity(entity);
	}

	/**
	 * 新增平台的全部
	 */
	public int insertAll(JpushMsg entity) {
		return jpushMsgMapper.insertAll(entity);
	}

	/**
	 * 修改
	 */
	public int update(JpushMsg entity) {
		return jpushMsgMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return jpushMsgMapper.delete(ids);
	}

}
