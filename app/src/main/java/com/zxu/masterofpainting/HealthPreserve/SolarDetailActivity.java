package com.zxu.masterofpainting.HealthPreserve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.Adapter.EfficacyAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.EfficacyItem;

import java.util.ArrayList;
import java.util.List;

public class SolarDetailActivity extends AppCompatActivity {
    private String imagUrl;
    private String solarIntr;
    private SimpleDraweeView solarSimpleDraweeView;
    private RecyclerView solarRecyclerView;
    private List<EfficacyItem> mEfficacyItemList = new ArrayList<>();

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
        solarIntr = intent.getStringExtra("solarIntr");
        solarSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.solar_detai_imag);
        solarRecyclerView = (RecyclerView) findViewById(R.id.solar_detai_recycler_view);
        setAllData();
    }

    private void setAllData(){
        if (solarIntr != null) {
            String[] split = solarIntr.split("#");
            for (int i = 0; i < split.length; i++) {
                mEfficacyItemList.add(new EfficacyItem(split[i].split("@")[0], split[i].split("@")[1]));
            }
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
            solarRecyclerView.setLayoutManager(layoutManager);
            EfficacyAdapter solarDetaiAdapter = new EfficacyAdapter(mEfficacyItemList);
            solarSimpleDraweeView.setImageURI(imagUrl);
            solarRecyclerView.setAdapter(solarDetaiAdapter);
        } else if (imagUrl!=null){
            Toast.makeText(this, "imagurl != null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
//        String[] split = solarIntr.split("#");
//        for (int i = 0; i < split.length; i++) {
//            mEfficacyItemList.add(new EfficacyItem(split[i].split("@")[0], split[i].split("@")[1]));
//        }
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
//        solarRecyclerView.setLayoutManager(layoutManager);
//        EfficacyAdapter solarDetaiAdapter = new EfficacyAdapter(mEfficacyItemList);
//        solarSimpleDraweeView.setImageURI(imagUrl);
//        solarRecyclerView.setAdapter(solarDetaiAdapter);
    }
}
