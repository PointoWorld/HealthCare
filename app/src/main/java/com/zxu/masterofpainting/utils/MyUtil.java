package com.zxu.masterofpainting.utils;

import com.zxu.masterofpainting.bean.NutritionItem;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                + "grant_type=client_credentials"
                + "&client_id=" + ak
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
//            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return null;
    }
}
