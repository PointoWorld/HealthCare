package com.zxu.masterofpainting.bean;

public class Material {
    private String materialName;
    private String materialConsumption;

    public Material(String materialName, String materialConsumption) {
        this.materialName = materialName;
        this.materialConsumption = materialConsumption;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialConsumption() {
        return materialConsumption;
    }

    public void setMaterialConsumption(String materialConsumption) {
        this.materialConsumption = materialConsumption;
    }
}
