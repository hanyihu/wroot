package com.vic.ck.api.personal.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.constant.AttachmentModuleEnum;
import com.vic.ck.api.constant.FinalFiledParams;
import com.vic.ck.api.personal.mapper.CustomerGroupTicketMapper;
import com.vic.ck.api.platform.service.CommodityService;
import com.vic.ck.api.platform.service.OrderService;
import com.vic.ck.entity.CustomerGroupTicket;
import com.vic.ck.entity.Order;
import com.vic.wroot.common.attachment.service.AttachmentService;
import com.vic.wroot.common.po.AjaxResponse;
import com.vic.wroot.common.util.QRCodeUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * 用户团购券表
 *
 */
@Service
public class CustomerGroupTicketService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerGroupTicketService.class);

	@Resource
	private CustomerGroupTicketMapper customerGroupTicketMapper;

	@Resource
	private CommodityService commodityService;

	@Resource
	private AttachmentService attachmentService;

	@Resource
	private OrderService orderService;

	/**
	 * 查询列表
	 */
	public PageInfo<CustomerGroupTicket> list(Lookup lookup) {
		startPage(lookup);
		List<CustomerGroupTicket> datas = customerGroupTicketMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public CustomerGroupTicket findById(int id) {
		return customerGroupTicketMapper.findById(id);
	}

	/**
	 * 根据券码查询
	 */
	public CustomerGroupTicket findByTicketNo(String ticketNo) {
		return customerGroupTicketMapper.findByTicketNo(ticketNo);
	}

	/**
	 * 新增
	 */
	public int insert(CustomerGroupTicket entity) {
		return customerGroupTicketMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(CustomerGroupTicket entity) {
		return customerGroupTicketMapper.update(entity);
	}

	/***
	 * 删除
	 */
	public int delete(int... ids) {
		return customerGroupTicketMapper.delete(ids);
	}
	
	/**
	 * 删除订单号对应的团购券
	 */
	public int deleteByOrdeno(String orderno){
		return customerGroupTicketMapper.deleteByOrdeno(orderno);
	}
	
	public void merchantDelete(int id) {
		customerGroupTicketMapper.merchantDelete(id);
	}

	/**
	 * 根据订单生成团购券
	 */
//	public int generateGroupbuyTicket(Order order) {
//
//		GroupbuyDetailVo gb = commodityService.groupbuyDetail(order.getCommodityId());
//		if (gb == null) {
//			return 0;
//		}
//		String ticketNo = GeneratorNoUtils.generatorTicketno();
//		CustomerGroupTicket t = new CustomerGroupTicket(order.getTotalFee(), order.getOrderName(), ticketNo,
//				order.getOrderno(), order.getMerchantId(), gb.getStartTime(), gb.getEndTime(), 0,
//				order.getCustomerId());
//		int result = insert(t);
//		generateQrcode(t);
//		return result;
//	}

	/**
	 * 生成团购券二维码
	 */
	public void generateQrcode(final CustomerGroupTicket ticket) {

		logger.info("start 生成团购券二维码 {}", ticket.getTicketNo());
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					final String filePath = "qrcode";
					File f = QRCodeUtil.getQrCode(filePath, ticket.getTicketNo());
					// 上传到附件项目
					AjaxResponse response = attachmentService.upload(f.getName(), new FileInputStream(f),
							AttachmentModuleEnum.QRCODE.getModule() + "");
					if (response != null && response.getCode() == 0) {
						int qrcode = response.getId();
						logger.info("ticket qrcode= {}", qrcode);
						attachmentService.updateTemporary(false, qrcode);
						// 修改团购券的二维码
						updateQrcode(ticket.getId(), qrcode);
						f.delete();
					}
				} catch (Exception e) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}

			}

		}).start();

	}

	/** 修改团购券的二维码 */
	public void updateQrcode(Integer id, int qrcode) {
		customerGroupTicketMapper.updateQrcode(id, qrcode);
	}

	/** 使用团购券 */
	public void useGroupTicket(CustomerGroupTicket ticket) {
		// 使用团购券
		customerGroupTicketMapper.useGroupTicket(ticket);
		// 订单状态置为待评价-已完成 -处理返点信息 ORDER_STATUS_NOCOMMENT
		Order order = orderService.findOrderByOrderno(ticket.getOrderno());
		if (order == null)
			return; // 实 不应为空

		orderService.alterOrderStatus(order.getOrderno(), FinalFiledParams.ORDER_STATUS_NOCOMMENT);
		orderService.afterFinishOrder(order);

	}

	/**
	 * 团购券否过期检测
	 */
	public int overduedUpdate() {
		return customerGroupTicketMapper.overduedUpdate();
	}

}
