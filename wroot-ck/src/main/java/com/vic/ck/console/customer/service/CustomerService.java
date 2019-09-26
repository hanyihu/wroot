package com.vic.ck.console.customer.service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.customer.mapper.CustomerMapper;
import com.vic.ck.entity.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * 用户表
 *
 */
@Service
public class CustomerService extends BaseService{

	@Resource
	private CustomerMapper customerMapper;
	
	/**
    * 查询列表
    */
	public PageInfo<Customer> list(Lookup lookup){
		startPage(lookup);
		List<Customer> datas = customerMapper.list(lookup);
		return PageInfo.instance(datas, lookup);
	}
	
	/**
	 * 根据主键查询
	 */
	public Customer findById(int id) {
		return customerMapper.findById(id);
	}
	
	/**
	 * 新增
	 */
	 public int insert(Customer entity) {
		return customerMapper.insert(entity);
	}
	
	/**
	 * 修改
	 */
	  public int update(Customer entity) {
		  return customerMapper.update(entity);
	  }
	  
	  /***
	   * 删除
	   */
	  public int delete(int...ids) {
		  return customerMapper.delete(ids);
	  }

    /*
     * 根据经纬度查询用户信息
     * */
    public List<Customer> findByLat(String longitude, String latitude) {
        return customerMapper.findByLat(longitude, latitude);
    }

}
