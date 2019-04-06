package com.zxu.masterofpainting.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagCloudView;
import com.moxun.tagcloudlib.view.TagsAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.LabelDetailActivity;
import com.zxu.masterofpainting.fragment.NurseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TextTagsAdapter extends TagsAdapter {
    private SharedPreferences sp;
    private int sx,sy;

    private List<String> dataSet = new ArrayList<>();
    private String[] strings;
    private TagCloudView view;

    public TextTagsAdapter(TagCloudView view, String[] strings) {
        this.view = view;
        this.strings = strings;
        //Collections.addAll(dataSet, data);
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public View getView(final Context context, final int position, final ViewGroup parent) {
        final TextView tv = new TextView(context);
        tv.setText(strings[position]);
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (Constants.isSelected == 0) {
                    Toast.makeText(context, "" + strings[position], Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,LabelDetailActivity.class);
                    intent.putExtra("labelName", strings[position]);
                    context.startActivity(intent);

                } else if (Constants.isSelected == 1) {
                    Constants.selectedLable.add(strings[position]);
                    //view.setBackgroundColor(R.color.colorAccent);
                    Toast.makeText(context, strings[position]+"已添加！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv.setTextColor(Color.rgb(255,111,58));
        tv.setTypeface(Typeface.SANS_SERIF);

        return tv;
    }



    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        view.setBackgroundColor(themeColor);
    }


}
