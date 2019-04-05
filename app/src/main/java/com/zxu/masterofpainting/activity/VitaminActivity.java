package com.zxu.masterofpainting.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.MaterialAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Material;
import com.zxu.masterofpainting.bean.Vitamin;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class VitaminActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamin);

        initView();
    }

    private void initView() {
        findViewById(R.id.vitaminA).setOnClickListener(this);
        findViewById(R.id.vitaminB1).setOnClickListener(this);
        findViewById(R.id.vitaminB2).setOnClickListener(this);
        findViewById(R.id.vitaminB3).setOnClickListener(this);
        findViewById(R.id.vitaminB5).setOnClickListener(this);
        findViewById(R.id.vitaminB6).setOnClickListener(this);
        findViewById(R.id.vitaminC).setOnClickListener(this);
        findViewById(R.id.vitaminD).setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vitaminA:
                Intent intent = new Intent(this,VitaminInfoActivity.class);
                intent.putExtra("vitaminCategory", "维生素A");
                startActivity(intent);
                break;
            case R.id.vitaminB1:
                Intent intent2 = new Intent(this,VitaminInfoActivity.class);
                intent2.putExtra("vitaminCategory", "维生素B1");
                startActivity(intent2);
                break;
            case R.id.vitaminB2:
                Intent intent3 = new Intent(this,VitaminInfoActivity.class);
                intent3.putExtra("vitaminCategory", "维生素B3");
                startActivity(intent3);
                break;
            case R.id.vitaminB3:
                Intent intent4 = new Intent(this,VitaminInfoActivity.class);
                intent4.putExtra("vitaminCategory", "维生素B3");
                startActivity(intent4);
                break;
            case R.id.vitaminB5:
                Intent intent5 = new Intent(this,VitaminInfoActivity.class);
                intent5.putExtra("vitaminCategory", "维生素B5");
                startActivity(intent5);
                break;
            case R.id.vitaminB6:
                Intent intent6 = new Intent(this,VitaminInfoActivity.class);
                intent6.putExtra("vitaminCategory", "维生素B6");
                startActivity(intent6);
                break;
            case R.id.vitaminC:
                Intent intent7 = new Intent(this,VitaminInfoActivity.class);
                intent7.putExtra("vitaminCategory", "维生素C");
                startActivity(intent7);
                break;
            case R.id.vitaminD:
                Intent intent8 = new Intent(this,VitaminInfoActivity.class);
                intent8.putExtra("vitaminCategory", "维生素D");
                startActivity(intent8);
                break;
        }
    }
}
