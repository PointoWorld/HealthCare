package com.zxu.masterofpainting.Recommend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.SolarTea;

import java.util.List;

public class SolarTeaAdapter extends RecyclerView.Adapter<SolarTeaAdapter.ViewHolder> {
    private List<SolarTea> mSolarTeaList;

    public SolarTeaAdapter(List<SolarTea> mSolarTeaList) {
        this.mSolarTeaList = mSolarTeaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solar_tea, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SolarTea solarTea = mSolarTeaList.get(position);
        holder.title.setText(solarTea.getTeaName());
        holder.simpleDraweeView.setImageURI(solarTea.getImgUrl());
        holder.intr.setText(solarTea.getTeaIntr());
    }

    @Override
    public int getItemCount() {
        return mSolarTeaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        SimpleDraweeView simpleDraweeView;
        TextView intr;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.solar_tea_title);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.solar_tea_sd);
            intr = (TextView) itemView.findViewById(R.id.solar_tea_tv);
        }
    }
}
