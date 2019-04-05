package com.zxu.masterofpainting.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.NutritionItem;

import java.util.List;

public class NutritionalAdapter extends ArrayAdapter<NutritionItem> {
    private int resourceId;

    public NutritionalAdapter(Context context, int resource, List<NutritionItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NutritionItem ingredientsInformation = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView compositionName = (TextView) view.findViewById(R.id.composition_name);
        TextView compotionJiliang = (TextView) view.findViewById(R.id.composition_jiliang);
        compositionName.setText(ingredientsInformation.getCompositioNname());
        compotionJiliang.setText(ingredientsInformation.getCompositionZongliang());
        return view;
    }
}
