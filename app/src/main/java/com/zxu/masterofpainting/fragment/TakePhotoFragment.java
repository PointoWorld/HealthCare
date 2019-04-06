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
import android.widget.Toast;

import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.laocaixw.layout.SuspendButtonLayout;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.PhysiqueActivity;
import com.zxu.masterofpainting.activity.ShowIngredientsActivity;
import com.zxu.masterofpainting.activity.TakePhotoActivity;

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

import static android.app.Activity.RESULT_OK;

public class TakePhotoFragment extends Fragment {
    private Handler handler = null;
    private Bitmap bitmap;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOSE_PHOTO = 2;

    private ImageView picture_Distinguish;
    private Uri imageUri;
    private SuspendButtonLayout suspendButtonLayout;
    SimpleMarqueeView<String> marqueeView;
    ValueLineChart mCubicValueLineChart;
    ValueLineSeries series;
    SimpleMF<String> marqueeFactory;
    List<String> datas;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_takephoto,container,false);
        iniview(view);

        return view;
    }

    public void iniview(View view){
        mCubicValueLineChart = (ValueLineChart) view.findViewById(R.id.cubiclinechart);
        datas = Arrays.asList("中午不吃饭会心慌哦！","吃饱后短暂午睡可以让身体气血充足。");
        //SimpleMarqueeView<T>，SimpleMF<T>：泛型T指定其填充的数据类型，比如String，Spanned等
        marqueeView = (SimpleMarqueeView) view.findViewById(R.id.simpleMarqueeView);
        marqueeFactory = new SimpleMF(getContext());
        marqueeFactory.setData(datas);
        marqueeView.setMarqueeFactory(marqueeFactory);
        marqueeView.startFlipping();


        series = new ValueLineSeries();
        series.setColor(getResources().getColor(R.color.colorPrimaryDark));

        series.addPoint(new ValueLinePoint("6号", 2.5f));
        series.addPoint(new ValueLinePoint("7号", 1.5f));
        series.addPoint(new ValueLinePoint("8号", 1.5f));
        series.addPoint(new ValueLinePoint("9号", 3.5f));
        series.addPoint(new ValueLinePoint("10号", 5.5f));

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
    }

}
