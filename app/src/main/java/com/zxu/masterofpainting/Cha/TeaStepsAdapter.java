package com.zxu.masterofpainting.Cha;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;

import java.util.List;

public class TeaStepsAdapter extends RecyclerView.Adapter<TeaStepsAdapter.ViewHolder> {
    private List<ItemSteps> mItemStepsList;

    public TeaStepsAdapter(List<ItemSteps> mItemStepsList) {
        this.mItemStepsList = mItemStepsList;
    }

    @Override
    public TeaStepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tea_steps, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TeaStepsAdapter.ViewHolder holder, int position) {
        ItemSteps itemSteps = mItemStepsList.get(position);
        holder.title.setText(itemSteps.getStepTitle());
        holder.content.setText(itemSteps.getStepContent());
        holder.simpleDraweeView.setImageURI(itemSteps.getStepImgUrl());
    }

    @Override
    public int getItemCount() {
        return mItemStepsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        SimpleDraweeView simpleDraweeView;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tea_steps_title);
            content = (TextView) itemView.findViewById(R.id.tea_steps_content);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.tea_steps_simple_view);
        }
    }
}
