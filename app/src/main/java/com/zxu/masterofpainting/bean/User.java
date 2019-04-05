package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String nickName;
    private String passWord;
    private String testState;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTestState() {
        return testState;
    }

    public void setTestState(String testState) {
        this.testState = testState;
    }
}
