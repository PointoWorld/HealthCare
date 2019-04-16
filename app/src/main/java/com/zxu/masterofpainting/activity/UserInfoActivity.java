package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.User;

import cn.bmob.v3.BmobUser;

public class UserInfoActivity extends AppCompatActivity {
    private TextView name;
    private TextView gender;
    private TextView tel;
    private TextView email;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        name = (TextView) findViewById(R.id.name_tv);
        gender = (TextView) findViewById(R.id.gender_tv);
        tel = (TextView) findViewById(R.id.number_tv);
        email = (TextView) findViewById(R.id.email_tv);
        button = (Button) findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (null != BmobUser.getCurrentUser(User.class)) {
            name.setText(BmobUser.getCurrentUser(User.class).getUsername());
            gender.setText(BmobUser.getCurrentUser(User.class).getGender());
            tel.setText(BmobUser.getCurrentUser(User.class).getMobilePhoneNumber());
            email.setText(BmobUser.getCurrentUser(User.class).getEmail());
        } else {

        }
    }
}
