package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Mineral extends BmobObject {
    private String mineralName;
    private String mineralEffect;

    public Mineral(String mineralName, String mineralEffect) {
        this.mineralName = mineralName;
        this.mineralEffect = mineralEffect;
    }

    public String getMineralName() {
        return mineralName;
    }

    public void setMineralName(String mineralName) {
        this.mineralName = mineralName;
    }

    public String getMineralEffect() {
        return mineralEffect;
    }

    public void setMineralEffect(String mineralEffect) {
        this.mineralEffect = mineralEffect;
    }
}
