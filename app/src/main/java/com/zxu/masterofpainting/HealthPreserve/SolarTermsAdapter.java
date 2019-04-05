package com.zxu.masterofpainting.HealthPreserve;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.SolarTerms;

import java.util.List;

public class SolarTermsAdapter extends RecyclerView.Adapter<SolarTermsAdapter.ViewHolder> {
    private List<SolarTerms> solarTermsList;

    public SolarTermsAdapter(List<SolarTerms> solarTermsList) {
        this.solarTermsList = solarTermsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SolarTerms solarTerms = solarTermsList.get(position);
        holder.simpleDraweeView.setImageURI(solarTerms.getImageUrl());
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SolarDetailActivity.class);
                intent.putExtra("imagUrl", solarTerms.getImageUrl());
                intent.putExtra("solarIntr", solarTerms.getSolarTerms());
                v.getContext().startActivity(intent);
            }
        });
        holder.intr.setText(solarTerms.getSolarName()+"养生");
    }

    @Override
    public int getItemCount() {
        return solarTermsList.size();
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
