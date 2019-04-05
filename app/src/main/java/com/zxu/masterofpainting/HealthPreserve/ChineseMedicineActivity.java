package com.zxu.masterofpainting.HealthPreserve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.paulyung.laybellayout.LaybelLayout;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.ShowIngredientsActivity;

public class ChineseMedicineActivity extends AppCompatActivity {
    private LaybelLayout medicineLaybel;
    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_medicine);

        initView();
        loadData();
    }

    private void initView() {
        medicineLaybel = (LaybelLayout) findViewById(R.id.medicine_laybel_layout);
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.chinese_simple_view);
    }
    private void loadData(){
        simpleDraweeView.setImageURI("http://www.mianfeiwendang.com/pic/99c12b918d2d8e9577c057051e49691a055e7cb0/1-810.18-jpg_6-1439.82-0-0-1439.82.jpg");
        medicineLaybel.setAdapter(new LaybelLayout.Adapter(Constants.medicine));
        medicineLaybel.setOnItemClickListener(new LaybelLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int p) {
                Intent intent = new Intent(ChineseMedicineActivity.this,ShowIngredientsActivity.class);
                intent.putExtra("result", Constants.medicine[p]);
                startActivity(intent);
            }
        });
    }
}
