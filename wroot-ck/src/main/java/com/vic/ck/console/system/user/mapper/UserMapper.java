package com.vic.ck.console.system.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vic.base.pager.Lookup;
import com.vic.ck.console.entity.User;
import com.vic.ck.vo.ConsoleBellTip;
import com.vic.wroot.common.annotation.MybatisMapper;

@MybatisMapper
public interface UserMapper {

	User getUserByUsername(@Param("username")String username);
	
	/**
	 * 查看用户所有的信息
	 */
	public List<User> listUser(Lookup lookUp);	
	
	/**
	 * 根据名称进行查找看是否重复
	 */
	public boolean checkName(@Param("id")Integer id, @Param("username")String username);
	/**
	 * 获取编号为id的用户的所有信息
	 */
	public User getUserById(@Param("id")int id);
	/**
	 * 添加用户
	 */
	public void addUser(User entity);
	
	/**
	 * 修改用户信息
	 */
	public void updateUser(User entity);
	/**
	 * 删除用户
	 */
	public void deleteUser(@Param("ids")int[] ids);
	/**
	 * 获取id
	 */
	public int checkID(@Param("ids")int[] ids);

	/***修改用户状态*/
	void updateStatus(@Param("id")int id, @Param("status")int status);
	
	/**x修密码*/
	void updatePassword(@Param("id")int id, @Param("password")String password);

	/**一些提醒的数量*/
	ConsoleBellTip findTips();
	
}
