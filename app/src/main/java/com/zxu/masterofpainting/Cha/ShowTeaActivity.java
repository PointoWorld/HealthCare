package com.zxu.masterofpainting.Cha;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.MyPagerAdapter;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import dmax.dialog.SpotsDialog;

public class ShowTeaActivity extends AppCompatActivity {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"养生功效","冲泡方法", "鉴定方法"};
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
        Toast.makeText(this, ingredientsName, Toast.LENGTH_SHORT).show();
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
        mImageArray = new int[]{
                R.mipmap.longjing,
                R.mipmap.longjing,
                R.mipmap.longjing};
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_light};

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle("                 "+ingredientsName)
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);
    }

    private void initFragments(){
        mFragments = new ArrayList<>();
        IntrFragment nutritionalComponentsFragment = new IntrFragment();
        ChongPaoFragment edibleEfficacyFragment = new ChongPaoFragment();
        IdentifyFragment suitableAvoidFragment = new IdentifyFragment();
        mFragments.add(nutritionalComponentsFragment);
        mFragments.add(edibleEfficacyFragment);
        mFragments.add(suitableAvoidFragment);

//        for (String title : mTitles) {
//            mFragments.add(ShowDetailFragment.getInstance(title));
//        }
    }

    private void initViewPager(){
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//        }
        return super.onOptionsItemSelected(item);
    }
}
