package com.zxu.masterofpainting.Cha;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.liteav.demo.play.SuperPlayerConst;
import com.tencent.liteav.demo.play.SuperPlayerGlobalConfig;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ChongPaoFragment extends Fragment {
    private RecyclerView teaRecyclerView;
    private SuperPlayerView mSuperPlayerView;
    private List<ItemSteps> mTeaStepsList = new ArrayList<>();
    // 通过URL方式的视频信息配置
    private SuperPlayerModel model2 = new SuperPlayerModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chong_pao, container, false);
        initView(view);

        // 播放器配置
        SuperPlayerGlobalConfig prefs = SuperPlayerGlobalConfig.getInstance();
        // 开启悬浮窗播放
        prefs.enableFloatWindow = true;
        // 设置悬浮窗的初始位置和宽高
        SuperPlayerGlobalConfig.TXRect rect = new SuperPlayerGlobalConfig.TXRect();
        rect.x = 0;
        rect.y = 0;
        rect.width = 810;
        rect.height = 540;
        prefs.floatViewRect = rect;
        // 播放器默认缓存个数
        prefs.maxCacheItem = 5;
        // 设置播放器渲染模式
        prefs.enableHWAcceleration = true;
        prefs.renderMode = TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;

        // 通过URL方式的视频信息配置
        SuperPlayerModel model2 = new SuperPlayerModel();
        model2.title  = Constants.teaName;
        model2.videoURL = Constants.teachongPao;
        String s = "https://vd3.bdstatic.com/mda-iidkzcr3t2j7sa3v/mda-iidkzcr3t2j7sa3v.mp4?playlist=%5B%22hd%22%2C%22sc%22%5D&vt=1&cd=0&did=fe375095f7294e1718fe87a29be19d28&logid=1184924766&vid=9375042527669561062";

        // 开始播放
        mSuperPlayerView.playWithMode(model2);
        loadData();
        return view;
    }

    private void initView(View view){
        mSuperPlayerView = view.findViewById(R.id.main_super_player_view);
        teaRecyclerView = (RecyclerView) view.findViewById(R.id.tea_recycler_view);
    }

    private void loadData(){
        String[] split = Constants.teasteps.split("#");
        for (int i = 0; i < split.length; i++) {
            String[] itemSplit = split[i].split("@");
            mTeaStepsList.add(new ItemSteps(itemSplit[0], itemSplit[1], itemSplit[2]));
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        teaRecyclerView.setLayoutManager(layoutManager);
        TeaStepsAdapter teaStepsAdapter = new TeaStepsAdapter(mTeaStepsList);
        teaRecyclerView.setAdapter(teaStepsAdapter);
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
