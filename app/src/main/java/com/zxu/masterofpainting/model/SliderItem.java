package com.zxu.masterofpainting.model;

public class SliderItem {
    private String ingredientsName;
    private String sliderIntroduce;
    private String recommendCollocation;

    public SliderItem(String ingredientsName, String sliderIntroduce, String recommendCollocation) {
        this.ingredientsName = ingredientsName;
        this.sliderIntroduce = sliderIntroduce;
        this.recommendCollocation = recommendCollocation;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }

    public String getSliderIntroduce() {
        return sliderIntroduce;
    }

    public void setSliderIntroduce(String sliderIntroduce) {
        this.sliderIntroduce = sliderIntroduce;
    }

    public String getRecommendCollocation() {
        return recommendCollocation;
    }

    public void setRecommendCollocation(String recommendCollocation) {
        this.recommendCollocation = recommendCollocation;
    }
}
