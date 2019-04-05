package com.zxu.masterofpainting.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.MyApplication;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.CollectionActivity;
import com.zxu.masterofpainting.activity.LoginActivity;
import com.zxu.masterofpainting.activity.RegisterActivity;
import com.zxu.masterofpainting.activity.TestingActivity;
import com.zxu.masterofpainting.activity.UserInfoActivity;
import com.zxu.masterofpainting.bean.User;

import cn.bmob.v3.BmobUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnClickListener{

    private SimpleDraweeView simpleDraweeView;
    private Button btnSignOut;
    private TextView logintxt;
    private TextView registertxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.collection_view).setOnClickListener(this);
        view.findViewById(R.id.btn_sign_out).setOnClickListener(this);
        view.findViewById(R.id.login).setOnClickListener(this);
        view.findViewById(R.id.register).setOnClickListener(this);
        view.findViewById(R.id.re_test).setOnClickListener(this);
        view.findViewById(R.id.my_info_view).setOnClickListener(this);
        simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.sdv_user_picture);
        if (BmobUser.getCurrentUser(User.class) != null) {
            simpleDraweeView.setImageURI("https://i8.meishichina.com/attachment/recipe/2018/08/10/2018081015339030872667138811491.jpg?x-oss-process=style/p800");
        } else {
            simpleDraweeView.setImageResource(R.drawable.nologin);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                Login();
                break;
            case R.id.register:
                Register();
                break;
            case R.id.collection_view:
                v.getContext().startActivity(new Intent(v.getContext(),CollectionActivity.class));
                break;
            case R.id.my_info_view:
                myInfo();
                break;
            case R.id.re_test:
                reTest();
                break;
            case R.id.btn_sign_out:
                //退出登录
                if (BmobUser.getCurrentUser(User.class) != null) {
                    BmobUser.getCurrentUser(User.class).logOut();
                    simpleDraweeView.setImageResource(R.drawable.nologin);
                    Toast.makeText(getContext(), "退出成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "您已经在退出状态", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void Login(){
        startActivity(new Intent(getContext(),LoginActivity.class));
    }
    private void Register(){
        //Toast.makeText(getContext(), "register", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(),RegisterActivity.class));
    }

    private void myInfo(){
        startActivity(new Intent(getContext(),UserInfoActivity.class));
    }

    private void reTest(){
        if (null != BmobUser.getCurrentUser(User.class)) {
            if (BmobUser.getCurrentUser(User.class).getTestState().equals("0")) {
                startActivity(new Intent(getContext(),TestingActivity.class));
            } else {
                Toast.makeText(getContext(), "您已经测试过了", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "您还没有登录呢", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onStart() {
        super.onStart();

        //Toast.makeText(getContext(), "onstart", Toast.LENGTH_SHORT).show();
        if (BmobUser.getCurrentUser(User.class) != null) {
            //Toast.makeText(getContext(), BmobUser.getCurrentUser(User.class).getUsername(), Toast.LENGTH_SHORT).show();
            simpleDraweeView.setImageURI("https://i8.meishichina.com/attachment/recipe/2018/08/10/2018081015339030872667138811491.jpg?x-oss-process=style/p800");

        } else {
            //Toast.makeText(getContext(), "用户空", Toast.LENGTH_SHORT).show();
            simpleDraweeView.setImageResource(R.drawable.nologin);
        }
    }
}
