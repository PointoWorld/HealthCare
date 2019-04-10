package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.zxu.masterofpainting.Adapter.CollocationAdapter;
import com.zxu.masterofpainting.Adapter.RecommendAdapter;
import com.zxu.masterofpainting.Adapter.ZuheIngAdapter;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Collocation;
import com.zxu.masterofpainting.bean.Ingredient1;
import com.zxu.masterofpainting.bean.Ingredients;
import com.zxu.masterofpainting.bean.Recommend;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SmartCombinationActivity extends AppCompatActivity {
    private RecyclerView ingredientsRecyclerView;
    private RecyclerView collocationRecyclerView;
    private List<Recommend> mRecommendList = new ArrayList<>();
    private List<Collocation> mCollocationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_combination);

        initView();
        loadAllData();
    }

    private void initView() {
        ingredientsRecyclerView = (RecyclerView) findViewById(R.id.ingredients_rv);
        collocationRecyclerView = (RecyclerView) findViewById(R.id.collocation_rv);
    }
    private void loadAllData(){
        BmobQuery<Ingredient1> ingredientsBmobQuery = new BmobQuery<>("Ingredient1");
        ingredientsBmobQuery.findObjects(new FindListener<Ingredient1>() {
            @Override
            public void done(List<Ingredient1> list, BmobException e) {
                if (null == e && null != list) {
                    int k = 0;
                    for (int i = 0; i < list.size(); i++) {
                        k++;
                        if (k < 7) {
                            mRecommendList.add(new Recommend(list.get(i).getImg(),list.get(i).getIngredientsName()));
                        } else {
                            k = 0;
                            break;
                        }
                    }
                    setIngredientsData();
                } else {
                    Toast.makeText(SmartCombinationActivity.this, "网络可能不好呦", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BmobQuery<Collocation> collocationBmobQuery = new BmobQuery<>("Collocation");
        collocationBmobQuery.findObjects(new FindListener<Collocation>() {
            @Override
            public void done(List<Collocation> list, BmobException e) {
                if (null == e && null != list) {
                    int k = 0;
                    for (int i = 0; i < list.size(); i++) {
                        k++;
                        if (k < 7) {
                            mCollocationList.add(list.get(i));
                        } else {
                            k = 0;
                            break;
                        }
                    }
                    setCollocationData();
                } else {
                    Toast.makeText(SmartCombinationActivity.this, "网络可能不好呦", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setIngredientsData(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        ingredientsRecyclerView.setLayoutManager(layoutManager);
        ZuheIngAdapter recommendAdapter = new ZuheIngAdapter(mRecommendList);
        ingredientsRecyclerView.setAdapter(recommendAdapter);
    }
    private void setCollocationData(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        collocationRecyclerView.setLayoutManager(layoutManager);
        CollocationAdapter recommendAdapter = new CollocationAdapter(mCollocationList);
        collocationRecyclerView.setAdapter(recommendAdapter);
    }
}
