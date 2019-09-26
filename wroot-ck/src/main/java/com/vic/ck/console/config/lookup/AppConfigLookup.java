package com.vic.ck.console.config.lookup;

import com.vic.base.pager.CommonLookup;

public class AppConfigLookup extends CommonLookup {
    /**
     * 小程序名称
     */
    private String app_name;

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }
}
