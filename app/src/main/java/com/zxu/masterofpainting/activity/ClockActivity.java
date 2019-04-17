package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.CardBean;
import com.zxu.masterofpainting.bean.User;
import com.zxu.masterofpainting.view.OnSignedSuccess;
import com.zxu.masterofpainting.view.SignDate;

import java.util.ArrayList;
import java.util.Calendar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ClockActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView teaSum;
    private OptionsPickerView pvCustomOptions;
    private Button btn_CustomOptions;
    private ArrayList<CardBean> cardItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        initView();
    }

    private void initView() {
        findViewById(R.id.img_jian).setOnClickListener(this);
        findViewById(R.id.img_jia).setOnClickListener(this);
        findViewById(R.id.btn_daka).setOnClickListener(this);
        findViewById(R.id.btn_CustomOptions).setOnClickListener(this);
        teaSum = (TextView) findViewById(R.id.sum_tea);
        btn_CustomOptions = (Button) findViewById(R.id.btn_CustomOptions);

        getCardData();
        initCustomOptionPicker();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_jia:
                if (Integer.parseInt(teaSum.getText().toString()) <= 7) {
                    teaSum.setText(String.valueOf(Integer.parseInt(teaSum.getText().toString())+1));
                }else {
                    Toast.makeText(this, "喝茶虽好可不要喝的太多呦", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_jian:
                if (Integer.parseInt(teaSum.getText().toString()) > 0) {
                    teaSum.setText(String.valueOf(Integer.parseInt(teaSum.getText().toString())-1));
                } else {
                    Toast.makeText(this, "不能再减啦", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_CustomOptions:
                pvCustomOptions.show(); //弹出自定义条件选择器
                break;
            case R.id.btn_daka:
                daka();
                break;
        }
    }
    private void getCardData() {
        String[] strings = {"西湖龙井","铁观音","毛尖","普洱","大红袍"};
        for (int i = 0; i < 5; i++) {
            cardItem.add(new CardBean(i, strings[i]));
        }

        for (int i = 0; i < cardItem.size(); i++) {
            if (cardItem.get(i).getCardNo().length() > 6) {
                String str_item = cardItem.get(i).getCardNo().substring(0, 6) + "...";
                cardItem.get(i).setCardNo(str_item);
            }
        }
    }
    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                btn_CustomOptions.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        //final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });

//                        tvAdd.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                getCardData();
//                                pvCustomOptions.setPicker(cardItem);
//                            }
//                        });

                    }
                })
                .isDialog(true)
                .setOutSideCancelable(false)
                .build();

        pvCustomOptions.setPicker(cardItem);//添加数据


    }
    private void daka(){
        BmobUser.getCurrentUser(User.class).setCupcategory(btn_CustomOptions.getText().toString());
        BmobUser.getCurrentUser(User.class).setCupcategory(teaSum.getText().toString());
        User user = new User();
        user.setObjectId(BmobUser.getCurrentUser(User.class).getObjectId());
        user.setCupcategory(btn_CustomOptions.getText().toString());
        user.setCupSum(teaSum.getText().toString());

        String teaRecord = BmobUser.getCurrentUser(User.class).getTeaRecord();
        Calendar calendar = Calendar.getInstance();
        String day = ";" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + ",";

        user.setTeaRecord(teaRecord + day + teaSum.getText().toString());

        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (null == e) {
                    Toast.makeText(ClockActivity.this, "打卡成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ClockActivity.this, "打卡失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
