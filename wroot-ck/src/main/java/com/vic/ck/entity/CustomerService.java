package com.vic.ck.entity;

import com.vic.ck.util.CommonUtils;
import com.vic.wroot.common.annotation.AttachmentFlag;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/*骑手端 我的客服表*/

/**
 * @author hanyihu
 * @date 2019/4/20 13:37
 */
public class CustomerService implements Serializable {
    private static final long serialVersionUID = 1L;

    /*id*/
    private Integer id;

    /*客服标题*/
    @ApiModelProperty(value = "客服标题")
    private String title;

   /*客服电话*/
   @ApiModelProperty(value = "客服电话")
   private String phone;

   /*客服头像*/
   @ApiModelProperty(value = "客服头像")
   @AttachmentFlag(AttachmentFlag.AttachmenType.SIGN)
   private Integer  headImage;

   /*客服问题集合*/
   @ApiModelProperty(value = "客服问题")
    private List<CustomerServiceProblem> problemList;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getHeadImage() {
        return headImage;
    }

    public void setHeadImage(Integer headImage) {
        this.headImage = headImage;
    }

    public List<CustomerServiceProblem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<CustomerServiceProblem> problemList) {
        this.problemList = problemList;
    }

    public String getHeadImageUrl() {
        if ((headImage == null || headImage == 0) ) {
            return null;
        }
        return CommonUtils.getImageUrl(headImage);
    }



}
