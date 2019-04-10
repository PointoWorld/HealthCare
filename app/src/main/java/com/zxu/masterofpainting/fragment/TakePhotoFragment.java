package com.zxu.masterofpainting.fragment;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.laocaixw.layout.SuspendButtonLayout;
import com.zxu.masterofpainting.HealthPreserve.SolarDetailActivity;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.ClockActivity;
import com.zxu.masterofpainting.activity.PhysiqueActivity;
import com.zxu.masterofpainting.activity.ShowIngredientsActivity;
import com.zxu.masterofpainting.activity.TakePhotoActivity;
import com.zxu.masterofpainting.activity.TestingActivity;
import com.zxu.masterofpainting.bean.Hour;
import com.zxu.masterofpainting.bean.SolarTerms;
import com.zxu.masterofpainting.bean.User;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.app.Activity.RESULT_OK;

public class TakePhotoFragment extends Fragment implements View.OnClickListener{
    private Handler handler = null;
    private Bitmap bitmap;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOSE_PHOTO = 2;

    private ImageView picture_Distinguish;
    private Uri imageUri;
    private SuspendButtonLayout suspendButtonLayout;
    SimpleMarqueeView<String> marqueeView;
    SimpleMarqueeView<String> solorMarqueeView;
    ValueLineChart mCubicValueLineChart;
    ValueLineSeries series;
    SimpleMF<String> marqueeFactory;
    SimpleMF<String> solarMarqueeFactory;
    List<String> datas;
    List<String> solarDatas;
    private TextView hourName;
    private TextView solarName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_takephoto,container,false);
        iniview(view);

        return view;
    }

    public void iniview(View view){
        mCubicValueLineChart = (ValueLineChart) view.findViewById(R.id.cubiclinechart);
        view.findViewById(R.id.tizhi_test_yangye).setOnClickListener(this);
        view.findViewById(R.id.daka_yangye).setOnClickListener(this);
        view.findViewById(R.id.time_detail).setOnClickListener(this);
        view.findViewById(R.id.solar_detail).setOnClickListener(this);
        hourName = (TextView) view.findViewById(R.id.hour_name);
        solarName = (TextView) view.findViewById(R.id.solar_name);
        datas = Arrays.asList("下午不吃饭会心慌哦！","吃饱后短暂午睡可以让身体气血充足。");
        solarDatas = Arrays.asList("谷雨节气后降雨增多，空气中的湿度逐渐加大，要针对其气候特点进行调养！","适宜的膳食有：参蒸鳝段、菊花鳝鱼等，具有祛风湿、舒筋骨、温补气血的功效");
        //SimpleMarqueeView<T>，SimpleMF<T>：泛型T指定其填充的数据类型，比如String，Spanned等
        marqueeView = (SimpleMarqueeView) view.findViewById(R.id.simpleMarqueeView);
        solorMarqueeView = (SimpleMarqueeView) view.findViewById(R.id.solar_simpleMarqueeView);
        marqueeFactory = new SimpleMF(getContext());
        solarMarqueeFactory = new SimpleMF(getContext());
        marqueeFactory.setData(datas);
        solarMarqueeFactory.setData(solarDatas);
        marqueeView.setMarqueeFactory(marqueeFactory);
        solorMarqueeView.setMarqueeFactory(solarMarqueeFactory);
        marqueeView.startFlipping();
        solorMarqueeView.startFlipping();


        series = new ValueLineSeries();
        series.setColor(getResources().getColor(R.color.colorPrimaryDark));

        series.addPoint(new ValueLinePoint("6号", 2.5f));
        series.addPoint(new ValueLinePoint("7号", 1.5f));
        series.addPoint(new ValueLinePoint("8号", 2.5f));
        series.addPoint(new ValueLinePoint("9号", 1.5f));
        series.addPoint(new ValueLinePoint("10号", 2.5f));
        series.addPoint(new ValueLinePoint("11号", 3.5f));
        series.addPoint(new ValueLinePoint("12号", 0.5f));
        series.addPoint(new ValueLinePoint("13号", 1.5f));

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tizhi_test_yangye:
                if (null != BmobUser.getCurrentUser(User.class)) {
                    if (BmobUser.getCurrentUser(User.class).getTestState().equals("0")) {
                        startActivity(new Intent(getContext(),TestingActivity.class));
                    } else {
                        Toast.makeText(getContext(), "您已经测试完了", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "您还没有登录呢", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.daka_yangye:
                startActivity(new Intent(getContext(),ClockActivity.class));
                break;
            case R.id.time_detail:
                BmobQuery<Hour> hourTermsBmobQuery = new BmobQuery<>("Hour");
                hourTermsBmobQuery.findObjects(new FindListener<Hour>() {
                    @Override
                    public void done(List<Hour> list, BmobException e) {
                        if (e == null && list != null) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getHourName().equals(hourName.getText())) {
                                    Intent intent = new Intent(getContext(),SolarDetailActivity.class);
                                    intent.putExtra("imagUrl", list.get(i).getImageUrl());
                                    intent.putExtra("solarIntr", list.get(i).getIntr());
                                    startActivity(intent);
                                    break;
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "网络可能不好呦", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.solar_detail:
                //Toast.makeText(getContext(), solarName.getText(), Toast.LENGTH_SHORT).show();
                BmobQuery<SolarTerms> solarTermsBmobQuery = new BmobQuery<>("SolarTerms");
                solarTermsBmobQuery.findObjects(new FindListener<SolarTerms>() {
                    @Override
                    public void done(List<SolarTerms> list, BmobException e) {
                        if (null == e && null != list) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getSolarName().equals(solarName.getText())) {
                                    Intent intent = new Intent(getContext(),SolarDetailActivity.class);
                                    intent.putExtra("imagUrl", list.get(i).getImageUrl());
                                    intent.putExtra("solarIntr", list.get(i).getSolarTerms());
                                    startActivity(intent);
                                    //Toast.makeText(getContext(), "haha", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "网络可能不好呦", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

                default:

                    break;
        }
    }
}
