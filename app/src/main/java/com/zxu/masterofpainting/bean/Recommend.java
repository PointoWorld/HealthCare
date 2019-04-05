package com.zxu.masterofpainting.bean;

public class Recommend {
    private String pictureUrl;
    private String recommendIngredientsName;

    public Recommend(String pictureUrl, String recommendIngredientsName) {
        this.pictureUrl = pictureUrl;
        this.recommendIngredientsName = recommendIngredientsName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRecommendIngredientsName() {
        return recommendIngredientsName;
    }

    public void setRecommendIngredientsName(String recommendIngredientsName) {
        this.recommendIngredientsName = recommendIngredientsName;
    }
}
