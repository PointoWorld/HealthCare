package com.zxu.masterofpainting.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Step;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private List<Step> mStepList;

    public StepsAdapter(List<Step> stepList) {
        this.mStepList = stepList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_steps, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Step step = mStepList.get(position);
        holder.serialNumber.setText(step.getSerialNumber());
        holder.stepPicture.setImageURI(step.getPictureUrl());
        holder.stepDetails.setText(step.getStepDetails());
    }

    @Override
    public int getItemCount() {
        return mStepList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serialNumber;
        SimpleDraweeView stepPicture;
        TextView stepDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            serialNumber = (TextView) itemView.findViewById(R.id.serial_number);
            stepPicture = (SimpleDraweeView) itemView.findViewById(R.id.step_picture);
            stepDetails = (TextView) itemView.findViewById(R.id.step_details);
        }
    }
}
