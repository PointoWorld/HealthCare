package com.zxu.masterofpainting.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.zxu.masterofpainting.HealthPreserve.ChaCareActivity;
import com.zxu.masterofpainting.HealthPreserve.ChineseMedicineActivity;
import com.zxu.masterofpainting.HealthPreserve.HourDetailActivity;
import com.zxu.masterofpainting.HealthPreserve.SolarTermsActivity;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.MineralActivity;
import com.zxu.masterofpainting.activity.SliderActivity;
import com.zxu.masterofpainting.activity.TestingActivity;
import com.zxu.masterofpainting.activity.VitaminActivity;
import com.zxu.masterofpainting.bean.Slider;
import com.zxu.masterofpainting.bean.User;

import java.util.HashMap;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;


public class InfoFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,View.OnClickListener{
    private SliderLayout mDemoSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        initView(view);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("补血益气粥", "https://i3.meishichina.com/attachment/magic/2019/03/08/2019030815520112062878197577.jpg");
        url_maps.put("春天皮肤容易干燥", "https://i3.meishichina.com/attachment/mofang/2019/03/11/20190311155228762132110169539.jpg");
        url_maps.put("补血益", "https://i3.meishichina.com/attachment/magic/2019/03/01/2019030115514054909578197577.jpg");
//        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Hannibal",R.drawable.tu);
//        file_maps.put("Big Bang Theory",R.drawable.tu);
//        file_maps.put("House of Cards",R.drawable.tu);
//        file_maps.put("Game of Thrones", R.drawable.tu);
        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);
        mDemoSlider.addOnPageChangeListener(this);

        return view;
    }

    private void initView(View view) {
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        view.findViewById(R.id.tizhi_test).setOnClickListener(this);
        view.findViewById(R.id.changshi).setOnClickListener(this);
        view.findViewById(R.id.vitamin_imgview).setOnClickListener(this);
        view.findViewById(R.id.mineral_imag_view).setOnClickListener(this);
        view.findViewById(R.id.zhyi_img).setOnClickListener(this);
        view.findViewById(R.id.solar_imag_view).setOnClickListener(this);
        view.findViewById(R.id.hour_img).setOnClickListener(this);
        view.findViewById(R.id.cha_care).setOnClickListener(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        if (slider.getBundle().get("extra").equals("春天皮肤容易干燥")) {
            BmobQuery<Slider> sliderBmobQuery = new BmobQuery<>("Slider");
            sliderBmobQuery.getObject("8228d229f7", new QueryListener<Slider>() {
                @Override
                public void done(Slider slider, BmobException e) {
                    if (null == e && null != slider) {
                        Intent intent = new Intent(getContext(),SliderActivity.class);
                        intent.putExtra("sliderintr", slider.getSliderIntr());
                        intent.putExtra("slidercontent", slider.getSliderContent());
                        startActivity(intent);
                    }
                }
            });
        }
        Toast.makeText(getContext(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhyi_img:
                startActivity(new Intent(getContext(),ChineseMedicineActivity.class));
                break;
            case R.id.cha_care:
                startActivity(new Intent(getContext(),ChaCareActivity.class));
                break;
            case R.id.solar_imag_view:
                startActivity(new Intent(getContext(),SolarTermsActivity.class) );
                break;
            case R.id.hour_img:
                startActivity(new Intent(getContext(),HourDetailActivity.class));
                break;
            case R.id.tizhi_test:
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
            case R.id.changshi:
                Toast.makeText(getContext(), "changshi1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.vitamin_imgview:
                startActivity(new Intent(getContext(),VitaminActivity.class));
                break;
            case R.id.mineral_imag_view:
                Intent intent = new Intent(getContext(),MineralActivity.class);
                startActivity(intent);
                break;
        }
    }
}
