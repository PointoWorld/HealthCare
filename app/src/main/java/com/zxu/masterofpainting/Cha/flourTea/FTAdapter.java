package com.zxu.masterofpainting.Cha.flourTea;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.SolarTea;

import java.util.List;

public class FTAdapter extends RecyclerView.Adapter<FTAdapter.ViewHolder> {
    private List<SolarTea> mSolarTeaList;

    public FTAdapter(List<SolarTea> mSolarTeaList) {
        this.mSolarTeaList = mSolarTeaList;
    }

    @Override
    public FTAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solar_tea, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FTAdapter.ViewHolder holder, int position) {
        final SolarTea solarTea = mSolarTeaList.get(position);
        holder.title.setText(solarTea.getTeaName());
        holder.simpleDraweeView.setImageURI(solarTea.getImgUrl());
        holder.intr.setText(solarTea.getTeaIntr());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ShowFTeaActivity.class);
                intent.putExtra("flowerTea", solarTea.getTeaName());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSolarTeaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView title;
        SimpleDraweeView simpleDraweeView;
        TextView intr;
        public ViewHolder(View itemView) {
            super(itemView);
            view = (CardView) itemView.findViewById(R.id.tea_card_view);
            title = (TextView) itemView.findViewById(R.id.solar_tea_title);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.solar_tea_sd);
            intr = (TextView) itemView.findViewById(R.id.solar_tea_tv);
        }
    }
}
