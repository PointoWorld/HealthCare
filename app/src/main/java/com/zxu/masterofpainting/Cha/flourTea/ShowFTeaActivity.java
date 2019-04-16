package com.zxu.masterofpainting.Cha.flourTea;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zxu.masterofpainting.Adapter.MyPagerAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import cn.hugeterry.coordinatortablayout.listener.LoadHeaderImagesListener;
import dmax.dialog.SpotsDialog;

public class ShowFTeaActivity extends AppCompatActivity {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"养生功效", "制作方法"};
    private ViewPager mViewPager;

    private String flowerTeaName;
    private SpotsDialog spotsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ftea);

        initView();
        getIngredientsData();
        setCoordinatorTabLayoutData();
    }
    private void initView() {
        Intent intent = getIntent();
        flowerTeaName = intent.getStringExtra("flowerTea");
        mViewPager = (ViewPager) findViewById(R.id.flower_tea_vp);
        mViewPager.setOffscreenPageLimit(4);
        spotsDialog = new SpotsDialog(this,"拼命加载中...");
        spotsDialog.show();
    }
    private void getIngredientsData(){
        BmobQuery<flowertea> flowerteaBmobQuery = new BmobQuery<>("flowertea");
        flowerteaBmobQuery.findObjects(new FindListener<flowertea>() {
            @Override
            public void done(List<flowertea> list, BmobException e) {
                if (null == e && null != list) {
                    for (int i = 0; i < list.size(); i++) {
                        if (flowerTeaName.equals(list.get(i).getName())) {
                            Constants.fteaIntrVideo = list.get(i).getGongXiao().split("@")[0];
                            Constants.fteaIntrtext = list.get(i).getGongXiao().split("@")[1];
                            Constants.fteastepsVideo = list.get(i).getZuoFa().split("@")[0];
                            Constants.fteastepstext = list.get(i).getZuoFa().split("@")[1];

                            Constants.fteaYuanLiao = list.get(i).getYuanLiao();
                            Constants.fteaEffect = list.get(i).getGongXiao();
                            Constants.fteasteps = list.get(i).getZuoFa();
                            Constants.fteaimgUrl = list.get(i).getImgUrl();
                            Constants.fteaName = list.get(i).getName();
                            initFragments();
                            initViewPager();
                            spotsDialog.dismiss();
                            break;
                        }
                    }
                }
            }
        });
    }
    private void setCoordinatorTabLayoutData(){
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_light};

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.ftea_coordinatortablayout);
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle("                 "+flowerTeaName)
                .setLoadHeaderImagesListener(new LoadHeaderImagesListener() {
                    @Override
                    public void loadHeaderImages(ImageView imageView, TabLayout.Tab tab) {
                        switch (tab.getPosition()) {
                            case 0:
                                loadImages(imageView, Constants.fteaimgUrl);
                                break;
                            case 1:
                                loadImages(imageView, Constants.fteaimgUrl);
                                break;
                        }
                    }
                })
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);
    }
    private void initFragments(){
        mFragments = new ArrayList<>();
        FTEffectFragment ftEffectFragment = new FTEffectFragment();
        FTStepsFragment ftStepsFragment = new FTStepsFragment();
        mFragments.add(ftEffectFragment);
        mFragments.add(ftStepsFragment);
    }
    private void initViewPager(){
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }
    private void loadImages(ImageView imageView, String url) {
        Glide.with(ShowFTeaActivity.this).load(url).into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
