package com.vic.ck.console.customer.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vic.base.pager.Lookup;
import com.vic.base.pager.PageInfo;
import com.vic.base.pager.Remind;
import com.vic.ck.console.BaseConsoleController;
import com.vic.ck.console.customer.lookup.CustomerLookup;
import com.vic.ck.console.customer.service.CustomerService;
import com.vic.ck.entity.Customer;

/**
 * 用户表
 * 
 */
@Controller
@RequestMapping("/console/customer/customer")
public class CustomerController extends BaseConsoleController {

	@Resource
	private CustomerService customerService;

	/** 列表页 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String page(Model model) {
		PageInfo<Customer> pager = customerService.list(getLookup());
		model.addAttribute("pager", pager);
		return "console/customer/customer/list";
	}

	/** 重写查询条件 */
	protected Lookup instanceLookup() {
		return new CustomerLookup();
	}

	/** 查询 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(CustomerLookup lookup) {
		setLookup(lookup);
		return "redirect:/console/customer/customer/";
	}

	/** 前往新增页面 */
	@RequestMapping(value = "/0", method = RequestMethod.GET)
	public String add(Model model) {
		return "console/customer/customer/form";
	}

	/** 前往修改页面 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editor(@PathVariable int id, Model model) {
		Customer entity = customerService.findById(id);
		model.addAttribute("entity", entity);
		return "console/customer/customer/form";
	}


	/** 修改用户状态 */
	@RequestMapping(value = "/{id}/enabled", method = RequestMethod.GET)
	public String status(@PathVariable int id, boolean enabled, RedirectAttributes attributes) {
		Customer entity = new Customer();
		entity.setId(id); entity.setEnabled(enabled);
		customerService.update(entity);
		attributes.addFlashAttribute(Remind.success());
		return "redirect:/console/customer/customer/";
	}

}
