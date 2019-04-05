package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Slider extends BmobObject {
    private String sliderName;
    private String sliderIntr;
    private String sliderContent;

    public Slider(String sliderName, String sliderIntr, String sliderContent) {
        this.sliderName = sliderName;
        this.sliderIntr = sliderIntr;
        this.sliderContent = sliderContent;
    }

    public String getSliderName() {
        return sliderName;
    }

    public void setSliderName(String sliderName) {
        this.sliderName = sliderName;
    }

    public String getSliderIntr() {
        return sliderIntr;
    }

    public void setSliderIntr(String sliderIntr) {
        this.sliderIntr = sliderIntr;
    }

    public String getSliderContent() {
        return sliderContent;
    }

    public void setSliderContent(String sliderContent) {
        this.sliderContent = sliderContent;
    }
}
