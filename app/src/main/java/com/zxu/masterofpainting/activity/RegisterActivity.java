package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nickName;
    private EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        findViewById(R.id.btn_register).setOnClickListener(this);
        nickName = (EditText) findViewById(R.id.register_nickname);
        passWord = (EditText) findViewById(R.id.register_password);
    }

    private void Register(){
        User user = new User();
        user.setUsername(nickName.getText().toString());
        user.setPassword(passWord.getText().toString());
        user.setTestState(0+"");
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (null == e) {
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                Register();
                break;
        }
    }

}
