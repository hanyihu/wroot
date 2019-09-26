package com.vic.ck.console.statistics.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图内部数据
 * 
 * @author VIC
 *
 */
public class SerieVo {

	private String name;

	/**
	 * 类似于组
	 */
	private String stake;

	private String type = "bar";

	/**
	 * 此name对应每个柱里面的数据
	 */
	private List<Double> data = new ArrayList<Double>();
	
	
	
	public SerieVo() {
		super();
	}

	public SerieVo(String name, String stake) {
		super();
		this.name = name;
		this.stake = stake;
	}

	public void addData(Double data){
		this.data.add(data);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStake() {
		return stake;
	}

	public void setStake(String stake) {
		this.stake = stake;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}

}