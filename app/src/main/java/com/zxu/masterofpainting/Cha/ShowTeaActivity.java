package com.zxu.masterofpainting.Cha;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

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

public class ShowTeaActivity extends AppCompatActivity {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"冲泡方法","养生功效", "鉴定方法"};
    private ViewPager mViewPager;
    private BmobQuery<Tea> ingredientsBmobQuery;

    private String ingredientsName;
    private SpotsDialog spotsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tea);
        initView();
        getIngredientsData();
        setCoordinatorTabLayoutData();
    }

    private void initView() {
        Intent intent = getIntent();
        ingredientsName = intent.getStringExtra("result");
        mViewPager = (ViewPager) findViewById(R.id.tea_vp);
        mViewPager.setOffscreenPageLimit(4);
        spotsDialog = new SpotsDialog(this,"拼命加载中...");
        spotsDialog.show();
    }

    private void getIngredientsData(){
        Constants.teaName = ingredientsName;
        ingredientsBmobQuery = new BmobQuery<>("Tea");
        ingredientsBmobQuery.addWhereEqualTo("teaName", ingredientsName);
        ingredientsBmobQuery.findObjects(new FindListener<Tea>() {
            @Override
            public void done(List<Tea> list, BmobException e) {
                if (null == e && list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        Tea correctIngredients = list.get(i);
                        if (correctIngredients.getTeaName().equals(ingredientsName)) {
                            Constants.teaIntr= correctIngredients.getIntr();
                            Constants.teaUrl = correctIngredients.getImgUrl();

                            Constants.teachongPao = correctIngredients.getChongPao();
                            Constants.teasteps = correctIngredients.getSteps();
                            Constants.teaidentify = correctIngredients.getIdentify();
                            initFragments();
                            initViewPager();
                            spotsDialog.dismiss();
                            break;
                        }
                    }
                } else {
                    Toast.makeText(ShowTeaActivity.this, "tea list is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setCoordinatorTabLayoutData(){
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_light};

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle("                 "+ingredientsName)
                .setLoadHeaderImagesListener(new LoadHeaderImagesListener() {
                    @Override
                    public void loadHeaderImages(ImageView imageView, TabLayout.Tab tab) {
                        switch (tab.getPosition()) {
                            case 0:
                                loadImages(imageView, Constants.teaUrl);
                                break;
                            case 1:
                                loadImages(imageView, Constants.teaUrl);
                                break;
                            case 2:
                                loadImages(imageView, Constants.teaUrl);
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
        ChongPaoFragment edibleEfficacyFragment = new ChongPaoFragment();
        IntrFragment nutritionalComponentsFragment = new IntrFragment();
        IdentifyFragment suitableAvoidFragment = new IdentifyFragment();
        mFragments.add(edibleEfficacyFragment);
        mFragments.add(nutritionalComponentsFragment);
        mFragments.add(suitableAvoidFragment);
    }

    private void initViewPager(){
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    private void loadImages(ImageView imageView, String url) {
        Glide.with(ShowTeaActivity.this).load(url).into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
