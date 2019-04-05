package com.zxu.masterofpainting.HealthPreserve;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Hour;

import java.util.List;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.ViewHolder> {
    private List<Hour> mHourList;

    public HourAdapter(List<Hour> mHourList) {
        this.mHourList = mHourList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Hour hour = mHourList.get(position);
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SolarDetailActivity.class);
                intent.putExtra("imagUrl", hour.getImageUrl());
                intent.putExtra("solarIntr", hour.getIntr());
                v.getContext().startActivity(intent);
            }
        });
        holder.simpleDraweeView.setImageURI(hour.getImageUrl());
        holder.intr.setText(hour.getHourName());
    }

    @Override
    public int getItemCount() {
        return mHourList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView intr;
        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.recommend_simpledrawview);
            intr = (TextView) itemView.findViewById(R.id.recommend_ingredients_name);
        }
    }
}
