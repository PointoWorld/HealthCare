package com.zxu.masterofpainting.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.laocaixw.layout.SuspendButtonLayout;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Tab;
import com.zxu.masterofpainting.fragment.NurseFragment;
import com.zxu.masterofpainting.fragment.RecommendFragment;
import com.zxu.masterofpainting.fragment.TakePhotoFragment;
import com.zxu.masterofpainting.fragment.MyFragment;
import com.zxu.masterofpainting.widget.FragmentTabHost;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private SuspendButtonLayout suspendButtonLayout;
    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initTab();
    }

    private void initToolBar() {
        suspendButtonLayout = (SuspendButtonLayout) findViewById(R.id.layout);
        suspendButtonLayout.setPosition(true, 100);
        suspendButtonLayout.setOnSuspendListener(new SuspendButtonLayout.OnSuspendListener() {
            @Override
            public void onButtonStatusChanged(int status) {
                // 监听按钮状态：展开、关闭、移动等
            }

            @Override
            public void onChildButtonClick(int index) {
                // 监听子按钮的点击事件
                String ss = null;
                if (index == 1) {
                    ss = "tea";
                } else if (index == 2) {
                    ss = "ingredients";
                }
                Intent intent = new Intent(MainActivity.this, TakePhotoActivity.class);
                intent.putExtra("cameraid", ss);
                startActivity(intent);
            }
        });
//        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
//        toobarTitle = (TextView) findViewById(R.id.toolbar_main_title);
//        toobarTitle.setText(R.string.app_name);
//
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
    }

    private void initTab() {
        Tab tab_takephoto = new Tab(TakePhotoFragment.class,R.string.takephoto,R.drawable.selector_icon_takephoto);
        Tab tab_recommend = new Tab(RecommendFragment.class,R.string.detection,R.drawable.selector_icon_detection);
        //Tab tab_photoinfo = new Tab(InfoFragment.class,R.string.detection,R.drawable.selector_icon_detection);
        //Tab tab_test = new Tab(NurseFragment.class,R.string.nurse,R.drawable.selector_icon_nurse);
        Tab tab_user = new Tab(MyFragment.class,R.string.user,R.drawable.selector_user_info);

        mTabs.add(tab_takephoto);
        mTabs.add(tab_recommend);
        //mTabs.add(tab_test);
        mTabs.add(tab_user);
        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(R.id.tabhost);
        mTabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab : mTabs){
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec,tab.getFragment(),null);

        }
    }

    private  View buildIndicator(Tab tab){
        View view =mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return  view;
    }


}
