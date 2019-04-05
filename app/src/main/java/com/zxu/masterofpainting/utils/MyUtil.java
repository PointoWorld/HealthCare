package com.zxu.masterofpainting.utils;

import com.zxu.masterofpainting.bean.NutritionItem;

import java.util.ArrayList;
import java.util.List;

public class MyUtil {
    public static List<NutritionItem> nutritionStringUtil(String nutritionString) {
        List<NutritionItem> ingredientsInformationList = new ArrayList<>();
        String[] str = nutritionString.split(";");
        
        return ingredientsInformationList;
    }

    public static int getScore(String frequency){
        if (frequency.equals("没有")) {
            return 1;
        } else if (frequency.equals("很少")) {
            return 2;
        } else if (frequency.equals("有时")) {
            return 3;
        } else if (frequency.equals("经常")) {
            return 4;
        }
        return 5;
    }
}
