package com.zxu.masterofpainting.Cha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdentifyFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<ItemSteps> mMtemStepsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_identify, container, false);

        initView(view);
        //loadData();
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.tea_identify_recycler_view);
        loadData();
    }

    private void loadData(){
        String[] split = Constants.teaidentify.split("#");
        for (int i = 0; i < split.length; i++) {
            String[] itemSplit = split[i].split("@");
            mMtemStepsList.add(new ItemSteps(itemSplit[0], itemSplit[1], itemSplit[2]));
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        TeaStepsAdapter teaStepsAdapter = new TeaStepsAdapter(mMtemStepsList);
        recyclerView.setAdapter(teaStepsAdapter);
    }

}
