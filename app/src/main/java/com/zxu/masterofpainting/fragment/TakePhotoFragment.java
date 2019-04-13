package com.zxu.masterofpainting.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.HealthPreserve.SolarDetailActivity;
import com.zxu.masterofpainting.MyApplication;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.ClockActivity;
import com.zxu.masterofpainting.activity.TestingActivity;
import com.zxu.masterofpainting.bean.Hour;
import com.zxu.masterofpainting.bean.SolarTerms;
import com.zxu.masterofpainting.bean.User;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class TakePhotoFragment extends Fragment implements View.OnClickListener{
    ValueLineChart mCubicValueLineChart;
    ValueLineSeries series;
    private RadarChart chart;
    private TextView hourName;
    private TextView solarName;
    List<String> datas;
    List<String> solarDatas;
    SimpleMarqueeView<String> marqueeView;
    SimpleMarqueeView<String> solorMarqueeView;
    SimpleMF<String> marqueeFactory;
    SimpleMF<String> solarMarqueeFactory;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_takephoto,container,false);
        iniview(view);

        return view;
    }

    public void iniview(View view){
        mCubicValueLineChart = (ValueLineChart) view.findViewById(R.id.cubiclinechart);
        hourName = (TextView) view.findViewById(R.id.hour_name);
        solarName = (TextView) view.findViewById(R.id.solar_name);
        view.findViewById(R.id.tizhi_test_yangye).setOnClickListener(this);
        view.findViewById(R.id.daka_yangye).setOnClickListener(this);
        view.findViewById(R.id.time_detail).setOnClickListener(this);
        view.findViewById(R.id.solar_detail).setOnClickListener(this);
        getCurrentTime();
        loadAllData(view);
    }
    private void getCurrentTime(){
        int month = MyApplication.getInstance().getMonth();
        int hour = MyApplication.getInstance().getHour();
        String hourname = currentHourName(hour);
        hourName.setText(hourname);
        solarName.setText(Constants.solar[month]);
    }
    private void loadAllData(View view){
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



        chart = view.findViewById(R.id.chart1);

        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(2f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        setSanJiaoData();

        chart.animateXY(1400, 1400, Easing.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
        //xAxis.setTypeface(tfLight);
        xAxis.setTextSize(18f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private final String[] mActivities = new String[]{"自由基", "抗辐射", "热量"};
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        YAxis yAxis = chart.getYAxis();
        //yAxis.setTypeface(tfLight);
        yAxis.setLabelCount(3, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //l.setTypeface(tfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(getResources().getColor(R.color.colorPrimaryDark));


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
                BmobQuery<SolarTerms> solarTermsBmobQuery = new BmobQuery<>("SolarTerms");
                solarTermsBmobQuery.findObjects(new FindListener<SolarTerms>() {
                    @Override
                    public void done(List<SolarTerms> list, BmobException e) {
                        if (null == e && null != list) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getSolarName().equals(solarName.getText())) {
                                    Intent intent = new Intent(getContext(),SolarDetailActivity.class);
                                    intent.putExtra("imagUrl", list.get(i).getImageUrl());
                                    intent.putExtra("solarTea", list.get(i).getTea());
                                    intent.putExtra("solarFruits", list.get(i).getFuirts());
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

    private void setSanJiaoData() {
        float mul = 80;
        float min = 20;
        int cnt = 3;
        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();

            entries1.add(new RadarEntry(40));
            entries1.add(new RadarEntry(20));
            entries1.add(new RadarEntry(30));

            entries2.add(new RadarEntry(30));
            entries2.add(new RadarEntry(60));
            entries2.add(new RadarEntry(20));


        RadarDataSet set1 = new RadarDataSet(entries1, "昨日");
        set1.setColor(getResources().getColor(R.color.colorAccent));
        set1.setFillColor(getResources().getColor(R.color.colorPrimaryDark));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "今日");
        set2.setColor(getResources().getColor(R.color.colorPrimaryDark));
        set2.setFillColor(getResources().getColor(R.color.colorPrimaryDark));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        //data.setValueTypeface(tfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(getResources().getColor(R.color.colorPrimaryDark));

        chart.setData(data);
        chart.invalidate();
    }

    private String currentHourName(int hour){
        if (7 <= hour && hour < 9) {
            return Constants.hour[0];
        } else if (9 <= hour && hour < 11) {
            return Constants.hour[1];
        } else if (11 <= hour && hour < 13) {
            return Constants.hour[2];
        } else if (13 <= hour && hour < 15) {
            return Constants.hour[3];
        } else if (15 <= hour && hour < 17) {
            return Constants.hour[4];
        } else if (17 <= hour && hour < 19) {
            return Constants.hour[5];
        } else if (19 <= hour && hour < 21) {
            return Constants.hour[6];
        } else if (21 <= hour && hour < 23) {
            return Constants.hour[7];
        } else if (1 <= hour && hour < 3) {
            return Constants.hour[9];
        } else if (3 <= hour && hour < 5) {
            return Constants.hour[10];
        } else if (5 <= hour && hour < 7) {
            return Constants.hour[11];
        }
        return Constants.hour[8];
    }
}
