package com.zxu.masterofpainting.Cha;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Recommend;

import java.util.List;

public class RecTeaAdapter extends RecyclerView.Adapter<RecTeaAdapter.ViewHolder> {
    private List<Recommend> mRecommendList;

    public RecTeaAdapter(List<Recommend> mRecommendList) {
        this.mRecommendList = mRecommendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suitable_avoid,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Recommend recommend = mRecommendList.get(position);
                Intent intent = new Intent(v.getContext(),ShowTeaActivity.class);
                intent.putExtra("result", recommend.getRecommendIngredientsName());
                v.getContext().startActivity(intent);
            }
        });
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
            simpleDraweeView = itemView.findViewById(R.id.collocation_image_view);
            textView = itemView.findViewById(R.id.collocation_name);
        }
    }
}
