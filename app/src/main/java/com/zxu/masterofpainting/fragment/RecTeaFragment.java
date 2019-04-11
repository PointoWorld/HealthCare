package com.zxu.masterofpainting.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxu.masterofpainting.R;

public class RecTeaFragment extends Fragment {

    private String mTitle;

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
        return view;
    }

    private void initView(View view) {

    }

}
