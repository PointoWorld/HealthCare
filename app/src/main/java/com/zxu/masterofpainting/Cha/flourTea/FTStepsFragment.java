package com.zxu.masterofpainting.Cha.flourTea;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.liteav.demo.play.SuperPlayerConst;
import com.tencent.liteav.demo.play.SuperPlayerGlobalConfig;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;
import com.tencent.rtmp.TXLiveConstants;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FTStepsFragment extends Fragment {
    private SuperPlayerView mSuperPlayerView;
    private TextView ftTV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ftsteps, container, false);
        initView(view);

        // 播放器配置
        SuperPlayerGlobalConfig prefs = SuperPlayerGlobalConfig.getInstance();
        // 播放器默认缓存个数
        prefs.maxCacheItem = 2;
        // 设置播放器渲染模式
        prefs.enableHWAcceleration = true;
        prefs.renderMode = TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;

        // 通过URL方式的视频信息配置
        SuperPlayerModel model2 = new SuperPlayerModel();
        model2.title  = Constants.fteaName;
        model2.videoURL = Constants.fteastepsVideo;
        // 开始播放
        mSuperPlayerView.playWithMode(model2);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSuperPlayerView.onPause();
            }
        }, 1800);//3秒后执行Runnable中的run方法
        return view;
    }

    private void initView(View view) {
        mSuperPlayerView = (SuperPlayerView) view.findViewById(R.id.flower_tea_steps_pv);
        ftTV = (TextView) view.findViewById(R.id.ft_steps_tv);
        ftTV.setText(Constants.fteastepstext);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 重新开始播放
        //Toast.makeText(getContext(), "restart", Toast.LENGTH_SHORT).show();
        if (mSuperPlayerView.getPlayState() == SuperPlayerConst.PLAYSTATE_PLAY) {
            mSuperPlayerView.onResume();
            if (mSuperPlayerView.getPlayMode() == SuperPlayerConst.PLAYMODE_FLOAT) {
                mSuperPlayerView.requestPlayMode(SuperPlayerConst.PLAYMODE_WINDOW);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // 停止播放
        if (mSuperPlayerView.getPlayMode() != SuperPlayerConst.PLAYMODE_FLOAT) {
            mSuperPlayerView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 释放
        mSuperPlayerView.release();
        if (mSuperPlayerView.getPlayMode() != SuperPlayerConst.PLAYMODE_FLOAT) {
            mSuperPlayerView.resetPlayer();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //Toast.makeText(getContext(), "On start", Toast.LENGTH_SHORT).show();
    }
}
