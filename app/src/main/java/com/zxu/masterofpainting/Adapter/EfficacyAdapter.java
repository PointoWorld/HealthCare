package com.zxu.masterofpainting.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.EfficacyItem;

import java.util.List;

public class EfficacyAdapter extends RecyclerView.Adapter<EfficacyAdapter.ViewHolder> {
    private List<EfficacyItem> mEfficacyItemList;

    public EfficacyAdapter(List<EfficacyItem> mEfficacyItemList) {
        this.mEfficacyItemList = mEfficacyItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_efficacy, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EfficacyItem efficacy = mEfficacyItemList.get(position);
        holder.efficacyTitle.setText(efficacy.getEfficacytitle());
        holder.efficacyContent.setText(efficacy.getEfficacycontent());
    }

    @Override
    public int getItemCount() {
        return mEfficacyItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView efficacyTitle;
        TextView efficacyContent;

        public ViewHolder(View itemView) {
            super(itemView);
            efficacyTitle = (TextView) itemView.findViewById(R.id.efficacy_title);
            efficacyContent = (TextView) itemView.findViewById(R.id.efficacy_content);
        }
    }
}
