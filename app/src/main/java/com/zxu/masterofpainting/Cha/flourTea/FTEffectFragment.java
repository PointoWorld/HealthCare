package com.zxu.masterofpainting.Cha.flourTea;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tencent.liteav.demo.play.SuperPlayerConst;
import com.tencent.liteav.demo.play.SuperPlayerGlobalConfig;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FTEffectFragment extends Fragment {
    private SuperPlayerView mSuperPlayerView;
    private TextView ftTV;
    private TXCloudVideoView txCloudVideoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fteffect, container, false);
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
        model2.videoURL = Constants.fteaIntrVideo;

        // 开始播放
        mSuperPlayerView.playWithMode(model2);
        return view;
    }

    private void initView(View view) {
        mSuperPlayerView = (SuperPlayerView) view.findViewById(R.id.flower_tea_pv);
        ftTV = (TextView) view.findViewById(R.id.ft_effect_tv);
        ftTV.setText(Constants.fteaIntrtext);
        //txCloudVideoView.setAuto
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
}
