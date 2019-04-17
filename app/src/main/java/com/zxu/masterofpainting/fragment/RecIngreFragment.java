package com.zxu.masterofpainting.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.CollocationAdapter;
import com.zxu.masterofpainting.Adapter.ZuheIngAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.SmartCombinationActivity;
import com.zxu.masterofpainting.bean.Collocation;
import com.zxu.masterofpainting.bean.Ingredient1;
import com.zxu.masterofpainting.bean.Recommend;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class RecIngreFragment extends Fragment {
    private String mTitle;
    private RecyclerView collocationRecyclerView;
    private List<Recommend> mRecommendList = new ArrayList<>();
    private List<Collocation> mCollocationList = new ArrayList<>();

    public static RecIngreFragment getInstance(String title) {
        RecIngreFragment sf = new RecIngreFragment();
        sf.mTitle = title;
        return sf;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rec_ingre, container, false);

        initView(view);
        loadAllData();
        return view;
    }

    private void initView(View view) {
        collocationRecyclerView = (RecyclerView) view.findViewById(R.id.rec_collocation_rv);
    }

    private void loadAllData(){
        BmobQuery<Collocation> collocationBmobQuery = new BmobQuery<>("Collocation");
        collocationBmobQuery.findObjects(new FindListener<Collocation>() {
            @Override
            public void done(List<Collocation> list, BmobException e) {
                if (null == e && null != list) {
                    String s = "";
                    for (int i = 0; i < 20; i++) {
                        int i1 = (int) (1 + Math.random() * (115));
                        s = s + i1 + ";";
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (s.contains("" + i)) {
                            mCollocationList.add(list.get(i));
                        }
                    }
                    setCollocationData();
                } else {
                    Toast.makeText(getContext(), "网络可能不好呦", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void setIngredientsData(){
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
//        ingredientsRecyclerView.setLayoutManager(layoutManager);
//        ZuheIngAdapter recommendAdapter = new ZuheIngAdapter(mRecommendList);
//        ingredientsRecyclerView.setAdapter(recommendAdapter);
//    }
    private void setCollocationData(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        collocationRecyclerView.setLayoutManager(layoutManager);
        CollocationAdapter recommendAdapter = new CollocationAdapter(mCollocationList);
        collocationRecyclerView.setAdapter(recommendAdapter);
    }

}
