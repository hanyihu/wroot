package com.vic.ck.entity;

/**
 * 版本号1.0
 */
public class Version {
    private static final long serialVersionUID = 1L;

    /**
     * 号
     */
    private Integer versionNumber;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }
}
