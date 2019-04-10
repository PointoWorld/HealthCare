package com.zxu.masterofpainting.fragment;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.joaquimley.faboptions.FabOptions;
import com.zxu.masterofpainting.Adapter.HomeAdapter;
import com.zxu.masterofpainting.Adapter.HorizontalPagerAdapter;
import com.zxu.masterofpainting.Adapter.LabelMenuAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.SmartCombinationActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NurseFragment extends Fragment implements View.OnClickListener{
    private FabOptions mFabOptions;
    private LinearLayout circleBack;
    private Button selectbtn;
    private Button zuhebtn;
    private Button nobtn;
    public static String TAG = "nurseFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nurse,container,false);
        initView(view);
        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext(), false));
        return view;
    }

    private void initView(View view) {
        mFabOptions = view.findViewById(R.id.fab_options);
        mFabOptions.setOnClickListener(this);
        circleBack = (LinearLayout) view.findViewById(R.id.circle_back);
    }

//    @SuppressLint("ResourceAsColor")
//    private void select(){
//        //circleBack.setBackgroundColor(getResources().getColor(R.color.colorAccentLight));
//        Toast.makeText(getContext(), "开始选择", Toast.LENGTH_SHORT).show();
//        Constants.isSelected = 1;
//        Constants.selectedLable.clear();
//    }
//    private void startZuHe(){
//        Toast.makeText(getContext(), "组合了"+Constants.selectedLable.size()+"个元素", Toast.LENGTH_SHORT).show();
//    }
//    @SuppressLint("ResourceAsColor")
//    private void noSelect(){
//        //circleBack.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//        Toast.makeText(getContext(), "取消组合成功！", Toast.LENGTH_SHORT).show();
//        Constants.selectedLable.clear();
//        Constants.isSelected = 0;
//    }




//    private void loadData(){
//        labelNameMenu = Constants.labelNameMenu;
//        for (int i = 0; i < labelNameMenu.length; i++) {
//            menuList.add(labelNameMenu[i]);
//            showTitle.add(i);
//            labelItemContent.add(Constants.labelMenuContent[i]);
//        }
//        tv_title.setText(Constants.labelNameMenu[0]);
//        menuAdapter.notifyDataSetChanged();
//        homeAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.faboptions_favorite:
                mFabOptions.setButtonColor(R.id.faboptions_favorite, R.color.colorAccent);
                Toast.makeText(getContext(), "单选模式", Toast.LENGTH_SHORT).show();
                Constants.selectedLable.clear();
                Constants.isSelected = 0;
                break;
//            case R.id.faboptions_textsms:
//                mFabOptions.setButtonColor(R.id.faboptions_textsms, R.color.colorAccent);
//                Toast.makeText(getContext(), "Message", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.faboptions_share:
                mFabOptions.setButtonColor(R.id.faboptions_share, R.color.colorAccent);
                Toast.makeText(getContext(), "多选模式", Toast.LENGTH_SHORT).show();
                Constants.isSelected = 1;
                Constants.selectedLable.clear();
                break;

            case R.id.start_zuhe:
                startActivity(new Intent(getContext(),SmartCombinationActivity.class));
                //mFabOptions.setButtonColor(R.id.start_zuhe, R.color.colorAccent);
                Toast.makeText(getContext(), "组合了"+Constants.selectedLable.size()+"个元素", Toast.LENGTH_SHORT).show();
                break;

            default:
                // no-op
        }
    }
}
