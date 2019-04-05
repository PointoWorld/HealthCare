package com.zxu.masterofpainting.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Mineral;
import com.zxu.masterofpainting.bean.Vitamin;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class VitaminInfoActivity extends AppCompatActivity {
    private TextView vInfo;
    private TextView vInfoContent;
    private TextView vEffect;
    private TextView vEffectContent;
    private TextView vSheru;
    private TextView vSheruContent;
    private TextView vBucheng;
    private TextView vBuchengContent;
    private TextView vLaili;
    private TextView vLailiContent;
    private TextView vRenqun;
    private TextView vRenqunContent;
    private TextView vQuefa;
    private TextView vQuefaContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamin_info);

        initView();
    }

    private void initView() {
        vInfo = (TextView) findViewById(R.id.vitamin_title1);
        vInfoContent= (TextView) findViewById(R.id.vitamin_intr);
        vEffect = (TextView) findViewById(R.id.vitamin_title2);
        vEffectContent = (TextView) findViewById(R.id.vitamin_effect);
        vSheru = (TextView) findViewById(R.id.vitamin_title3);
        vSheruContent = (TextView) findViewById(R.id.vitamin_sheruliang);
        vBucheng = (TextView) findViewById(R.id.vitamin_title4);
        vBuchengContent = (TextView) findViewById(R.id.vitamin_bucheng);
        vLaili = (TextView) findViewById(R.id.vitamin_title5);
        vLailiContent = (TextView) findViewById(R.id.vitamin_laili);
        vRenqun = (TextView) findViewById(R.id.vitamin_title6);
        vRenqunContent = (TextView) findViewById(R.id.vitamin_renqun);
        vQuefa = (TextView) findViewById(R.id.vitamin_title7);
        vQuefaContent = (TextView) findViewById(R.id.vitamin_quefa);
        Intent intent = getIntent();
        String vitaminName = intent.getStringExtra("vitaminCategory");
        LoaDate(vitaminName);
    }

    private void LoaDate(final String vitaminName) {
        if (vitaminName.contains("维生素")) {
            BmobQuery<Vitamin> vitaminBmobQuery = new BmobQuery<>("Vitamin");
            //vitaminBmobQuery.addWhereEqualTo("vitaminName",vitaminName);
            vitaminBmobQuery.findObjects(new FindListener<Vitamin>() {
                @Override
                public void done(List<Vitamin> list, BmobException e) {
                    if (e == null) {
                        if (list != null) {
                            //Toast.makeText(VitaminInfoActivity.this, ""+list.size(), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < list.size(); i++) {
                                Vitamin vitamin = list.get(i);
                                if (vitamin.getVitaminName().equals(vitaminName)) {
                                    String[] vitaminSplit = list.get(i).getVitaminEffect().split("#");
                                    setData(vitaminSplit);
                                    break;
                                }
                            }
                        } else {
                            Toast.makeText(VitaminInfoActivity.this, "没有查找到相关信息", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        Toast.makeText(VitaminInfoActivity.this, "没有查找到相关信息", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            BmobQuery<Mineral> mineralBmobQuery = new BmobQuery<>("Mineral");
            mineralBmobQuery.findObjects(new FindListener<Mineral>() {
                @Override
                public void done(List<Mineral> list, BmobException e) {
                    if (null == e) {
                        if (null != list) {
                            //Toast.makeText(VitaminInfoActivity.this, ""+list.size(), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getMineralName().equals(vitaminName)) {
                                    String[] mineralSplit = list.get(i).getMineralEffect().split("#");
                                    setData(mineralSplit);
                                    break;
                                }
                            }
                        }
                    } else {
                        Toast.makeText(VitaminInfoActivity.this, "没有查找到矿物质相关信息", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void setData(String[] vitaminSplit) {
        if (null != vitaminSplit) {
            vInfo.setText(vitaminSplit[0]);
            vInfoContent.setText(vitaminSplit[1]);
            vEffect.setText(vitaminSplit[2]);
            vEffectContent.setText(vitaminSplit[3]);
            vSheru.setText(vitaminSplit[4]);
            vSheruContent.setText(vitaminSplit[5]);
            vBucheng.setText(vitaminSplit[6]);
            vBuchengContent.setText(vitaminSplit[7]);
            vLaili.setText(vitaminSplit[8]);
            vLailiContent.setText(vitaminSplit[9]);
            vRenqun.setText(vitaminSplit[10]);
            vRenqunContent.setText(vitaminSplit[11]);
            vQuefa.setText(vitaminSplit[12]);
            vQuefaContent.setText(vitaminSplit[13]);

        }
    }

}
