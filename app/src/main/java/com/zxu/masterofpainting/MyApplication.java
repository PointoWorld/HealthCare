package com.zxu.masterofpainting;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MyApplication extends Application {
    public static MyApplication myApplication;

    public static MyApplication getInstance(){
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initializationBmob();
        Fresco.initialize(this);
    }

    private void initializationBmob() {
        Bmob.initialize(this,"865e1d41ba44fc9fa928add29b1ccbc0");

    }

}
