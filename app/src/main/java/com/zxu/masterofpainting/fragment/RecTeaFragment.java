package com.zxu.masterofpainting.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.Cha.RecTeaAdapter;
import com.zxu.masterofpainting.Cha.Tea;
import com.zxu.masterofpainting.Cha.flourTea.FTAdapter;
import com.zxu.masterofpainting.Cha.flourTea.flowertea;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.Recommend.SolarTeaAdapter;
import com.zxu.masterofpainting.bean.Recommend;
import com.zxu.masterofpainting.bean.SolarTea;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class RecTeaFragment extends Fragment {
    private String mTitle;
    private RecyclerView recyclerView;
    private RecyclerView fTRecyclerView;
    private List<Recommend> mRecommendList = new ArrayList<>();
    private List<SolarTea> mSolarTeaList = new ArrayList<>();

    public static RecTeaFragment getInstance(String title) {
        RecTeaFragment sf = new RecTeaFragment();
        sf.mTitle = title;
        return sf;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rec_tea, container, false);
        initView(view);
        loadTeaDat();
        loadFlowerTeaDat();
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.rec_tea_rv);
        fTRecyclerView = view.findViewById(R.id.flower_tea_rv);
    }

    private void loadTeaDat(){
        BmobQuery<Tea> teaBmobQuery = new BmobQuery<>("Tea");
        teaBmobQuery.findObjects(new FindListener<Tea>() {
            @Override
            public void done(List<Tea> list, BmobException e) {
                if (null == e && null != list) {
                    for (int i = 0; i < list.size(); i++) {
                        mRecommendList.add(new Recommend(list.get(i).getImgUrl(),list.get(i).getTeaName()));
                    }
                    setTeaData();
                } else {
                    Toast.makeText(getContext(), "网络不好呦", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loadFlowerTeaDat(){
        BmobQuery<flowertea> flowerteaBmobQuery = new BmobQuery<>("flowertea");
        flowerteaBmobQuery.findObjects(new FindListener<flowertea>() {
            @Override
            public void done(List<flowertea> list, BmobException e) {
                for (int i = 0; i < list.size(); i++) {
                    String[] split = list.get(i).getGongXiao().split("@");
                    if (split.length == 2) {
                        mSolarTeaList.add(new SolarTea(list.get(i).getName(), list.get(i).getImgUrl(), split[1]));
                    }
                }
                setFlowerTeaData();
            }
        });
    }

    private void setTeaData(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecTeaAdapter recTeaAdapter = new RecTeaAdapter(mRecommendList);
        recyclerView.setAdapter(recTeaAdapter);
    }
    private void setFlowerTeaData(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        fTRecyclerView.setLayoutManager(layoutManager);
        FTAdapter ftAdapter = new FTAdapter(mSolarTeaList);
        fTRecyclerView.setAdapter(ftAdapter);
    }
}
