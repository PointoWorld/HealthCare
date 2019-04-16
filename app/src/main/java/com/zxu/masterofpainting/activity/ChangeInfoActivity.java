package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ChangeInfoActivity extends AppCompatActivity {
    private EditText name;
    private EditText gender;
    private EditText tel;
    private EditText email;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        name = (EditText) findViewById(R.id.name_change_tv);
        gender = (EditText) findViewById(R.id.gender_change_tv);
        tel = (EditText) findViewById(R.id.number_change_tv);
        email = (EditText) findViewById(R.id.email_change_tv);
        button = (Button) findViewById(R.id.btn_change_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.getCurrentUser(User.class).setUsername(name.getText().toString());
                BmobUser.getCurrentUser(User.class).setGender(gender.getText().toString());
                BmobUser.getCurrentUser(User.class).setMobilePhoneNumber(tel.getText().toString());
                BmobUser.getCurrentUser(User.class).setEmail(email.getText().toString());
                User user = new User();
                user.setObjectId(BmobUser.getCurrentUser(User.class).getObjectId());
                user.setUsername(name.getText().toString());
                user.setGender(gender.getText().toString());
                user.setMobilePhoneNumber(tel.getText().toString());
                user.setEmail(email.getText().toString());
                user.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (null == e) {
                            Toast.makeText(ChangeInfoActivity.this, "修改失败！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ChangeInfoActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
