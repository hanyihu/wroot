package com.vic.base.pager;

import com.github.pagehelper.Page;
import com.vic.base.BaseConstant;

import java.util.List;

/**
 * 列表数据用于分页
 * @author VIC
 *
 */
public class PageInfo<T> {
	
	private List<T> datas;//数据
	
	private int total;//总数据
	
	private int size = BaseConstant.PAGE_SIZE; //每页的数据数量
	
	private int page = 1;//当前页码
	
	
	private int pages;//总页数
	
	private int curSize;//当前页是数量
	
	private Lookup lookup = new Lookup();//本页面的查询条件
	
	
	
	public PageInfo(){}
	
	public PageInfo(int total, List<T> datas){
		this.total = total;
		this.datas = datas;
	}
	
	public PageInfo(Page<T> page, List<T> datas, Lookup lookup){
		if(page != null) {
			this.total = (int) page.getTotal();
			this.size = page.getPageSize();
			if(lookup !=null &&lookup.getPage() > 0) {
				this.page = lookup.getPage();
			}
			this.page = page.getPageNum();
			this.pages = page.getPages();
			this.curSize = page.size();
		}
		this.datas = datas;
		this.lookup = lookup;
	}
	
	
	public PageInfo(List<T> datas, Lookup lookup){
		if(datas instanceof Page) {
			Page<T> page = (Page<T>) datas;
			this.datas = page.getResult();
			this.total = (int) page.getTotal();
			this.page = page.getPageNum();
			this.size = page.getPageSize();
			this.pages = page.getPages();
			this.curSize = page.size();
		}
		this.lookup = lookup;

	}
	
	public static <T> PageInfo<T> instance(Page<T> page, List<T> datas, Lookup lookup){
		return new PageInfo<T>(page, datas, lookup);
	}
	
	public static <T> PageInfo<T> instance(Page<T> page, List<T> datas){
		return new PageInfo<T>(page, datas, null);
	}
	
	public static <T> PageInfo<T> instance(List<T> datas,Lookup lookup){
		return new PageInfo<T>(datas, lookup);
	}
	
	public static <T> PageInfo<T> instance(List<T> datas){
		return new PageInfo<T>(datas, null);
	}
	
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Lookup getLookup() {
		return lookup;
	}

	public void setLookup(Lookup lookup) {
		this.lookup = lookup;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCurSize() {
		return curSize;
	}

	public void setCurSize(int curSize) {
		this.curSize = curSize;
	}
	
	
	

}
