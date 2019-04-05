package com.zxu.masterofpainting.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.RecommendAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Label;
import com.zxu.masterofpainting.bean.Recommend;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import dmax.dialog.SpotsDialog;

public class LabelDetailActivity extends AppCompatActivity {
    private String labelName;
    private String introduce;
    private String recommend;
    private String ingredientsFine;
    private String ingredientsFineUrl;
    private String badRecommend;
    private String ingredientsBad;
    private String ingredientsBadUrl;
    private String fineCollocation;
    private String fineCollocationUrl;
    private Toolbar toolbar;
    private TextView title;
    private TextView introduceTextView;
    private TextView recommendFineTextView;
    private TextView recommendBadTextView;
    private Button collocationButton;
    private RecyclerView recommendRecyclerView;
    private RecyclerView badRecommendRecyclerView;
    private List<Recommend> recommendList = new ArrayList<>();
    private List<Recommend> badRecommendList = new ArrayList<>();

    private SpotsDialog spotsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_detail);
        spotsDialog = new SpotsDialog(this,"拼命加载中...");

        initView();
        getData();
        loadBmobData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_label_detail);
        title = (TextView) findViewById(R.id.toolbar_label_detail_title);
        introduceTextView = (TextView) findViewById(R.id.introduce_text_view);
        recommendFineTextView = (TextView) findViewById(R.id.recommend_fine_text_view);
        recommendBadTextView = (TextView) findViewById(R.id.recommend_bad_text_view);
        collocationButton = (Button) findViewById(R.id.collocation_btn);
        recommendRecyclerView = (RecyclerView) findViewById(R.id.recommend_recyclerview);
        badRecommendRecyclerView = (RecyclerView) findViewById(R.id.badrecommend_recyclerview);
        spotsDialog.show();
        collocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabelDetailActivity.this,LabelFineCollocationActivity.class);
                intent.putExtra("labelCollocation", fineCollocation);
                intent.putExtra("labelName", labelName);
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }
    private void getData(){
        Intent intent = getIntent();
        labelName = intent.getStringExtra("labelName");
        title.setText(labelName);
        Toast.makeText(this, labelName, Toast.LENGTH_SHORT).show();
    }

    private void loadBmobData(){
        BmobQuery<Label> labelBmobQuery = new BmobQuery<>("Label");
        labelBmobQuery.findObjects(new FindListener<Label>() {
            @Override
            public void done(List<Label> list, BmobException e) {
                if (null == e) {
                    int i = 0;
                    for (i = 0; i < list.size(); i++) {
                        if (list.get(i).getLabelName().equals(labelName)) {
                            introduce = list.get(i).getIntroduce();
                            recommend = list.get(i).getRecommend();
                            ingredientsFine = list.get(i).getIngredientsFine();
                            ingredientsFineUrl = list.get(i).getIngredientsFineUrl();
                            badRecommend = list.get(i).getBadRecommend();
                            ingredientsBad = list.get(i).getIngredientsBad();
                            ingredientsBadUrl = list.get(i).getIngredientsBadUrl();
                            fineCollocation = list.get(i).getFineCollocation();
                            fineCollocationUrl = list.get(i).getFineCollocationUrl();

                            setData();
                            //Toast.makeText(LabelDetailActivity.this, list.get(i).getIntroduce(), Toast.LENGTH_SHORT).show();
                            spotsDialog.dismiss();
                            break;
                        }
                    }
                    if (i >= list.size()) {
                        Toast.makeText(LabelDetailActivity.this, "没有查找到标签相关信息", Toast.LENGTH_SHORT).show();
                        spotsDialog.dismiss();
                    }
                } else {
                    Toast.makeText(LabelDetailActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                    spotsDialog.dismiss();
                }
            }
        });
    }

    private void setData(){
        introduceTextView.setText(introduce);
        recommendFineTextView.setText(recommend);
        recommendBadTextView.setText(badRecommend);
        String[] splitRecommend = ingredientsFine.split(",");
        String[] splitBadRecommend = ingredientsBad.split(",");
        String[] splitRecommendUrl = ingredientsFineUrl.split(",");
        String[] splitBadRecommendUrl = ingredientsBadUrl.split(",");
        for (int i = 0; i < splitRecommend.length; i++) {
            recommendList.add(new Recommend(splitRecommendUrl[i], splitRecommend[i]));
        }
        for (int i = 0; i < splitBadRecommend.length; i++) {
            badRecommendList.add(new Recommend(splitBadRecommendUrl[i], splitBadRecommend[i]));
        }
        StaggeredGridLayoutManager recommendLM = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager badRecommendLM = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recommendRecyclerView.setLayoutManager(recommendLM);
        badRecommendRecyclerView.setLayoutManager(badRecommendLM);
        RecommendAdapter recommendAdapter = new RecommendAdapter(recommendList);
        RecommendAdapter badRecommendAdapter = new RecommendAdapter(badRecommendList);
        recommendRecyclerView.setAdapter(recommendAdapter);
        badRecommendRecyclerView.setAdapter(badRecommendAdapter);
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
