package com.zxu.masterofpainting.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.CollectionAdapter;
import com.zxu.masterofpainting.Adapter.CollocationAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Collection;
import com.zxu.masterofpainting.bean.Collocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import dmax.dialog.SpotsDialog;

public class CollectionActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView collectionRrecyclerview;
    private SpotsDialog spotsDialog;
    private List<String> objectIdList = new ArrayList<>();
    private List<Collocation> mCollocationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initView();
        intToolBar();
        getData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_collection);
        collectionRrecyclerview = (RecyclerView) findViewById(R.id.collection_recyclerview);
        spotsDialog = new SpotsDialog(this,"拼命加载中...");
        spotsDialog.show();
    }

    private void intToolBar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void getData(){
        final BmobQuery<Collection> collectionBmobQuery = new BmobQuery<>("CollectionAdapter");
        collectionBmobQuery.findObjects(new FindListener<Collection>() {
            @Override
            public void done(List<Collection> list, BmobException e) {
                if (e == null) {
                    if (list.size() >= 1) {
                        for (int i = 0; i < list.size(); i++) {
                            objectIdList.add(list.get(i).getmObjectId());
                        }

                        for (int i = 0; i < objectIdList.size(); i++) {
                            BmobQuery<Collocation> collocationBmobQuery = new BmobQuery<>("Collocation");
                            collocationBmobQuery.getObject(objectIdList.get(i), new QueryListener<Collocation>() {
                                @Override
                                public void done(Collocation collocation, BmobException e) {
                                    if (e == null) {
                                        mCollocationList.add(collocation);
                                    } else {
                                        Toast.makeText(CollectionActivity.this, "获取收藏失败", Toast.LENGTH_SHORT).show();
                                    }
                                    showCollection();
                                    spotsDialog.dismiss();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(CollectionActivity.this, "您还没有收藏自己的营养搭配", Toast.LENGTH_SHORT).show();
                        spotsDialog.dismiss();
                    }

                } else {
                    Toast.makeText(CollectionActivity.this, "获取收藏ID失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showCollection(){
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        collectionRrecyclerview.setLayoutManager(layoutManager);
        CollectionAdapter collectionAdapter = new CollectionAdapter(mCollocationList);
        collectionRrecyclerview.setAdapter(collectionAdapter);
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
