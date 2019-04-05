package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.SliderAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Slider;
import com.zxu.masterofpainting.model.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class SliderActivity extends AppCompatActivity {
    private TextView sliderIntr;
    private RecyclerView sliderRecycler;
    private List<SliderItem> sliderItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        initView();
    }

    private void initView() {
        String sliderIntrr = getIntent().getStringExtra("sliderintr");
        String sliderContent = getIntent().getStringExtra("slidercontent");
        sliderIntr = (TextView) findViewById(R.id.slider_intr);
        sliderRecycler = (RecyclerView) findViewById(R.id.slider_recycler_view);
        sliderIntr.setText(sliderIntrr);
        loadSliderData(sliderContent);
    }

    private void loadSliderData(String sliderContent) {
        String[] sliderSplit = sliderContent.split("@");
        Toast.makeText(this, ""+sliderSplit.length, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < sliderSplit.length; i++) {
            String[] itemSplit = sliderSplit[i].split("#");
            sliderItemList.add(new SliderItem(itemSplit[0], itemSplit[1], itemSplit[2]));
        }
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        sliderRecycler.setLayoutManager(layoutManager);
        SliderAdapter sliderAdapter = new SliderAdapter(sliderItemList);
        sliderRecycler.setAdapter(sliderAdapter);
    }

}
