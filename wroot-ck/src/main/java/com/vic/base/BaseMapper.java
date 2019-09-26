package com.vic.base;

import java.util.List;

import com.vic.ck.entity.Merchant;
import org.apache.ibatis.annotations.Param;

import com.vic.base.pager.Lookup;

/**
 * 后台的Base MAPPER
 * @author VIC
 *
 * @param <T>
 */
public interface BaseMapper<T>{

	/**查询列表*/
	List<T> list(Lookup lookup);
	
	/**根据主键id查询对象*/
	T findById(@Param("id") int id);

	/**插入对象*/
	int insert(T entity);
	
	/**更新数据*/
	int update(T entity);
	
	/**批量删除*/
	int  delete(@Param("ids")int[] ids);
	
	/**批量获取*/
	List<T> findByIds(@Param("ids")int[] ids);

	/**根据状态查询所有回收的商家*/
	List<T> findByState(Lookup lookup,@Param("state") int state);

	/**根据id查询当前商家信息**/
	Merchant findByIdAll(@Param("id") int id);

}
