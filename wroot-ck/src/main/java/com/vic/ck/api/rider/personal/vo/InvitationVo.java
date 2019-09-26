package com.vic.ck.api.rider.personal.vo;


import com.vic.ck.entity.Customer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
/**
 * @author hanyihu
 * @date 2019/4/17 13:14
 *
 */
/*骑手端 个人中心  我的邀请类*/
public class InvitationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /*我的邀请信息集合*/
    @ApiModelProperty(value = "我的邀请信息集合")
    private List<Customer> invitationList;

    /*已邀请人数*/
    @ApiModelProperty(value = "已邀请人数")
    private Integer allInvitation;

    /*成功邀请人数*/
    @ApiModelProperty(value = "成功邀请人数")
    private Integer successInvitation;

    /*累计收益*/
    @ApiModelProperty(value = "累计收益")
    private Integer invatationFee;

    /*邀请地址*/
    @ApiModelProperty(value = "邀请地址")
    private String codePath;

    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    public List<Customer> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(List<Customer> invitationList) {
        this.invitationList = invitationList;
    }

    public Integer getAllInvitation() {
        return allInvitation;
    }

    public void setAllInvitation(Integer allInvitation) {
        this.allInvitation = allInvitation;
    }

    public Integer getSuccessInvitation() {
        return successInvitation;
    }

    public void setSuccessInvitation(Integer successInvitation) {
        this.successInvitation = successInvitation;
    }

    public Integer getInvatationFee() {
        return invatationFee;
    }

    public void setInvatationFee(Integer invatationFee) {
        this.invatationFee = invatationFee;
    }
}
