package com.zxu.masterofpainting.HealthPreserve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.SolarTerms;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SolarTermsActivity extends AppCompatActivity {
    private List<SolarTerms> mSolarTermsList = new ArrayList<>();
    private RecyclerView solarRecyclerView;
    private SimpleDraweeView solarSimpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_terms);
        initView();
        LoadData();
    }

    private void initView() {
        solarRecyclerView = (RecyclerView) findViewById(R.id.solar_recycler_view);
        solarSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.solar_simple_view);
    }
    private void LoadData(){
        BmobQuery<SolarTerms> solarTermsBmobQuery = new BmobQuery<>("SolarTerms");
        solarTermsBmobQuery.findObjects(new FindListener<SolarTerms>() {
            @Override
            public void done(List<SolarTerms> list, BmobException e) {
                    if (e == null && list != null) {
                        for (int i = 0; i < list.size(); i++) {
                            mSolarTermsList.add(new SolarTerms(list.get(i).getSolarName(),list.get(i).getImageUrl(), list.get(i).getSolarTerms()));
                        }
                        setAllData();
                    } else {
                        Toast.makeText(SolarTermsActivity.this, "呀，没找到数据", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    private void setAllData(){
        solarSimpleDraweeView.setImageURI("https://res.keyunzhan.com/img/JieQiNew/20160311/1779310323.png");
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        solarRecyclerView.setLayoutManager(layoutManager);
        SolarTermsAdapter solarTermsAdapter = new SolarTermsAdapter(mSolarTermsList);
        solarRecyclerView.setAdapter(solarTermsAdapter);
    }
}
