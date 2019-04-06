package com.zxu.masterofpainting.fragment;



import android.annotation.SuppressLint;
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
import com.zxu.masterofpainting.Adapter.HomeAdapter;
import com.zxu.masterofpainting.Adapter.HorizontalPagerAdapter;
import com.zxu.masterofpainting.Adapter.LabelMenuAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NurseFragment extends Fragment{
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
        circleBack = (LinearLayout) view.findViewById(R.id.circle_back);
        selectbtn = (Button) view.findViewById(R.id.lable_select);
        selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select();
            }
        });
        zuhebtn = (Button) view.findViewById(R.id.lable_zuhe);
        zuhebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startZuHe();
            }
        });
        nobtn = (Button) view.findViewById(R.id.lable_no_select);
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noSelect();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void select(){
        //circleBack.setBackgroundColor(getResources().getColor(R.color.colorAccentLight));
        Toast.makeText(getContext(), "开始选择", Toast.LENGTH_SHORT).show();
        Constants.isSelected = 1;
        Constants.selectedLable.clear();
    }
    private void startZuHe(){
        Toast.makeText(getContext(), "组合了"+Constants.selectedLable.size()+"个元素", Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("ResourceAsColor")
    private void noSelect(){
        //circleBack.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        Toast.makeText(getContext(), "取消组合成功！", Toast.LENGTH_SHORT).show();
        Constants.selectedLable.clear();
        Constants.isSelected = 0;
    }





















    private void loadData(){
//        labelNameMenu = Constants.labelNameMenu;
//        for (int i = 0; i < labelNameMenu.length; i++) {
//            menuList.add(labelNameMenu[i]);
//            showTitle.add(i);
//            labelItemContent.add(Constants.labelMenuContent[i]);
//        }
//        tv_title.setText(Constants.labelNameMenu[0]);
//        menuAdapter.notifyDataSetChanged();
//        homeAdapter.notifyDataSetChanged();
    }


//    private void initView(View view) {
//        lv_menu = (ListView) view.findViewById(R.id.lv_menu);
//        tv_title = (TextView) view.findViewById(R.id.tv_titile);
//        lv_home = (ListView) view.findViewById(R.id.lv_home);
//
//
//        menuAdapter = new LabelMenuAdapter(getContext(), menuList);
//        lv_menu.setAdapter(menuAdapter);
//
//        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                menuAdapter.setSelectItem(position);
//                menuAdapter.notifyDataSetInvalidated();
//                tv_title.setText(menuList.get(position));
//                lv_home.setSelection(showTitle.get(position));
//            }
//        });
//
//        homeAdapter = new HomeAdapter(getContext(), labelItemContent,menuList);
//        lv_home.setAdapter(homeAdapter);
//        lv_home.setOnScrollListener(new AbsListView.OnScrollListener() {
//            private int scrollState;
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                this.scrollState = scrollState;
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem,
//                                 int visibleItemCount, int totalItemCount) {
//                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//                    return;
//                }
//                int current = showTitle.indexOf(firstVisibleItem);
////				lv_home.setSelection(current);
//                if (currentItem != current && current >= 0) {
//                    currentItem = current;
//                    tv_title.setText(menuList.get(currentItem));
//                    menuAdapter.setSelectItem(currentItem);
//                    menuAdapter.notifyDataSetInvalidated();
//                }
//            }
//        });
//    }


}
