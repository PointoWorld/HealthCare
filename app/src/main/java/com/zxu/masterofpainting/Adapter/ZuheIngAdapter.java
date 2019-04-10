package com.zxu.masterofpainting.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Recommend;

import java.util.List;

public class ZuheIngAdapter extends RecyclerView.Adapter<ZuheIngAdapter.ViewHolder> {
    private List<Recommend> mRecommendList;

    public ZuheIngAdapter(List<Recommend> mRecommendList) {
        this.mRecommendList = mRecommendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_combin_ingre, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recommend recommend = mRecommendList.get(position);
        holder.simpleDraweeView.setImageURI(recommend.getPictureUrl());
        holder.textView.setText(recommend.getRecommendIngredientsName());
    }

    @Override
    public int getItemCount() {
        return mRecommendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.zuhe_sd);
            textView = (TextView) itemView.findViewById(R.id.zh_tv);
        }
    }
}
