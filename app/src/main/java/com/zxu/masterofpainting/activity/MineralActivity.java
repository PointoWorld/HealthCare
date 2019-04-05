package com.zxu.masterofpainting.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zxu.masterofpainting.R;

public class MineralActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mineral);

        initView();
    }

    private void initView() {
        findViewById(R.id.protein).setOnClickListener(this);
        findViewById(R.id.calcium).setOnClickListener(this);
        findViewById(R.id.iron).setOnClickListener(this);
        findViewById(R.id.sodium).setOnClickListener(this);
        findViewById(R.id.potassium).setOnClickListener(this);
        findViewById(R.id.phosphorus).setOnClickListener(this);
        findViewById(R.id.zinc).setOnClickListener(this);
        findViewById(R.id.iodine).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.protein:
                startMyActivity("蛋白质");
                break;
            case R.id.calcium:
                startMyActivity("钙");
                break;
            case R.id.iron:
                startMyActivity("铁");
                break;
            case R.id.sodium:
                startMyActivity("钠");
                break;
            case R.id.potassium:
                startMyActivity("钾");
                break;
            case R.id.phosphorus:
                startMyActivity("磷");
                break;
            case R.id.zinc:
                startMyActivity("锌");
                break;
            case R.id.iodine:
                startMyActivity("碘");
                break;
        }
    }

    private void startMyActivity(String mineral) {
        Intent intent = new Intent(this,VitaminInfoActivity.class);
        intent.putExtra("vitaminCategory", mineral);
        startActivity(intent);
    }

}
