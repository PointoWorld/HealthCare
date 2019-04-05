package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Collocation extends BmobObject {
    private String CollocationName;
    private String CollocationPicture;
    private String CollocationEfficacy;
    private String CollocationIngredients;
    private String StepPictures;
    private String StepDetails;

    public String getCollocationName() {
        return CollocationName;
    }

    public void setCollocationName(String collocationName) {
        CollocationName = collocationName;
    }

    public String getCollocationPicture() {
        return CollocationPicture;
    }

    public void setCollocationPicture(String collocationPicture) {
        CollocationPicture = collocationPicture;
    }

    public String getCollocationEfficacy() {
        return CollocationEfficacy;
    }

    public void setCollocationEfficacy(String collocationEfficacy) {
        CollocationEfficacy = collocationEfficacy;
    }

    public String getCollocationIngredients() {
        return CollocationIngredients;
    }

    public void setCollocationIngredients(String collocationIngredients) {
        CollocationIngredients = collocationIngredients;
    }

    public String getStepPictures() {
        return StepPictures;
    }

    public void setStepPictures(String stepPictures) {
        StepPictures = stepPictures;
    }

    public String getStepDetails() {
        return StepDetails;
    }

    public void setStepDetails(String stepDetails) {
        StepDetails = stepDetails;
    }
}
