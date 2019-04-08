package com.zxu.masterofpainting.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.CollocationAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.CollocationActivity;
import com.zxu.masterofpainting.bean.Collocation;
import com.zxu.masterofpainting.bean.SuitableAvoidItem;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SuitableAvoidFragment extends Fragment {
    //private String suitableAvoidStr;
    //private RecyclerView collocationRecyclerView;
    //private List<SuitableAvoidItem> mSuitableAvoidItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private List<Collocation> mCollocationList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suitable_avoid, container, false);
        initView(view);
        getAllData();
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.collocation_product_recyclerview_zhijie);
    }

    private void getAllData(){
//        if (Constants.ingredientsSuitableCollocation != null) {
//            suitableAvoidStr = Constants.ingredientsSuitableCollocation;
//            String[] splitString = suitableAvoidStr.split(";");
//            for (int j = 0; j < splitString.length; j++) {
//                String[] childsplit = splitString[j].split(",");
//                mSuitableAvoidItemList.add(new SuitableAvoidItem(childsplit[0], childsplit[1]));
//            }
//            setSuitableAvoidData();
//        } else {
//            Toast.makeText(getContext(), "哎呦~没有适宜搭配呀~", Toast.LENGTH_SHORT).show();
//        }
        BmobQuery<Collocation> collocationBmobQuery = new BmobQuery<>("CollocationActivity");
        collocationBmobQuery.setLimit(1000);
        collocationBmobQuery.findObjects(new FindListener<Collocation>() {
            @Override
            public void done(List<Collocation> list, BmobException e) {
                if (list != null) {
                    //Toast.makeText(getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < list.size(); i++) {
                        String collocationIngredients = list.get(i).getCollocationIngredients();
                        if (collocationIngredients.contains(Constants.ingredientsName)) {
                            mCollocationList.add(list.get(i));
                        }
                    }
                    setAllData();
                } else {
                    Toast.makeText(getContext(), "查询出错啦~", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAllData(){
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CollocationAdapter collocationAdapter = new CollocationAdapter(mCollocationList);
        recyclerView.setAdapter(collocationAdapter);
    }
}
