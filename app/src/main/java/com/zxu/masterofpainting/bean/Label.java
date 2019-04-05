package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Label extends BmobObject {
    private String labelName;
    private String introduce;
    private String recommend;
    private String ingredientsFine;
    private String ingredientsFineUrl;
    private String badRecommend;
    private String ingredientsBad;
    private String ingredientsBadUrl;
    private String fineCollocation;
    private String fineCollocationUrl;

    public Label(String labelName, String introduce, String recommend, String ingredientsFine,
                 String ingredientsFineUrl, String badRecommend, String ingredientsBad,
                 String ingredientsBadUrl, String fineCollocation, String fineCollocationUrl) {
        this.labelName = labelName;
        this.introduce = introduce;
        this.recommend = recommend;
        this.ingredientsFine = ingredientsFine;
        this.ingredientsFineUrl = ingredientsFineUrl;
        this.badRecommend = badRecommend;
        this.ingredientsBad = ingredientsBad;
        this.ingredientsBadUrl = ingredientsBadUrl;
        this.fineCollocation = fineCollocation;
        this.fineCollocationUrl = fineCollocationUrl;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getIngredientsFine() {
        return ingredientsFine;
    }

    public void setIngredientsFine(String ingredientsFine) {
        this.ingredientsFine = ingredientsFine;
    }

    public String getIngredientsFineUrl() {
        return ingredientsFineUrl;
    }

    public void setIngredientsFineUrl(String ingredientsFineUrl) {
        this.ingredientsFineUrl = ingredientsFineUrl;
    }

    public String getBadRecommend() {
        return badRecommend;
    }

    public void setBadRecommend(String badRecommend) {
        this.badRecommend = badRecommend;
    }

    public String getIngredientsBad() {
        return ingredientsBad;
    }

    public void setIngredientsBad(String ingredientsBad) {
        this.ingredientsBad = ingredientsBad;
    }

    public String getIngredientsBadUrl() {
        return ingredientsBadUrl;
    }

    public void setIngredientsBadUrl(String ingredientsBadUrl) {
        this.ingredientsBadUrl = ingredientsBadUrl;
    }

    public String getFineCollocation() {
        return fineCollocation;
    }

    public void setFineCollocation(String fineCollocation) {
        this.fineCollocation = fineCollocation;
    }

    public String getFineCollocationUrl() {
        return fineCollocationUrl;
    }

    public void setFineCollocationUrl(String fineCollocationUrl) {
        this.fineCollocationUrl = fineCollocationUrl;
    }
}
