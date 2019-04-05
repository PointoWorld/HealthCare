package com.zxu.masterofpainting.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.flurgle.camerakit.CameraKit;
import com.flurgle.camerakit.CameraListener;
import com.flurgle.camerakit.CameraView;
import com.zxu.masterofpainting.Cha.ShowTeaActivity;
import com.zxu.masterofpainting.Cha.Tea;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Ingredients;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import dmax.dialog.SpotsDialog;

public class TakePhotoActivity extends AppCompatActivity implements View.OnLayoutChangeListener{
    private SpotsDialog spotsDialog;
    @BindView(R.id.camera)
    CameraView camera;
    private Handler handler;
    private String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_take_photo);

        ButterKnife.bind(this);
        handler = new Handler();
        Intent intent = getIntent();
        cameraId = intent.getStringExtra("cameraid");
        camera.addOnLayoutChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera.start();
    }

    @Override
    protected void onPause() {
        camera.stop();
        super.onPause();
    }

    @OnClick(R.id.capturePhoto)
    void capturePhoto() {
        final long startTime = System.currentTimeMillis();
        camera.setCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(final byte[] jpeg) {
                super.onPictureTaken(jpeg);

                final AipImageClassify client = new AipImageClassify("15847958", "pyReKYGYKrDX1Zf1ISAm3rpC", "hiSnw9qYoI1fMasUC2RlIwNGyEhVteQm");
                client.setConnectionTimeoutInMillis(2000);
                client.setSocketTimeoutInMillis(60000);
                spotsDialog = new SpotsDialog(TakePhotoActivity.this,"正在识别中...");
                spotsDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject res = client.plantDetect(jpeg, new HashMap<String, String>());
                        try {
                            String result = res.getJSONArray("result").getJSONObject(0).getString("name");
                            handler.post(new UIRunable(result));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(MainActivity.this, res.toString(), Toast.LENGTH_SHORT).show();
                    }
                }).start();
            }
        });
        camera.captureImage();
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

    }


    public class UIRunable implements Runnable{
        String ss;
        String sss = "银耳";
        public UIRunable(String s) {
            this.ss = s;
        }

        @Override
        public void run() {
            if (cameraId.equals("ingredients")) {
                //Toast.makeText(TakePhotoActivity.this, ss, Toast.LENGTH_SHORT).show();
                BmobQuery<Ingredients> ingredientsBmobQuery = new BmobQuery<>("Ingredients");
                ingredientsBmobQuery.findObjects(new FindListener<Ingredients>() {
                    @Override
                    public void done(List<Ingredients> list, BmobException e) {
                        if (null == e && null != list) {
                            boolean yes = false;
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getIngredientsName().equals(sss)) {
                                    yes = true;

                                    break;
                                }
                            }
                            spotsDialog.dismiss();
                            if (!yes) {
                                Toast.makeText(TakePhotoActivity.this, "请正确摆好拍摄位置呦", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(TakePhotoActivity.this, ShowIngredientsActivity.class);
                                intent.putExtra("result", sss);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(TakePhotoActivity.this, "请正确摆好拍摄位置呦", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (cameraId.equals("tea")){
                //Toast.makeText(TakePhotoActivity.this, ss, Toast.LENGTH_SHORT).show();
                BmobQuery<Tea> ingredientsBmobQuery = new BmobQuery<>("Tea");
                ingredientsBmobQuery.findObjects(new FindListener<Tea>() {
                    @Override
                    public void done(List<Tea> list, BmobException e) {
                        if (null == e && null != list) {
                            Intent intent = new Intent(TakePhotoActivity.this, ShowTeaActivity.class);
                            intent.putExtra("result", "西湖龙井");
                            startActivity(intent);
                            spotsDialog.dismiss();
//                            boolean yes = false;
//                            for (int i = 0; i < list.size(); i++) {
//                                if (list.get(i).getTeaName().equals(ss)) {
//                                    yes = true;
//
//                                    break;
//                                }
//                            }
//                            spotsDialog.dismiss();
//                            if (!yes) {
//                                Toast.makeText(TakePhotoActivity.this, "请正确摆好拍摄位置呦", Toast.LENGTH_SHORT).show();
//                            } else {
////                                Intent intent = new Intent(TakePhotoActivity.this, ShowTeaActivity.class);
////                                intent.putExtra("result", "西湖龙井");
////                                startActivity(intent);
//                            }
//                        } else {
//                            Toast.makeText(TakePhotoActivity.this, "请正确摆好拍摄位置呦", Toast.LENGTH_SHORT).show();
//                        }
                        }
                    }
                });
            } else {

            }

        }
    }
}
