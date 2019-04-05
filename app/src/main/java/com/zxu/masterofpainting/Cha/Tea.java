package com.zxu.masterofpainting.Cha;

import cn.bmob.v3.BmobObject;

public class Tea extends BmobObject {
    private String teaName;
    private String imgUrl;
    private String intr;
    private String chongPao;
    private String steps;
    private String identify;


    public Tea(String teaName, String imgUrl, String intr, String choPao, String steps, String identify) {
        this.teaName = teaName;
        this.imgUrl = imgUrl;
        this.intr = intr;
        this.chongPao = choPao;
        this.steps = steps;
        this.identify = identify;
    }

    public String getChongPao() {
        return chongPao;
    }

    public void setChongPao(String chongPao) {
        this.chongPao = chongPao;
    }

    public String getIntr() {
        return intr;
    }

    public void setIntr(String intr) {
        this.intr = intr;
    }



    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        identify = identify;
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
}
