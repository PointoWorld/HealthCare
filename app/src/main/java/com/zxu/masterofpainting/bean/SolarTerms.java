package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class SolarTerms extends BmobObject {
    private String solarName;
    private String imageUrl;
    private String solarTerms;

    public SolarTerms(String solarName, String imageUrl, String solarTerms) {
        this.solarName = solarName;
        this.imageUrl = imageUrl;
        this.solarTerms = solarTerms;
    }

    public String getSolarName() {
        return solarName;
    }

    public void setSolarName(String solarName) {
        this.solarName = solarName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSolarTerms() {
        return solarTerms;
    }

    public void setSolarTerms(String solarTerms) {
        this.solarTerms = solarTerms;
    }
}
