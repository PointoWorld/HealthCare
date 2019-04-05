package com.zxu.masterofpainting.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxu.masterofpainting.Adapter.EfficacyAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.EfficacyItem;

import java.util.ArrayList;
import java.util.List;

public class EdibleEfficacyFragment extends Fragment {
    private String edibleEfficacyStr;
    private RecyclerView efficacyrecyclerview;
    private EfficacyAdapter efficacyAdapter;
    private List<EfficacyItem> efficacyItemList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edible_efficacy, container, false);
        initView(view);
        getData();
        return view;
    }

    private void initView(View view) {
        efficacyrecyclerview = (RecyclerView) view.findViewById(R.id.efficacy_recycler_view);
    }

    private void getData() {
        if (Constants.ingredientsEfficiency != null) {
            edibleEfficacyStr = Constants.ingredientsEfficiency;
            String[] splitstring = edibleEfficacyStr.substring(1,edibleEfficacyStr.length()).split("\"");
            for (int i = 0; i < splitstring.length / 2; i++) {
                efficacyItemList.add(new EfficacyItem(splitstring[i*2], splitstring[i*2 + 1]));
            }
            setEfficacyListView();
        } else {

        }
    }

    private void setEfficacyListView() {
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        efficacyrecyclerview.setLayoutManager(layoutManager);
        EfficacyAdapter efficacyAdapter = new EfficacyAdapter(efficacyItemList);
        efficacyrecyclerview.setAdapter(efficacyAdapter);
    }
}
