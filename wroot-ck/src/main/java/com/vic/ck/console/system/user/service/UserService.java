package com.vic.ck.console.system.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.entity.User;
import com.vic.ck.console.system.user.mapper.UserMapper;
import com.vic.ck.vo.ConsoleBellTip;

/**
 * 用户信息 service
 * 
 * @author VIC
 *
 */
@Service
public class UserService extends BaseService {

	@Resource
	private UserMapper userMapper;

	/**
	 * 根据用户名查询用户
	 */
	public User getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}

	/**
	 * 列出所有的用户信息
	 * 
	 * @param lookup
	 * @return
	 */
	public PageInfo<User> list(Lookup lookup) {
		Page<User> page = startPage(lookup);
		List<User> list = userMapper.listUser(lookup);
		return PageInfo.instance(page, list, lookup);
	}

	/**
	 * 根据用户名查找
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public boolean checkName(Integer id, String username) {
		return userMapper.checkName(id, username);
	}

	/**
	 * 获取当前id下的用户信息
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}

	public void addUser(User entity) {
		userMapper.addUser(entity);
	}

	public void updateUser(User entity) {
		userMapper.updateUser(entity);
	}

	public void deleteUser(int... ids) {
		userMapper.deleteUser(ids);
	}

	public int checkID(int... ids) {
		return userMapper.checkID(ids);
	}

	/** 修改状态 */
	public void updateStatus(int id, int status) {
		userMapper.updateStatus(id, status);

	}

	/** 修改密码 */
	public void updatePassword(int id, String password) {
		userMapper.updatePassword(id, password);
	}

	/**
	 * 获取一些提醒数量
	 */
	public ConsoleBellTip findTips() {
		return userMapper.findTips();
	}

}
