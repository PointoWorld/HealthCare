package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String testState;
    private String gender;
    private String TeaRecord;
    private String cupSum;
    private String cupcategory;

    public String getCupSum() {
        return cupSum;
    }

    public void setCupSum(String cupSum) {
        this.cupSum = cupSum;
    }

    public String getCupcategory() {
        return cupcategory;
    }

    public void setCupcategory(String cupcategory) {
        this.cupcategory = cupcategory;
    }

    public String getTeaRecord() {
        return TeaRecord;
    }

    public void setTeaRecord(String teaRecord) {
        TeaRecord = teaRecord;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTestState() {
        return testState;
    }

    public void setTestState(String testState) {
        this.testState = testState;
    }
}
