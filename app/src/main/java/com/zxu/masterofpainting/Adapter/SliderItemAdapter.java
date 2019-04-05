package com.zxu.masterofpainting.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.model.SliderItemReco;

import java.util.List;

public class SliderItemAdapter extends RecyclerView.Adapter<SliderItemAdapter.ViewHolder> {
    private List<SliderItemReco> mSliderItemRecoList;

    public SliderItemAdapter(List<SliderItemReco> sliderItemRecoList) {
        this.mSliderItemRecoList = sliderItemRecoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SliderItemReco sliderItemReco = mSliderItemRecoList.get(position);
        holder.simpleDraweeView.setImageURI(sliderItemReco.getImageUrl());
        holder.collocationName.setText(sliderItemReco.getCollocationName());
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), sliderItemReco.getCollocationName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSliderItemRecoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView simpleDraweeView;
        private TextView collocationName;

        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.recommend_simpledrawview);
            collocationName = (TextView) itemView.findViewById(R.id.recommend_ingredients_name);
        }
    }
}
