package com.zxu.masterofpainting.HealthPreserve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Hour;
import com.zxu.masterofpainting.bean.SolarTerms;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HourActivity extends AppCompatActivity {
    private List<SolarTerms> mSolarTermsList = new ArrayList<>();
    private List<Hour> mHourList = new ArrayList<>();
    private RecyclerView hourRecyclerView;
    private SimpleDraweeView hourSimpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour);
        initView();
        LoadData();
    }
    private void initView() {
        hourRecyclerView = (RecyclerView) findViewById(R.id.hour_recycler_view);
        hourSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.hour_simple_view);
    }
    private void LoadData(){
        BmobQuery<Hour> hourTermsBmobQuery = new BmobQuery<>("Hour");
        hourTermsBmobQuery.findObjects(new FindListener<Hour>() {
            @Override
            public void done(List<Hour> list, BmobException e) {
                if (e == null && list != null) {
                    if (e == null && list != null) {
                        for (int i = 0; i < list.size(); i++) {
                            mSolarTermsList.add(new SolarTerms(list.get(i).getHourName(),list.get(i).getImageUrl(), list.get(i).getIntr()));
                            mHourList.add(new Hour(list.get(i).getHourName(), list.get(i).getIntr(), list.get(i).getImageUrl()));
                        }
                        setAllData();
                    } else {
                        Toast.makeText(HourActivity.this, "呀，没找到数据", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(HourActivity.this, "呀，没找到数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAllData(){
        hourSimpleDraweeView.setImageURI("http://5b0988e595225.cdn.sohucs.com/images/20180628/e3ce77a744994026bd97f0d6532aa6ff.jpeg");
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        hourRecyclerView.setLayoutManager(layoutManager);
        SolarTermsAdapter solarTermsAdapter = new SolarTermsAdapter(mSolarTermsList);
        hourRecyclerView.setAdapter(solarTermsAdapter);
    }
}
