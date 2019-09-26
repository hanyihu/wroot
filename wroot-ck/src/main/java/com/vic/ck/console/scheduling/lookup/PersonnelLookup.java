package com.vic.ck.console.scheduling.lookup;


import com.vic.base.pager.CommonLookup;

/**
 * @author xbw
 * @time 2019/4/17 13:25
 * 骑手查询条件
 */
public class PersonnelLookup  extends CommonLookup {
    private String nickname;
    private Integer gender;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

}
