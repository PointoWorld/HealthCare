package com.zxu.masterofpainting.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.HealthPreserve.SolarDetailActivity;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.ChaCare;

import java.util.List;

public class ChaCareAdapter extends RecyclerView.Adapter<ChaCareAdapter.ViewHolder> {
    private List<ChaCare> mChaCareList;

    public ChaCareAdapter(List<ChaCare> chaCareList) {
        this.mChaCareList = chaCareList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collocation, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ChaCare chaCare = mChaCareList.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SolarDetailActivity.class);
                intent.putExtra("imagUrl", chaCare.getImagUrl());
                intent.putExtra("solarIntr", chaCare.getContent());
                v.getContext().startActivity(intent);
            }
        });
        holder.simpleDraweeView.setImageURI(chaCare.getImagUrl());
        holder.title.setText(chaCare.getTitle());
        //holder.content.setText(chaCare.getContent());
    }

    @Override
    public int getItemCount() {
        return mChaCareList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        SimpleDraweeView simpleDraweeView;
        TextView title;
        TextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            view = (View) itemView.findViewById(R.id.chacare_linnear);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.collocation_simple_drawe_view);
            title = (TextView) itemView.findViewById(R.id.collocation_product_name);
            content = (TextView) itemView.findViewById(R.id.collocation_product_efficacy);
        }
    }
}
