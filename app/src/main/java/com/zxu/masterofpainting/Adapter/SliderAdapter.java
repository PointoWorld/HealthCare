package com.zxu.masterofpainting.Adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.model.SliderItem;
import com.zxu.masterofpainting.model.SliderItemReco;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {
    private List<SliderItem> sliderItemList;

    public SliderAdapter(List<SliderItem> sliderItemList) {
        this.sliderItemList = sliderItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<SliderItemReco> sliderItemRecosList = new ArrayList<>();
        SliderItem sliderItem = sliderItemList.get(position);
        String recommendCollo = sliderItem.getRecommendCollocation();
        String[] split = recommendCollo.split(";");
        for (int i = 0; i < split.length; i++) {
            sliderItemRecosList.add(new SliderItemReco(split[i].split(",")[0], split[i].split(",")[1]));
        }
        holder.sliderIngredients.setText(sliderItem.getIngredientsName());
        holder.sliderIntr.setText(sliderItem.getSliderIntroduce());
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        holder.sliderRecyclerView.setLayoutManager(layoutManager);
        SliderItemAdapter sliderItemAdapter = new SliderItemAdapter(sliderItemRecosList);
        holder.sliderRecyclerView.setAdapter(sliderItemAdapter);
    }

    @Override
    public int getItemCount() {
        return sliderItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sliderIngredients;
        private TextView sliderIntr;
        private RecyclerView sliderRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            sliderIngredients = (TextView) itemView.findViewById(R.id.item_slider_ingredients);
            sliderIntr = (TextView) itemView.findViewById(R.id.slider_introduce);
            sliderRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_slider_recyclerview);
        }
    }
}
