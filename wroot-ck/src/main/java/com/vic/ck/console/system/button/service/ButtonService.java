package com.vic.ck.console.system.button.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vic.base.BaseService;
import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.ck.console.entity.Button;
import com.vic.ck.console.system.button.mapper.ButtonMapper;

@Service
public class ButtonService extends BaseService {
	@Resource
	private ButtonMapper buttonMapper;

	public PageInfo<Button> list(Lookup lookup) {
		startPage(lookup);
		List<Button> list = buttonMapper.ListButton(lookup);
		return PageInfo.instance(list, lookup);
	}

	public boolean checkName(Integer id, String name) {
		return buttonMapper.checkName(id, name);
	}

	public Button getButtonId(int id) {
		return buttonMapper.getButtonid(id);
	}

	public void updateButton(Button entity) {
		buttonMapper.updateButton(entity);
	}

	public void addButton(Button entity) {
		buttonMapper.addButton(entity);
	}

	public void deleteButton(int... ids) {
		buttonMapper.deleteButton(ids);
	}
	public int checkID(int... ids){
		return buttonMapper.checkID(ids);
	}

}
