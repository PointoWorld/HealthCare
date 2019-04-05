package com.zxu.masterofpainting.bean;


public class NutritionItem {
    private String compositioNname; //成分名字
    private String compositionZongliang; //成分总量

    public NutritionItem(String compositioNname, String compositionZongliang) {
        this.compositioNname = compositioNname;
        this.compositionZongliang = compositionZongliang;
    }

    public String getCompositioNname() {
        return compositioNname;
    }

    public void setCompositioNname(String compositioNname) {
        this.compositioNname = compositioNname;
    }

    public String getCompositionZongliang() {
        return compositionZongliang;
    }

    public void setCompositionZongliang(String compositionZongliang) {
        this.compositionZongliang = compositionZongliang;
    }
}
