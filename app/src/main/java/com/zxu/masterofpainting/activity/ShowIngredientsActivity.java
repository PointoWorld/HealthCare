package com.zxu.masterofpainting.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zxu.masterofpainting.Adapter.MyPagerAdapter;
import com.zxu.masterofpainting.Cha.ShowTeaActivity;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Ingredient1;
import com.zxu.masterofpainting.fragment.EdibleEfficacyFragment;
import com.zxu.masterofpainting.fragment.NutritionalComponentsFragment;
import com.zxu.masterofpainting.fragment.SuitableAvoidFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import cn.hugeterry.coordinatortablayout.listener.LoadHeaderImagesListener;
import dmax.dialog.SpotsDialog;

public class ShowIngredientsActivity extends AppCompatActivity {
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"养生成分", "养生功效", "调养搭配"};
    private ViewPager mViewPager;
    private BmobQuery<Ingredient1> ingredientsBmobQuery;
    //食材名称
    private String ingredientsName;
    private SpotsDialog spotsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ingredients);

        initView();
        getIngredientsData();
//        setCoordinatorTabLayoutData();
    }

    private void initView() {
        Intent intent = getIntent();
        ingredientsName = intent.getStringExtra("result");
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(4);
        spotsDialog = new SpotsDialog(this,"拼命加载中...");
        spotsDialog.show();
    }

    private void getIngredientsData(){
        Toast.makeText(this, ingredientsName, Toast.LENGTH_SHORT).show();
        Constants.ingredientsName = ingredientsName;
        ingredientsBmobQuery = new BmobQuery<>("Ingredient1");
        ingredientsBmobQuery.addWhereEqualTo("IngredientsName", ingredientsName);
        ingredientsBmobQuery.findObjects(new FindListener<Ingredient1>() {
            @Override
            public void done(List<Ingredient1> list, BmobException e) {
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        Ingredient1 correctIngredients = list.get(i);
                        if (correctIngredients.getIngredientsName().equals(ingredientsName)) {
                            Constants.ingredientsNutrution= correctIngredients.getNutrition();
                            Constants.ingredientsEfficiency = correctIngredients.getEfficiency();
                            Constants.ingredientsImgUrl = correctIngredients.getImg();
                            Constants.ingredientsSuitableCollocation = correctIngredients.getSuitableCollocation();
                            setCoordinatorTabLayoutData(correctIngredients.getImg());
                            initFragments();
                            initViewPager();
                            spotsDialog.dismiss();
                            break;
                        }
                    }
                } else {
                    Toast.makeText(ShowIngredientsActivity.this, "list is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setCoordinatorTabLayoutData(final String s){
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_orange_light};

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle("                  "+ingredientsName)
                .setLoadHeaderImagesListener(new LoadHeaderImagesListener() {
                    @Override
                    public void loadHeaderImages(ImageView imageView, TabLayout.Tab tab) {
                        switch (tab.getPosition()) {
                            case 0:
                                loadImages(imageView, s);
                                break;
                            case 1:
                                loadImages(imageView, s);
                                break;
                            case 2:
                                loadImages(imageView, s);
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
        NutritionalComponentsFragment nutritionalComponentsFragment = new NutritionalComponentsFragment();
        EdibleEfficacyFragment edibleEfficacyFragment = new EdibleEfficacyFragment();
        SuitableAvoidFragment suitableAvoidFragment = new SuitableAvoidFragment();
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

    private void loadImages(ImageView imageView, String url) {
        Glide.with(ShowIngredientsActivity.this).load(url).into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
