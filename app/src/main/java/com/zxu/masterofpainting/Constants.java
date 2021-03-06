package com.zxu.masterofpainting;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static String ingredientsName;
    public static String ingredientsImgUrl;
    public static String ingredientsNutrution;
    public static String ingredientsEfficiency;
    public static String ingredientsSuitableCollocation;
    public static String teaName;
    public static String teaUrl;
    public static String teaIntr;
    public static String teachongPao;
    public static String teasteps;
    public static String teaidentify;


    public static String fteaEffect;
    public static String fteaYuanLiao;
    public static String fteasteps;
    public static String fteaimgUrl;
    public static String fteaName;
    public static String fteaIntrVideo;
    public static String fteaIntrtext;
    public static String fteastepsVideo;
    public static String fteastepstext;

    public static final String[] medicine = {"山药","人参","燕窝","阿胶","茯苓","党参","当归","蜂王浆","百合干","苦豆子","马鞭草","咖啡豆","甘草","川贝","桔梗","芭蕉叶","橘皮","决明子","苦参","鼠尾草","党参","太子参"};
    public static final String[] labelNameMenu = {"症状","补养","美容养颜","保健调养"};
    public static final String[][] labelMenuContent = {
    {"高血糖","高血压","高血脂","低血糖","胆固醇高","胃炎","腹泻","咳嗽","消化不良","便秘","肾亏","止咳","发烧","降火","缺钾","中暑","热感冒"
            ,"咳嗽有痰","扁桃体发炎"}
    ,{"解酒","脱发","养胃","补血","健脾","养肝","补气血","养肺","补脑","失眠","健忘","抑郁","补肾","瘦肚子","上火","抗疲劳","压力大","加快代谢"}
    ,{"美容","养颜","排毒","减肥","瘦身","瘦脸","瘦腿","抗皱","祛斑","美白","润肤","延缓衰老","护发","去黑眼圈","祛痘"}
    ,{"清热去火","疏肝理气","明目","降血压","降血糖","降血脂","增强免疫力"}
    };

//    public static final String[][] cardQuestion= {{"您容易疲乏吗？","您说话声音低弱无力吗？","您闷闷不乐、情绪低落吗？","您比一般人耐受不了寒冷（冬天的寒冷、夏天的空调等）吗？","您能适应外界自然和社会环境的变化吗？","您容易失眠吗？","您容易忘事（健忘）吗？"}
//    ,{"您容易疲乏吗？","您容易气短（呼吸短促、接不上气）吗？","您容易心慌吗？","您容易头晕或站起时眩晕吗？","您比别人容易患感冒吗？","您说话声音低弱无力吗？","您活动量稍大就出虚汗吗？"}
//    ,{"您总是感到手脚发凉吗？","您胃部、背部或腰膝部怕冷吗？","您感到怕冷、衣服比别人穿得多吗？","您比一般人耐受不了寒冷（冬天的寒冷，夏天的空调、电扇等）吗？","您比别人容易患感冒吗？","您吃（喝）凉的东西会感到不舒服或者怕吃（喝）凉的东西吗？","您受凉或吃（喝）凉的东西后，容易腹泻或者拉肚子吗？"}
//    ,{"您感到手脚心发热吗？","您感觉身体、脸上发热吗？","您皮肤或口唇干吗？","您口唇的颜色比一般人红吗？","您容易便秘或大便干燥吗？","您感到眼睛干涩吗？","您感到口干舌燥、总想喝水吗？"}
//    ,{"您感到胸闷或腹部胀满吗？","您腹部肥满松软吗？","您额部油脂分泌多吗？","您上眼睑比别人肿（上眼睑有轻微隆起的现象）吗？","您嘴里有黏黏的感觉吗？","您平时痰多，特别是咽喉部总感到有痰堵着吗？","您舌苔厚腻或有舌苔厚厚的感觉吗？"}
//    ,{"您鼻部油腻或油光发亮吗？","您易生痤疮或疮疖吗？","您感到口苦或嘴里有异味吗？","您大便粘滞不爽、有解不尽的感觉吗？","您小便时尿道有发热感、尿色浓（深）吗？","您带下色黄（白带颜色发黄）吗？（限女性回答）","您的阴囊部位潮湿吗？（限男性回答）"}
//    ,{"您的皮肤在不知不觉中会出现青紫瘀斑（皮下出血）吗？","您两颧部有细微红丝吗？","您身体上有哪些疼痛吗？","您面色晦暗或容易出现褐斑吗？","您容易有黑眼圈吗？","您容易忘事（健忘）吗？","您口唇颜色的偏暗吗？"}
//    ,{"您感到闷闷不乐、情绪低落吗？","您容易精神紧张、焦虑不安吗？","您多愁善感、感情脆弱吗？","您容易感到害怕或受到惊吓吗？","您胸肋部或乳房胀痛吗？","您无缘无故叹气吗？","您咽喉部有异物感，且吐之不出、咽之不下吗？"}
//    ,{"您没有感冒时也会打喷嚏吗？","您没有感冒时也会鼻塞、流鼻涕吗？","您有因季节变化、温度变化或异味等原因而咳喘的现象吗？","您容易过敏（对药物、食物、气味、花粉或在季节交替、气候变化时）吗？","您的皮肤容易起荨麻疹（风团、风疹块、风疙瘩）吗？","您的皮肤因过敏出现过紫癜紫红色淤点、瘀斑）吗？","您的皮肤一抓就红，并出现抓痕吗？"}};

    public static final String[][] cardQuestion= {{"您容易疲乏吗？","您说话声音低弱无力吗？","您闷闷不乐、情绪低落吗？","您比一般人耐受不了寒冷（冬天的寒冷、夏天的空调等）吗？","您能适应外界自然和社会环境的变化吗？","您容易失眠吗？","您容易忘事（健忘）吗？"}
            ,{"您容易疲乏吗？","您容易气短（呼吸短促、接不上气）吗？","您容易心慌吗？","您容易头晕或站起时眩晕吗？","您比别人容易患感冒吗？","您说话声音低弱无力吗？","您活动量稍大就出虚汗吗？"}};
    public static final String[] physiqueStr = {"平和质", "气虚质", "阳虚型", "阴虚型", " 痰湿型", "湿热型", "血淤型", "气郁型", "特禀型"};

    public static int isSelected = 0;
    public static List<String> selectedLable = new ArrayList<>();
    public static String[] solar = {"立春","雨水","惊蛰","春分","清明","谷雨","立夏"
            ,"小满","芒种","夏至","小暑","大暑"
             ,"立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至","小寒","大寒"};
    public static String[] hour = {"辰时","巳时","午时","未时","申时","酉时","戌时","亥时","子时","丑时","寅时","卯时"};
}
