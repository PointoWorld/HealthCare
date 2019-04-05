package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class Collection extends BmobObject {
    private String ObjectId;

    public Collection(){

    }

    public Collection(String objectId) {
        this.ObjectId = objectId;
    }

    public String getmObjectId() {
        return ObjectId;
    }

    public void setmObjectId(String mObjectId) {
        this.ObjectId = mObjectId;
    }
}
