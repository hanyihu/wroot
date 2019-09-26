package com.vic.ck.console.statistics.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图简单对象
 * @author VIC
 *
 */
public class BarGraphVo {

	/**
	 * 每个柱里面包含的name集合 (series的name属性的集合)
	 */
	private List<String> legend = new ArrayList<String>();
	
	/**
	 * x轴每个柱的名称集合
	 */
	private List<String> xAxis = new ArrayList<String>();
	
	/**
	 * series.size == legend.size
	 * 每个柱的数据 
	 * 
	 */
	private List<SerieVo> series = new ArrayList<SerieVo>();
	
	public void addLegend(String legend){
		this.legend.add(legend);
	}
	
	public void addXAxis(String xAxis){
		this.xAxis.add(xAxis);
	}
	
	public void addSeries(SerieVo series){
		this.series.add(series);
	}

	public List<String> getLegend() {
		return legend;
	}

	public void setLegend(List<String> legend) {
		this.legend = legend;
	}

	public List<String> getxAxis() {
		return xAxis;
	}

	public void setxAxis(List<String> xAxis) {
		this.xAxis = xAxis;
	}

	public List<SerieVo> getSeries() {
		return series;
	}

	public void setSeries(List<SerieVo> series) {
		this.series = series;
	}
	
	
	
}





/**大概对应如下数据*/
/*
 option = {
    tooltip : {
        trigger: 'axis',
        axisPointer : {            //坐标轴指示器，坐标轴触发有效
            type : 'line'         //默认为直线，可选为：'line' | 'shadow'
        }
    },
   
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['周一','周二','周三','周四','周五','周六','周日']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'直接访问',
            type:'bar',
            data:[320, 332, 301, 334, 390, 330, 320]
        },
        {
            name:'邮件营销',
            type:'bar',
            stack: '广告',
            data:[120, 132, 101, 134, 90, 230, 210]
        },
        {
            name:'联盟广告',
            type:'bar',
            stack: '广告',
            data:[220, 182, 191, 234, 290, 330, 310]
        },
        {
            name:'视频广告',
            type:'bar',
            stack: '广告',
            data:[150, 232, 201, 154, 190, 330, 410]
        }
    ]
};
*/
