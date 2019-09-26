package com.vic.ck.api.system.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.vic.base.BaseApiLookup;
import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.api.system.mapper.CustomerBalanceMapper;
import com.vic.ck.console.customer.lookup.CustomerBalanceLookup;
import com.vic.ck.entity.CustomerBalance;
import com.vic.ck.entity.CustomerBalanceRecord;

@Service
public class CustomerBalanceService extends BaseService {

	@Resource
	private CustomerBalanceMapper customerBalanceMapper;

	/** 修改余额 并新增明细 注意money是可以为负数的 */
	@Transactional
	public boolean addBalance(BigDecimal money, int type, int customerId) {
		if(money == null || money.compareTo(BigDecimal.ZERO) == 0) {
			return false;
		}
		CustomerBalance balance = getCustomerBalance(customerId);
		if (balance == null)
			return false;

		// 1、修改余额
		BigDecimal b = money.add(balance.getBalance()).setScale(2, BigDecimal.ROUND_HALF_UP);
		balance.setBalance(b);
		if (updateCustomerBalance(balance)) {
			// 2新增余额明细
			insertBalanceRecord(new CustomerBalanceRecord(customerId, money, type));
			return true;
		}

		return false;
	}

	/** 修改余额 并新增明细 注意money是可以为负数的 */
	@Transactional
	public boolean addBalance(BigDecimal money, int type, int customerId, String describe) {
		if(money == null || money.compareTo(BigDecimal.ZERO) == 0) {
			return false;
		}
		CustomerBalance balance = getCustomerBalance(customerId);
		if (balance == null)
			return false;

		// 1、修改余额
		BigDecimal b = money.add(balance.getBalance()).setScale(2, BigDecimal.ROUND_HALF_UP);
		balance.setBalance(b);
		if (updateCustomerBalance(balance)) {
			// 2新增余额明细
			insertBalanceRecord(new CustomerBalanceRecord(customerId, money, type, describe));
			return true;
		}

		return false;
	}

	/** 用户余额 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public CustomerBalance getCustomerBalance(int customerId) {
		return customerBalanceMapper.getCustomerBalance(customerId);
	}

	/** 用户余额 */
	public BigDecimal customerBalance(int customerId) {
		CustomerBalance b = customerBalanceMapper.getCustomerBalance(customerId);
		if (b == null) {
			return BigDecimal.ZERO;
		}
		return b.getBalance();

	}

	/** 修改账户余额 */
	public boolean updateCustomerBalance(CustomerBalance balance) {
		return customerBalanceMapper.updateCustomerBalance(balance) > 0;
	}

	/** 新增余额明细 */
	public void insertBalanceRecord(CustomerBalanceRecord record) {
		customerBalanceMapper.insertBalanceRecord(record);
	}

	/**
	 * 余额明细列表
	 */
	public PageInfo<CustomerBalanceRecord> balanceRecords(BaseApiLookup lookup) {
		Page<CustomerBalanceRecord> page = startPage(lookup);
		List<CustomerBalanceRecord> datas = customerBalanceMapper.balanceRecords(lookup);
		return PageInfo.instance(page, datas);
	}

	public boolean existBalanceRecord(int customerId, int type, Date date) {
		return customerBalanceMapper.existBalanceRecord(customerId, type, date);
	}
	
	/** **********************************************************************************/

	/**
	 * 查询列表
	 */
	public PageInfo<CustomerBalance> list(Lookup lookup) {
		startPage(lookup);
		List<CustomerBalance> datas = customerBalanceMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}

	/**
	 * 根据主键查询
	 */
	public CustomerBalance findById(int id) {
		return customerBalanceMapper.findById(id);
	}

	/**
	 * 修改
	 */
	public int update(CustomerBalance entity) {
		return customerBalanceMapper.update(entity);
	}

	/**
	 * 余额明细列表
	 */
	public PageInfo<CustomerBalanceRecord> balanceRecords(CustomerBalanceLookup lookup) {
		startPage(lookup);
		List<CustomerBalanceRecord> datas = customerBalanceMapper.balanceRecords(lookup);
		return PageInfo.instance(datas, lookup);
	}

}
