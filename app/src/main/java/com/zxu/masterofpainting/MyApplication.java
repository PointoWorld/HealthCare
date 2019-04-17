package com.zxu.masterofpainting;

import android.app.Application;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zxu.masterofpainting.bean.User;

import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MyApplication extends Application {
    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();

        initializationBmob();
        Fresco.initialize(this);
        myApplication = this;

        setCurrentUser();
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

    private void setCurrentUser(){
        if (null != BmobUser.getCurrentUser(User.class)) {
            BmobQuery<User> userBmobQuery = new BmobQuery<>("_User");
            userBmobQuery.findObjects(new FindListener<User>() {
                @Override
                public void done(List<User> list, BmobException e) {
                    if (null == e && null != list) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getUsername().equals(BmobUser.getCurrentUser(User.class).getUsername())) {
                                BmobUser.getCurrentUser(User.class).setTestState(list.get(i).getTestState());
                                BmobUser.getCurrentUser(User.class).setGender(list.get(i).getGender());
                                BmobUser.getCurrentUser(User.class).setCupcategory(list.get(i).getCupcategory());
                                BmobUser.getCurrentUser(User.class).setCupSum(list.get(i).getCupSum());
                                BmobUser.getCurrentUser(User.class).setTeaRecord(list.get(i).getTeaRecord());
                                break;
                            }
                        }
                    }
                }
            });
        } else {
            //当前没登录
        }
    }
}
