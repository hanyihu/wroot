package com.vic.ck.api.merchant.service;

import com.github.pagehelper.Page;
import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.merchant.mapper.MerchantBalanceMapper;
import com.vic.ck.entity.MerchantBalance;
import com.vic.ck.entity.MerchantBalanceRecord;
import com.vic.ck.vo.CompareBillVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class MerchantBalanceService extends BaseService {

	@Resource
	private MerchantBalanceMapper merchantBalanceMapper;

	/** 修改余额 并新增明细 注意money是可以为负数的 */
	@Transactional
	public boolean addBalance(BigDecimal money, int type, int merchantId) {
		MerchantBalance balance = getMerchantBalance(merchantId);
		if (balance == null)
			return false;

		// 1、修改余额
		BigDecimal b = money.add(balance.getBalance()).setScale(2, BigDecimal.ROUND_HALF_UP);
		balance.setBalance(b);
		if (updateMerchantBalance(balance)) {
			// 2新增余额明细
			insertBalanceRecord(new MerchantBalanceRecord(merchantId, money, type));
			return true;

		}

		return false;
	}

	/** 新增商户余额信息 */
	public int insertMerchantBalance(int merchantId) {
		return merchantBalanceMapper.insertMerchantBalance(merchantId);
	}

	/** 商户余额 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public MerchantBalance getMerchantBalance(int merchantId) {
		return merchantBalanceMapper.getMerchantBalance(merchantId);
	}

	/** 修改账户余额 */
	public boolean updateMerchantBalance(MerchantBalance balance) {
		return merchantBalanceMapper.updateMerchantBalance(balance) > 0;
	}

	/** 新增余额明细 */
	public void insertBalanceRecord(MerchantBalanceRecord record) {
		merchantBalanceMapper.insertBalanceRecord(record);
	}

	/**
	 * 余额明细列表
	 */
	public PageInfo<MerchantBalanceRecord> balanceRecords(Lookup lookup) {
		Page<MerchantBalanceRecord> page = startPage(lookup);
		List<MerchantBalanceRecord> datas = merchantBalanceMapper.balanceRecords(lookup);
		return PageInfo.instance(page, datas, lookup);
	}

	/** 获得对账信息 */
	public CompareBillVo findMerchantBill(int merchantId, Date startDate, Date endDate) {
		return merchantBalanceMapper.findMerchantBill(merchantId, startDate, endDate);
	}

	/**************************	 * 后台管理 ↓ ↓ ↓ **************************************************************/
	/**
	 * 查询列表
	 */
	public PageInfo<MerchantBalance> list(Lookup lookup) {
		startPage(lookup);
		List<MerchantBalance> datas = merchantBalanceMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public MerchantBalance findById(int id) {
		return merchantBalanceMapper.findById(id);
	}

	/**
	 * 新增
	 */
	public int insert(MerchantBalance entity) {
		return merchantBalanceMapper.insert(entity);
	}

	/**
	 * 修改
	 */
	public int update(MerchantBalance entity) {
		return merchantBalanceMapper.update(entity);
	}

}
