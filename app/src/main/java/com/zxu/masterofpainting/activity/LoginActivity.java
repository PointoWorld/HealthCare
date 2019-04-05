package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zxu.masterofpainting.MyApplication;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText nickName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        nickName = (EditText) findViewById(R.id.login_nickname);
        password = (EditText) findViewById(R.id.login_password);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Login();
                break;
        }
    }

    private void Login() {
        if (nickName.getText().toString() != null && password.getText().toString() != null) {
            User user = new User();
            user.setUsername(nickName.getText().toString());
            user.setPassword(password.getText().toString());
            user.login(new SaveListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if (null == e) {
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
