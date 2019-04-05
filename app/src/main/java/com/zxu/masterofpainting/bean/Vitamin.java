package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Vitamin extends BmobObject {
    private String vitaminName;
    private String vitaminEffect;

    public Vitamin(String vitaminName, String vitaminEffect) {
        this.vitaminName = vitaminName;
        this.vitaminEffect = vitaminEffect;
    }

    public String getVitaminName() {
        return vitaminName;
    }

    public void setVitaminName(String vitaminName) {
        this.vitaminName = vitaminName;
    }

    public String getVitaminEffect() {
        return vitaminEffect;
    }

    public void setVitaminEffect(String vitaminEffect) {
        this.vitaminEffect = vitaminEffect;
    }
}
