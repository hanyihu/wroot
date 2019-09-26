package com.vic.ck.console.msg.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.jpush.service.CustomerJpushService;
import com.vic.ck.api.jpush.service.MerchantJpushService;
import com.vic.ck.api.platform.service.JpushMsgService;
import com.vic.ck.api.system.service.AuthService;
import com.vic.ck.console.msg.mapper.PlatformMsgMapper;
import com.vic.ck.entity.Customer;
import com.vic.ck.entity.JpushMsg;
import com.vic.ck.entity.PlatformMsg;
import com.vic.wroot.third.jpush.JpusHandle.AudienceEnum;

/**
 * 系统的信息表
 *
 */
@Service
public class PlatformMsgService extends BaseService {

	@Resource
	private PlatformMsgMapper platformMsgMapper;
	
	@Resource
	private CustomerJpushService customerJpushService;
	
	@Resource
	private MerchantJpushService merchantJpushService;
	
	@Resource
	private JpushMsgService jpushMsgService;
	
	@Resource
	private AuthService authService;

	/**
	 * 查询列表
	 */
	public PageInfo<PlatformMsg> list(Lookup lookup) {
		startPage(lookup);
		List<PlatformMsg> datas = platformMsgMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public PlatformMsg findById(int id) {
		return platformMsgMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(PlatformMsg entity) {
		return platformMsgMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(PlatformMsg entity) {
		return platformMsgMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return platformMsgMapper.delete(ids);
	}

	/**
	 * 推送消息
	 */
	public void pushMsg(int id) {
		PlatformMsg entity = findById(id);
		if (entity != null) {
			Integer targetType = entity.getTargtType();
			Integer role =entity.getRole();
			if(role == null || targetType == null ) return;
			
			switch (targetType) {
			case 1: //全平台
				if(role ==1) {
					customerJpushService.create(entity.getContent()).audience(AudienceEnum.all).push();
				}else if(role ==2) {
					merchantJpushService.create(entity.getContent()).audience(AudienceEnum.all).push();
				}
				JpushMsg msg = new JpushMsg(null, role, entity.getContent(), 2);
				msg.setMsgId(id);
				jpushMsgService.insertAll(msg);
				break;
			case 2://某个城市
				if(role ==1) {
					customerJpushService.create(entity.getContent()).audience(AudienceEnum.tag).to(entity.getCityId()+"").push();
				}else if(role ==2) {
					merchantJpushService.create(entity.getContent()).audience(AudienceEnum.tag).to(entity.getCityId()+"").push();
				}
				JpushMsg msg2 = new JpushMsg(null, role, entity.getContent(), 2);
				msg2.setCityId(entity.getCityId());
				msg2.setMsgId(id);
				jpushMsgService.insertCity(msg2);
				break;
			case 3://个人
				Customer c = authService.findCustomerByMobile(entity.getMobile());
				if(c != null ){
					if(role ==1 && StringUtils.isNotEmpty(c.getMobileCode())) {
						jpushMsgService.pushToCustomer(c.getId(), entity.getContent(), c.getMobileCode());
					}else if(role == 2 && StringUtils.isNotEmpty(c.getMerchantMobileCode())){
						jpushMsgService.pushToMerchant(c.getId(), entity.getContent(), c.getMerchantMobileCode());
					}
				}
			default:
				break;
			}
		}
		platformMsgMapper.pushMsg(id);
	}
	

}
