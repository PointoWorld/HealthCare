package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Ingredient1 extends BmobObject {
    private String IngredientsName;
    private String Img;
    private String Nutrition;
    private String Efficiency;
    private String SuitableCollocation;
    private String Gongxiao;

    public Ingredient1(String ingredientsName, String img, String nutrition, String efficiency, String suitableCollocation, String gongxiao) {
        IngredientsName = ingredientsName;
        Img = img;
        Nutrition = nutrition;
        Efficiency = efficiency;
        SuitableCollocation = suitableCollocation;
        Gongxiao = gongxiao;
    }

    public String getIngredientsName() {
        return IngredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        IngredientsName = ingredientsName;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
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

    public String getGongxiao() {
        return Gongxiao;
    }

    public void setGongxiao(String gongxiao) {
        Gongxiao = gongxiao;
    }
}
