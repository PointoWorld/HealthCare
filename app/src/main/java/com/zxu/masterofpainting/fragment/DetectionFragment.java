package com.zxu.masterofpainting.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.PhysiqueActivity;
import com.zxu.masterofpainting.bean.User;
import com.zxu.masterofpainting.utils.MyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobUser;
import link.fls.swipestack.SwipeStack;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetectionFragment extends Fragment implements SwipeStack.SwipeStackListener, View.OnClickListener{
    private ArrayList<String> mData;
    private SwipeStack mSwipeStack;
    private SwipeStackAdapter mAdapter;
    private List<Integer> scoreList = new ArrayList<>();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detection, container, false);

        showDialog(view);
        initView(view);
        fillWithTestData();
        return view;
    }

    private void showDialog(final View view){
        AlertDialog alertDialog1 = new AlertDialog.Builder(getContext())
                .setTitle(R.string.detectiontitle)//标题
                .setMessage(R.string.detectionexplain)//内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initView(view);
                        fillWithTestData();
                    }
                })
                .create();
        alertDialog1.show();
    }

    private void initView(View view){
        mSwipeStack = (SwipeStack) view.findViewById(R.id.swipeStack);
        mData = new ArrayList<>();
        mAdapter = new SwipeStackAdapter(mData);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(this);

    }
    private void fillWithTestData() {
        //mData.add("根据国家中医学会发布 《 中医体质分类与判定 》 体质相关疾病的防治测试时将自动保存，、养生保健等提供依据。不适用于孕妇，请耐心作答");
        for (int i = 0; i < Constants.cardQuestion.length; i++) {
            for (int j = 0; j < Constants.cardQuestion[i].length; j++) {
                mData.add(Constants.cardQuestion[i][j]);
            }
        }
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
        String physiqueStr = "气虚质";
        Intent intent = new Intent(getContext(),PhysiqueActivity.class);
        intent.putExtra("physique", physiqueStr);
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
