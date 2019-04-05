package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.zxu.masterofpainting.R;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.btn_back)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    @OnClick(R.id.btn_back)
    void back(){
        finish();
    }
}
