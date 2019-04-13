package com.zxu.masterofpainting.HealthPreserve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.Adapter.EfficacyAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.Recommend.SolarTeaAdapter;
import com.zxu.masterofpainting.bean.EfficacyItem;
import com.zxu.masterofpainting.bean.Recommend;
import com.zxu.masterofpainting.bean.SolarTea;

import java.util.ArrayList;
import java.util.List;

public class SolarDetailActivity extends AppCompatActivity {
    private String imagUrl;
    private String solarTeaIntr;
    private String solarFruitsIntr;
    private String solarIntrr;
    private SimpleDraweeView solarSimpleDraweeView;
    private SimpleDraweeView solarTeaSimpleDraweeView;
    private RecyclerView solarFruitsRecyclerView;
    private TextView teaTitle;
    private TextView teaIntr;
    private TextView solarIntr;
    private List<SolarTea> mSolarTeaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_detail);
        initView();
        //setAllData();
    }

    private void initView() {
        Intent intent = getIntent();
        imagUrl = intent.getStringExtra("imagUrl");
        solarTeaIntr = intent.getStringExtra("solarTea");
        solarFruitsIntr = intent.getStringExtra("solarFruits");
        solarIntrr = intent.getStringExtra("solarIntr");
        solarSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.solar_detai_imag);

        solarIntr = (TextView) findViewById(R.id.solar_intr);

        teaTitle = (TextView) findViewById(R.id.tea_title);
        teaIntr = (TextView) findViewById(R.id.tea_intr);
        solarTeaSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.tea_sv);

        solarFruitsRecyclerView = (RecyclerView) findViewById(R.id.fruits_rv);

        solarSimpleDraweeView.setImageURI(imagUrl);
        setSolarData();
        setTeaData();
        setFruitsData();
    }

    private void setSolarData(){
        solarIntr.setText(solarIntrr);
    }

    private void setTeaData(){
        String[] teaSplit = solarTeaIntr.split("@");
        if (teaSplit.length == 3) {
            teaTitle.setText(teaSplit[0]);
            teaIntr.setText(teaSplit[2]);
            solarTeaSimpleDraweeView.setImageURI(teaSplit[1]);
        }
    }
    private void setFruitsData(){
        String[] fruitsSplit = solarFruitsIntr.split("#");
        for (int i = 0; i < fruitsSplit.length; i++) {
            String[] strings = fruitsSplit[i].split("@");
            if (strings.length == 3) {
                mSolarTeaList.add(new SolarTea(strings[0], strings[1], strings[2]));
            }
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        solarFruitsRecyclerView.setLayoutManager(layoutManager);
        SolarTeaAdapter solarTeaAdapter = new SolarTeaAdapter(mSolarTeaList);
        solarFruitsRecyclerView.setAdapter(solarTeaAdapter);
    }
}
