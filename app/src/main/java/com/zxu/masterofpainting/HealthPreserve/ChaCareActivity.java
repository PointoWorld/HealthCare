package com.zxu.masterofpainting.HealthPreserve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.ChaCare;
import com.zxu.masterofpainting.fragment.ChaCareAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ChaCareActivity extends AppCompatActivity {
    private List<ChaCare> mChaCareList = new ArrayList<>();
    private RecyclerView careRecyclerView;
    private SimpleDraweeView careSimpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_care);
        initView();
        loadAllData();
    }

    private void initView() {
        careSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.care_simple_view);
        careRecyclerView = (RecyclerView) findViewById(R.id.care_recycler_view);
    }
    private void loadAllData(){
        BmobQuery<ChaCare> chaCareBmobQuery = new BmobQuery<>("ChaCare");
        chaCareBmobQuery.findObjects(new FindListener<ChaCare>() {
            @Override
            public void done(List<ChaCare> list, BmobException e) {
                if (null == e && null != list) {
                    for (int i = 0; i < list.size(); i++) {
                        mChaCareList.add(new ChaCare(list.get(i).getTitle(), list.get(i).getImagUrl(), list.get(i).getContent()));
                    }
                    setData();
                } else {
                    Toast.makeText(ChaCareActivity.this, "哎呀，网络可能不好呀", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setData(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        careRecyclerView.setLayoutManager(layoutManager);
        ChaCareAdapter chaCareAdapter = new ChaCareAdapter(mChaCareList);
        careSimpleDraweeView.setImageURI("http://s13.sinaimg.cn/mw690/0063js8kzy7370nrZ0Mfc&690");
        careRecyclerView.setAdapter(chaCareAdapter);
    }

}
