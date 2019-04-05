package com.zxu.masterofpainting.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.User;
import com.zxu.masterofpainting.utils.MyUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import link.fls.swipestack.SwipeStack;

public class TestingActivity extends AppCompatActivity implements SwipeStack.SwipeStackListener, View.OnClickListener {

    private ArrayList<String> mData = new ArrayList<>();
    private SwipeStack mSwipeStack;
    private SwipeStackAdapter mAdapter;
    private List<Integer> scoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        showDialog();
        initView();
    }

    private void showDialog(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle(R.string.detectiontitle)//标题
                .setMessage(R.string.detectionexplain)//内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        initView();
//                        fillWithTestData();
                    }
                })
                .create();
        alertDialog1.show();
    }

    private void initView(){
        mSwipeStack = (SwipeStack) findViewById(R.id.swipeStack2);
        for (int i = 0; i < Constants.cardQuestion.length; i++) {
            for (int j = 0; j < Constants.cardQuestion[i].length; j++) {
                mData.add(Constants.cardQuestion[i][j]);
            }
        }
        mAdapter = new SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onViewSwipedToRight(int position) {
        String swipedElement = mAdapter.getItem(position);
        //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        String swipedElement = mAdapter.getItem(position);
        //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStackEmpty() {
//        String physiqueStr = getPhysiqueStr(scoreList);
        BmobUser.getCurrentUser(User.class).setTestState(1+"");
        User user = new User();
        user.setTestState("" + 1);
        user.setObjectId(BmobUser.getCurrentUser(User.class).getObjectId());
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (null == e) {
                    Toast.makeText(TestingActivity.this, "state suc", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TestingActivity.this, "state fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent = new Intent(this,PhysiqueActivity.class);
        intent.putExtra("physique", Constants.physiqueStr[1]);
        startActivity(intent);
        //Toast.makeText(getContext(), physiqueStr, Toast.LENGTH_SHORT).show();
    }

    public class SwipeStackAdapter extends BaseAdapter {

        private List<String> mData;

        public SwipeStackAdapter(List<String> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.card_question, parent, false);
            }

            TextView textViewCard = (TextView) convertView.findViewById(R.id.textViewCard);
            RadioGroup radioGroup = (RadioGroup) convertView.findViewById(R.id.radio_group);
            textViewCard.setText(mData.get(position));
            final View finalConvertView = convertView;
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int id = group.getCheckedRadioButtonId();
                    RadioButton choise = (RadioButton) finalConvertView.findViewById(id);
                    scoreList.add(MyUtil.getScore((String) choise.getText()));
                    //Toast.makeText(getContext(), choise.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }

    private String getPhysiqueStr(List<Integer> scoreList){
        int[] n = new int[9];
        for (int i = 0; i < 9; i++) {
            int sum = 0;
            for (int j = 0; j < 7; j++) {
                sum = sum + scoreList.get(i * 7 + j);
            }
            n[i] = sum;
        }
        int max = n[0];
        int k = 0;
        for (int i = 1; i < n.length; i++) {
            if (n[i] > max) {
                k = i;
            }
        }
        return Constants.physiqueStr[k];
    }
}
