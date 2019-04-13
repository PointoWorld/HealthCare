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
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Recommend;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class RecTeaFragment extends Fragment {
    private SimpleDraweeView simpleDraweeView;
    private String mTitle;
    private RecyclerView recyclerView;
    private List<Tea> mTeaList  = new ArrayList<>();
    private List<Recommend> mRecommendList = new ArrayList<>();

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
        loadDat();
        return view;
    }

    private void initView(View view) {
        simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.tea_sd);
        simpleDraweeView.setImageURI("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=8b537a3b63380cd7e61ea5eb997fca09/72f082025aafa40f70d64b56ad64034f78f0192c.jpg");
        recyclerView = view.findViewById(R.id.rec_tea_rv);
    }

    private void loadDat(){
        BmobQuery<Tea> teaBmobQuery = new BmobQuery<>("Tea");
        teaBmobQuery.findObjects(new FindListener<Tea>() {
            @Override
            public void done(List<Tea> list, BmobException e) {
                if (null == e && null != list) {
                    for (int i = 0; i < list.size(); i++) {
                        mRecommendList.add(new Recommend(list.get(i).getImgUrl(),list.get(i).getTeaName()));
                    }
                    setData();
                } else {
                    Toast.makeText(getContext(), "网络不好呦", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecTeaAdapter recTeaAdapter = new RecTeaAdapter(mRecommendList);
        recyclerView.setAdapter(recTeaAdapter);
    }
}
