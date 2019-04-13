package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.view.OnSignedSuccess;
import com.zxu.masterofpainting.view.SignDate;

public class ClockActivity extends AppCompatActivity implements View.OnClickListener{
    private SignDate signDate;
    private TextView teaSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        initView();
        loadCalendar();
    }

    private void initView() {
        findViewById(R.id.img_jian).setOnClickListener(this);
        findViewById(R.id.img_jia).setOnClickListener(this);
        findViewById(R.id.btn_daka).setOnClickListener(this);
        teaSum = (TextView) findViewById(R.id.sum_tea);
    }
    private void loadCalendar(){
        signDate = findViewById(R.id.signDate);
        signDate.setOnSignedSuccess(new OnSignedSuccess() {
            @Override
            public void OnSignedSuccess() {
                Log.e("wqf","Success");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_jia:
                teaSum.setText(String.valueOf(Integer.parseInt(teaSum.getText().toString())+1));
                break;
            case R.id.img_jian:
                if (Integer.parseInt(teaSum.getText().toString()) > 0) {
                    teaSum.setText(String.valueOf(Integer.parseInt(teaSum.getText().toString())-1));
                } else {
                    Toast.makeText(this, "不能再减啦", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_daka:
                Toast.makeText(this, "打卡完成！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
