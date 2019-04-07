package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.view.OnSignedSuccess;
import com.zxu.masterofpainting.view.SignDate;

public class ClockActivity extends AppCompatActivity {
    private SignDate signDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        initView();
        loadCalendar();
    }

    private void initView() {

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
}
