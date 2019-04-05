package com.zxu.masterofpainting.bean;

import cn.bmob.v3.BmobObject;

public class ChaCare extends BmobObject {
    private String title;
    private String imagUrl;
    private String content;

    public ChaCare(String title, String imagUrl, String content) {
        this.title = title;
        this.imagUrl = imagUrl;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
