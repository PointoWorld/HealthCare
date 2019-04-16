package com.zxu.masterofpainting.Cha.flourTea;

import cn.bmob.v3.BmobObject;

public class flowertea extends BmobObject {
    private String name;
    private String GongXiao;
    private String ZuoFa;
    private String YuanLiao;
    private String imgUrl;

    public flowertea(String name, String gongXiao, String zuoFa, String yuanLiao, String imgUrl) {
        this.name = name;
        GongXiao = gongXiao;
        ZuoFa = zuoFa;
        YuanLiao = yuanLiao;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGongXiao() {
        return GongXiao;
    }

    public void setGongXiao(String gongXiao) {
        GongXiao = gongXiao;
    }

    public String getZuoFa() {
        return ZuoFa;
    }

    public void setZuoFa(String zuoFa) {
        ZuoFa = zuoFa;
    }

    public String getYuanLiao() {
        return YuanLiao;
    }

    public void setYuanLiao(String yuanLiao) {
        YuanLiao = yuanLiao;
    }
}
