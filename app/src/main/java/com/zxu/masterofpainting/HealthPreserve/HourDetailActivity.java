package com.zxu.masterofpainting.HealthPreserve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.Adapter.EfficacyAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.EfficacyItem;
import com.zxu.masterofpainting.bean.Hour;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HourDetailActivity extends AppCompatActivity {
    private List<EfficacyItem> mEfficacyItemList = new ArrayList<>();
    private RecyclerView hourRecyclerView;
    private SimpleDraweeView hourSimpleDraweeView;
    private String hourName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour);
        initView();
    }
    private void initView() {
        hourRecyclerView = (RecyclerView) findViewById(R.id.hour_recycler_view);
        hourSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.hour_simple_view);
        Intent intent = getIntent();
        hourName = intent.getStringExtra("hourName");
        LoadData();
    }
    private void LoadData(){
        BmobQuery<Hour> hourTermsBmobQuery = new BmobQuery<>("Hour");
        hourTermsBmobQuery.findObjects(new FindListener<Hour>() {
            @Override
            public void done(List<Hour> list, BmobException e) {
                if (e == null && list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getHourName().equals(hourName)) {
                            setAllData(list.get(i).getImageUrl(), list.get(i).getIntr());
                            break;
                        }
                    }
                } else {
                    Toast.makeText(HourDetailActivity.this, "呀，没找到数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAllData(String imgUrl, String intr){
        hourSimpleDraweeView.setImageURI(imgUrl);
        String[] hourSplit = intr.split("#");
        for (int i = 0; i < hourSplit.length; i++) {
            String[] split = hourSplit[i].split("@");
            if (split.length == 2) {
                mEfficacyItemList.add(new EfficacyItem(split[0], split[1]));
            }
        }
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        hourRecyclerView.setLayoutManager(layoutManager);
        EfficacyAdapter efficacyAdapter = new EfficacyAdapter(mEfficacyItemList);
        hourRecyclerView.setAdapter(efficacyAdapter);
    }
}
