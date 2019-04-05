package com.zxu.masterofpainting.bean;

public class Step {
    private String serialNumber;
    private String pictureUrl;
    private String stepDetails;

    public Step(String serialNumber, String pictureUrl, String stepDetails) {
        this.serialNumber = serialNumber;
        this.pictureUrl = pictureUrl;
        this.stepDetails = stepDetails;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getStepDetails() {
        return stepDetails;
    }

    public void setStepDetails(String stepDetails) {
        this.stepDetails = stepDetails;
    }
}
