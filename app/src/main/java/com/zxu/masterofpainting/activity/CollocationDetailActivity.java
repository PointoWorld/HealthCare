package com.zxu.masterofpainting.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.Adapter.MaterialAdapter;
import com.zxu.masterofpainting.Adapter.StepsAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Collection;
import com.zxu.masterofpainting.bean.Collocation;
import com.zxu.masterofpainting.bean.Material;
import com.zxu.masterofpainting.bean.Step;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import dmax.dialog.SpotsDialog;

public class CollocationDetailActivity extends AppCompatActivity{
    private String objectId;
    private String collocationName;
    private String collocationPicture;
    private String collocationEfficacy;
    private String collocationIngredients;
    private String stepPictures;
    private String stepDetails;
    private SimpleDraweeView simpleDraweeView;
    private SpotsDialog spotsDialog;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView collocationEfficacyTextView;
    private RecyclerView ingredientsRecyclerView;
    private RecyclerView stepsRecyclerView;
    private Collocation selectCollocation;
    private FloatingActionButton floatingActionButton;

    private List<Material> mMaterialList = new ArrayList<>();
    private List<Step> mStepList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collocation_detail);
        initView();
        getAllData();
    }

    private void initView() {
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.collocation_image_view);
        collocationEfficacyTextView = (TextView) findViewById(R.id.collocation_efficacy_text);
        ingredientsRecyclerView = (RecyclerView) findViewById(R.id.ingredients_recyclerview);
        stepsRecyclerView = (RecyclerView) findViewById(R.id.steps_recyclerview);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.float_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCollectionData();
            }
        });
        Intent intent = getIntent();
        objectId = intent.getStringExtra("selectObjectId");
        spotsDialog = new SpotsDialog(this, "拼命加载中...");
        spotsDialog.show();
    }

    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("         " + collocationName);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        simpleDraweeView.setImageURI(collocationPicture);
    }

    private void getAllData() {
        BmobQuery<Collocation> collocationBmobQuery = new BmobQuery<>("Collocation");
        //collocationBmobQuery.addQueryKeys("CollocationIngredients");
        collocationBmobQuery.getObject(objectId, new QueryListener<Collocation>() {
            @Override
            public void done(Collocation collocation, BmobException e) {
                if (e == null && collocation != null) {
                    collocationName = collocation.getCollocationName();
                    collocationPicture = collocation.getCollocationPicture();
                    collocationEfficacy = collocation.getCollocationEfficacy();
                    collocationIngredients = collocation.getCollocationIngredients();
                    stepPictures = collocation.getStepPictures();
                    stepDetails = collocation.getStepDetails();
                    initToolBar();
                    setData();
                    spotsDialog.dismiss();
                } else {
                    //Toast.makeText(CollocationDetailActivity.this, "哎呀，没查找到~", Toast.LENGTH_SHORT).show();
                    spotsDialog.dismiss();
                }
            }
        });
    }

    private void setData(){
        String[] splitCollocationIngredients = collocationIngredients.split(";");
        String[] splitStepPictures = stepPictures.split(";");
        String[] splitStepDetails = stepDetails.split(";");
        for (int i = 0; i < splitCollocationIngredients.length; i++) {
            String[] childSplit = splitCollocationIngredients[i].split(",");
            mMaterialList.add(new Material(childSplit[0], childSplit[1]));
        }
        for (int i = 0; i < splitStepPictures.length; i++) {
            mStepList.add(new Step("" + (i +1), splitStepPictures[i], splitStepDetails[i]));
        }
        collocationEfficacyTextView.setText(collocationEfficacy);
        StaggeredGridLayoutManager layoutManager1 = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager layoutManager2 = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        ingredientsRecyclerView.setLayoutManager(layoutManager1);
        stepsRecyclerView.setLayoutManager(layoutManager2);
        MaterialAdapter materialAdapter = new MaterialAdapter(mMaterialList);
        StepsAdapter stepsAdapter = new StepsAdapter(mStepList);
        ingredientsRecyclerView.setAdapter(materialAdapter);
        stepsRecyclerView.setAdapter(stepsAdapter);
    }

    private void saveCollectionData(){
        Collection collection = new Collection(objectId);
        collection.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(CollocationDetailActivity.this, "收藏成功!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CollocationDetailActivity.this, "哎呀，收藏失败啦", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
