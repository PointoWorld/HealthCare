package com.zxu.masterofpainting.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.CollocationAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Collocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import dmax.dialog.SpotsDialog;

public class LabelFineCollocationActivity extends AppCompatActivity {
    private String collocationStr;
    private String labelName;
    private List<String> mCollocationNameList = new ArrayList<>();
    private SpotsDialog spotsDialog;
    private List<Collocation> mCollocationList = new ArrayList<>();
    private RecyclerView labelCollocationRecyclerView;
    private Toolbar toobar;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_fine_collocation);

        initView();
        loadData();
    }

    private void initView() {
        spotsDialog = new SpotsDialog(this,"拼命加载中...");
        spotsDialog.show();
        labelCollocationRecyclerView = (RecyclerView) findViewById(R.id.label_collocation_recyclerview);
        toobar = (Toolbar) findViewById(R.id.toolbar_label_collocation);
        title = (TextView) findViewById(R.id.toolbar_label_collocation_title);
        collocationStr = getIntent().getStringExtra("labelCollocation");
        labelName = getIntent().getStringExtra("labelName");
        title.setText(labelName+"的营养搭配");
        setSupportActionBar(toobar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        Toast.makeText(this, collocationStr, Toast.LENGTH_SHORT).show();
    }

    private void loadData(){
        String[] splitStr = collocationStr.split(",");
        mCollocationNameList = Arrays.asList(splitStr);
        //Toast.makeText(this, ""+splitStr.length, Toast.LENGTH_SHORT).show();

        BmobQuery<Collocation> collocationBmobQuery = new BmobQuery<>("Collocation");
        collocationBmobQuery.findObjects(new FindListener<Collocation>() {
            @Override
            public void done(List<Collocation> list, BmobException e) {
                if (null == e) {
                    for (int i = 0; i < list.size(); i++) {
                        if (collocationStr.contains(list.get(i).getCollocationName())) {
                            mCollocationList.add(list.get(i));
                        }
                    }
                    StaggeredGridLayoutManager layoutManager = new
                            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                    labelCollocationRecyclerView.setLayoutManager(layoutManager);
                    CollocationAdapter collocationAdapter = new CollocationAdapter(mCollocationList);
                    labelCollocationRecyclerView.setAdapter(collocationAdapter);

                    spotsDialog.dismiss();
                } else {
                    Toast.makeText(LabelFineCollocationActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                    spotsDialog.dismiss();
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
