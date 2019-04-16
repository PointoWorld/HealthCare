package com.zxu.masterofpainting.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zxu.masterofpainting.R;

import java.util.ArrayList;

public class CupSeeActivity extends AppCompatActivity {
    private BarChart chart;
    BarDataSet set1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cup_see);
        chart = findViewById(R.id.chart1);

        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(1, 2));
        values.add(new BarEntry(2, 4));
        values.add(new BarEntry(3, 1));
        values.add(new BarEntry(4, 5));

        set1 = new BarDataSet(values, "Data Set");
        set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set1.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        chart.setData(data);
        chart.setFitBars(true);
    }
}
