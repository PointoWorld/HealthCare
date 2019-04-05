package com.zxu.masterofpainting.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.Material;

import java.util.List;


public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {
    private List<Material> mMaterialList;

    public MaterialAdapter(List<Material> mMaterialList) {
        this.mMaterialList = mMaterialList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collocation_ingredients, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Material material = mMaterialList.get(position);
        holder.materialName.setText(material.getMaterialName());
        holder.materialConsumption.setText(material.getMaterialConsumption());
    }

    @Override
    public int getItemCount() {
        return mMaterialList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView materialName;
        TextView materialConsumption;

        public ViewHolder(View itemView) {
            super(itemView);
            materialName = (TextView) itemView.findViewById(R.id.material_name);
            materialConsumption = (TextView) itemView.findViewById(R.id.material_Consumption);
        }
    }
}
