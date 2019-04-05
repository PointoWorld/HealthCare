package com.zxu.masterofpainting.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.Adapter.CollocationAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Collocation;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import dmax.dialog.SpotsDialog;

public class CollocationActivity extends AppCompatActivity {
    //识别的食材名字
    private String collocationname1;
    //搭配的食材
    private String collocationname2;
    private SpotsDialog spotsDialog;
    private TextView toobarTitle;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private List<Collocation> mCollocationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collocation);

        initView();
        initToolBar();
        getAllData();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.collocation_product_recyclerview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toobarTitle = (TextView) findViewById(R.id.toolbar_title);
        spotsDialog = new SpotsDialog(this,"拼命加载中...");
        spotsDialog.show();
        Intent intent = getIntent();
        collocationname1 = intent.getStringExtra("collocationName1");
        collocationname2 = intent.getStringExtra("collocationName2");
    }

    private void initToolBar(){
        toobarTitle.setText(collocationname1 + " + " + collocationname2);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void getAllData(){
        BmobQuery<Collocation> collocationBmobQuery = new BmobQuery<>("CollocationActivity");
        collocationBmobQuery.setLimit(1000);
        collocationBmobQuery.findObjects(new FindListener<Collocation>() {
            @Override
            public void done(List<Collocation> list, BmobException e) {
                if (list != null) {
                    Toast.makeText(CollocationActivity.this, ""+list.size(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < list.size(); i++) {
                        String collocationIngredients = list.get(i).getCollocationIngredients();
                        if (collocationIngredients.contains(collocationname1)) {
                            mCollocationList.add(list.get(i));
                        }
                    }
                    setAllData();
                    spotsDialog.dismiss();
                } else {
                    Toast.makeText(CollocationActivity.this, "查询出错啦~", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAllData(){
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CollocationAdapter collocationAdapter = new CollocationAdapter(mCollocationList);
        recyclerView.setAdapter(collocationAdapter);
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
