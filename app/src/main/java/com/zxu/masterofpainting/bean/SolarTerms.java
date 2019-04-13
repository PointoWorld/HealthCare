package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class SolarTerms extends BmobObject {
    private String solarName;
    private String imageUrl;
    private String solarTerms;
    private String tea;
    private String fuirts;

    public SolarTerms(String solarName, String imageUrl, String solarTerms, String tea, String fuirts) {
        this.solarName = solarName;
        this.imageUrl = imageUrl;
        this.solarTerms = solarTerms;
        this.tea = tea;
        this.fuirts = fuirts;
    }

    public String getFuirts() {
        return fuirts;
    }

    public void setFuirts(String fuirts) {
        this.fuirts = fuirts;
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

    public String getTea() {
        return tea;
    }

    public void setTea(String tea) {
        this.tea = tea;
    }
}
