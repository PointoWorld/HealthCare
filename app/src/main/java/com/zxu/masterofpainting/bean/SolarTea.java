package com.zxu.masterofpainting.bean;

public class SolarTea {
    private String teaName;
    private String imgUrl;
    private String teaIntr;

    public SolarTea(String teaName, String imgUrl, String teaIntr) {
        this.teaName = teaName;
        this.imgUrl = imgUrl;
        this.teaIntr = teaIntr;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTeaIntr() {
        return teaIntr;
    }

    public void setTeaIntr(String teaIntr) {
        this.teaIntr = teaIntr;
    }
}
