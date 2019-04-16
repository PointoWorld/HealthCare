package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText passWord;
    private EditText passWordConf;
    private EditText telNumber;
    private EditText email;
    private EditText user_name;
    private String gender;
    private boolean male;
    RadioGroup radgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        male = true;
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_register).setOnClickListener(this);
        passWord = (EditText) findViewById(R.id.register_password);
        telNumber = (EditText) findViewById(R.id.tel_number);
        passWordConf = (EditText) findViewById(R.id.register_password_confin);
        email = (EditText) findViewById(R.id.email);
        radgroup = (RadioGroup) findViewById(R.id.regis_radioGroup);
        user_name = (EditText) findViewById(R.id.user_name);
        radgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                if (radbtn.getText().toString().equals("女")) {
                    male = false;
                }
            }
        });
    }

    private void Register(){
        User user = new User();
        user.setUsername(user_name.getText().toString());
        user.setPassword(passWord.getText().toString());
        user.setTestState(0+"");
        user.setMobilePhoneNumber(telNumber.getText().toString());
        user.setEmail(email.getText().toString());
        if (male) {
            user.setGender("男");
        }else {
            user.setGender("女");
        }
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
                if (passWord.getText().toString().equals(passWordConf.getText().toString())) {
                    Register();
                } else {
                    Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
