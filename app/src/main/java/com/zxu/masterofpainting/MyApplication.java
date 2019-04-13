package com.zxu.masterofpainting;

import android.app.Application;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Calendar;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MyApplication extends Application {
    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();

        initializationBmob();
        Fresco.initialize(this);
        myApplication = this;
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
    private void initializationBmob() {
        Bmob.initialize(this,"865e1d41ba44fc9fa928add29b1ccbc0");
    }

    public int getMonth(){
        Calendar calendar = calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }
    public int getHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

}
