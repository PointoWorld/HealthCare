package com.zxu.masterofpainting.Cha;

public class ItemSteps {
    private String stepTitle;
    private String stepContent;
    private String stepImgUrl;

    public ItemSteps(String stepTitle, String stepContent, String stepImgUrl) {
        this.stepTitle = stepTitle;
        this.stepContent = stepContent;
        this.stepImgUrl = stepImgUrl;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;
    }

    public String getStepContent() {
        return stepContent;
    }

    public void setStepContent(String stepContent) {
        this.stepContent = stepContent;
    }

    public String getStepImgUrl() {
        return stepImgUrl;
    }

    public void setStepImgUrl(String stepImgUrl) {
        this.stepImgUrl = stepImgUrl;
    }
}
