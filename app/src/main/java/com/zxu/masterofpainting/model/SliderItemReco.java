package com.zxu.masterofpainting.model;

public class SliderItemReco {
    private String imageUrl;
    private String collocationName;

    public SliderItemReco(String imageUrl, String collocationName) {
        this.imageUrl = imageUrl;
        this.collocationName = collocationName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCollocationName() {
        return collocationName;
    }

    public void setCollocationName(String collocationName) {
        this.collocationName = collocationName;
    }
}
