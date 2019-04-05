package com.zxu.masterofpainting.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.CollocationActivity;
import com.zxu.masterofpainting.activity.CollocationDetailActivity;
import com.zxu.masterofpainting.activity.TestActivity;
import com.zxu.masterofpainting.bean.SuitableAvoidItem;

import java.util.List;

public class SuitableAvoidAdapter extends RecyclerView.Adapter<SuitableAvoidAdapter.ViewHolder> {
    private List<SuitableAvoidItem> mSuitableAvoidItemList;

    public SuitableAvoidAdapter(List<SuitableAvoidItem> mSuitableAvoidItemList) {
        this.mSuitableAvoidItemList = mSuitableAvoidItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suitable_avoid, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.suitbaleavoidview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                SuitableAvoidItem suitableAvoidItem = mSuitableAvoidItemList.get(position);
                Intent intent = new Intent(v.getContext(),CollocationActivity.class);
                intent.putExtra("collocationName1", Constants.ingredientsName);
                intent.putExtra("collocationName2", suitableAvoidItem.getCollocationName());
                intent.putExtra("urlstring", "http://site.meishij.net/r/64/103/9963314/s9963314_150877710622523.jpg");
                v.getContext().startActivity(intent);
            }
        });
        viewHolder.collocationDrawView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                SuitableAvoidItem suitableAvoidItem = mSuitableAvoidItemList.get(position);
                Intent intent = new Intent(v.getContext(),CollocationActivity.class);
                intent.putExtra("collocationName1", Constants.ingredientsName);
                intent.putExtra("collocationName2", suitableAvoidItem.getCollocationName());
                intent.putExtra("urlstring", "http://site.meishij.net/r/64/103/9963314/s9963314_150877710622523.jpg");
                v.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SuitableAvoidItem suitableAvoidItem = mSuitableAvoidItemList.get(position);
        holder.collocationDrawView.setImageURI("http://site.meishij.net/r/64/103/9963314/s9963314_150877710622523.jpg");
        holder.collocationName.setText(Constants.ingredientsName+" + "+suitableAvoidItem.getCollocationName());
        holder.collocationEfficacy.setText(suitableAvoidItem.getCollocationEfficacy());
    }

    @Override
    public int getItemCount() {
        return mSuitableAvoidItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View suitbaleavoidview;
        SimpleDraweeView collocationDrawView;
        TextView collocationName;
        TextView collocationEfficacy;

        public ViewHolder(View itemView) {
            super(itemView);
            suitbaleavoidview = itemView;
            collocationDrawView = (SimpleDraweeView) itemView.findViewById(R.id.collocation_image_view);
            collocationName = (TextView) itemView.findViewById(R.id.collocation_name);
            collocationEfficacy = (TextView) itemView.findViewById(R.id.collocation_efficacy);
        }
    }
}
