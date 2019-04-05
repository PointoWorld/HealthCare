package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Hour extends BmobObject {
    private String hourName;
    private String intru;
    private String imageUrl;

    public Hour(String hourName, String intr, String imageUrl) {
        this.hourName = hourName;
        this.intru = intr;
        this.imageUrl = imageUrl;
    }

    public String getHourName() {
        return hourName;
    }

    public void setHourName(String hourName) {
        this.hourName = hourName;
    }

    public String getIntr() {
        return intru;
    }

    public void setIntr(String intr) {
        this.intru = intr;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
