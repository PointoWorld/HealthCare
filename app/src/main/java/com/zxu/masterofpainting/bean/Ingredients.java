package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Ingredients extends BmobObject {
    private String IngredientsName; //食材名字
    private String Nutrition; // 营养成分
    private String Efficiency; // 功效
    private String SuitableCollocation; // 适宜搭配

    public String getIngredientsName() {
        return IngredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        IngredientsName = ingredientsName;
    }

    public String getNutrition() {
        return Nutrition;
    }

    public void setNutrition(String nutrition) {
        Nutrition = nutrition;
    }

    public String getEfficiency() {
        return Efficiency;
    }

    public void setEfficiency(String efficiency) {
        Efficiency = efficiency;
    }

    public String getSuitableCollocation() {
        return SuitableCollocation;
    }

    public void setSuitableCollocation(String suitableCollocation) {
        SuitableCollocation = suitableCollocation;
    }
}
