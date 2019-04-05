package com.zxu.masterofpainting.bean;

public class SuitableAvoidItem {
    private String collocationName;
    private String collocationEfficacy;

    public SuitableAvoidItem(String collocationName, String collocationEfficacy) {
        this.collocationName = collocationName;
        this.collocationEfficacy = collocationEfficacy;
    }

    public String getCollocationName() {
        return collocationName;
    }

    public void setCollocationName(String collocationName) {
        this.collocationName = collocationName;
    }

    public String getCollocationEfficacy() {
        return collocationEfficacy;
    }

    public void setCollocationEfficacy(String collocationEfficacy) {
        this.collocationEfficacy = collocationEfficacy;
    }
}
