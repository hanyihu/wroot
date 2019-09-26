package com.vic.ck.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 骑手端  客服问题表
 *
 * @author hanyihu
 * @date 2019/4/20 13:37
 */
public class CustomerServiceProblem implements Serializable {
    private static final long serialVersionUID = 1L;

   private int id;

    /*问题*/
    @ApiModelProperty(value = "问题")
    private String problem;

    /*解决方案*/
    @ApiModelProperty(value = "解决方案")
    private String solution;

    /*客服问题集合*/
    @ApiModelProperty(value = "是否显示")
    private int isshow;

    public boolean getShow() {
        return (isshow==1?true:false);
    }

    public void setShow(int isshow) {
        this.isshow = isshow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
