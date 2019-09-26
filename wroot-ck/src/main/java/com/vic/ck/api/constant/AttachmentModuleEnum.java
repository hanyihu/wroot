package com.vic.ck.api.constant;

public enum AttachmentModuleEnum {

    QRCODE(1, "推荐二维码"),
    GROUP_BUY(2, "团购"),
    HOTEL(3, "酒店"),
    CUSTOMER(4, "用户相关"),
    MERCHANT(5, "商家相关"),
    SYSTEM(6, "系统相关");

    private int module;

    private String desc;

    private AttachmentModuleEnum(int module, String desc) {
        this.module = module;
        this.desc = desc;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
