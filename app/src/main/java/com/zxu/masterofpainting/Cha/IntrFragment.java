package com.zxu.masterofpainting.Cha;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class IntrFragment extends Fragment {
    private RecyclerView chaIntrRecyclerView;
    private List<EfficacyItem> mEfficacyItemList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intr, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        chaIntrRecyclerView = (RecyclerView) view.findViewById(R.id.cha_intr_rv);
        loadData();
    }

    private void loadData(){
        String[] split = Constants.teaIntr.split("#");
        for (int i = 0; i < split.length; i++) {
            String[] itemSplit = split[i].split("@");
            mEfficacyItemList.add(new EfficacyItem(itemSplit[0], itemSplit[1]));
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        chaIntrRecyclerView.setLayoutManager(layoutManager);
        EfficacyAdapter solarDetaiAdapter = new EfficacyAdapter(mEfficacyItemList);
        chaIntrRecyclerView.setAdapter(solarDetaiAdapter);
    }
}
