package com.zxu.masterofpainting.activity;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Collection;

import org.w3c.dom.Text;

public class PhysiqueActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title;
    private TextView myPhysiqueTextView;
    private String physiquestr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physique);

        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_physique);
        title = (TextView) findViewById(R.id.toolbar_physique_title);
        myPhysiqueTextView = (TextView) findViewById(R.id.physique_name_text_view);
        physiquestr = getIntent().getStringExtra("physique");
        myPhysiqueTextView.setText(physiquestr);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
