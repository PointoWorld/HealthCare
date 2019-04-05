package com.zxu.masterofpainting.bean;

public class EfficacyItem {
    private String efficacytitle; //功效标题
    private String efficacycontent; //功效内容

    public EfficacyItem(String efficacytitle, String efficacycontent) {
        this.efficacytitle = efficacytitle;
        this.efficacycontent = efficacycontent;
    }

    public String getEfficacytitle() {
        return efficacytitle;
    }

    public void setEfficacytitle(String efficacytitle) {
        this.efficacytitle = efficacytitle;
    }

    public String getEfficacycontent() {
        return efficacycontent;
    }

    public void setEfficacycontent(String efficacycontent) {
        this.efficacycontent = efficacycontent;
    }
}
