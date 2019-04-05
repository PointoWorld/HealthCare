package com.zxu.masterofpainting.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.CollocationDetailActivity;
import com.zxu.masterofpainting.bean.Collocation;

import java.util.List;

public class CollocationAdapter extends RecyclerView.Adapter<CollocationAdapter.ViewHolder> {
    private List<Collocation> mCollocationList;

    public CollocationAdapter(List<Collocation> collocationList) {
        this.mCollocationList = collocationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collocation, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.collocationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Collocation collocation = mCollocationList.get(position);
                Intent intent = new Intent(v.getContext(), CollocationDetailActivity.class);
                intent.putExtra("selectObjectId", collocation.getObjectId());
                v.getContext().startActivity(intent);
            }
        });
        viewHolder.collocationProductView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Collocation collocation = mCollocationList.get(position);
                Intent intent = new Intent(v.getContext(), CollocationDetailActivity.class);
                intent.putExtra("selectObjectId", collocation.getObjectId());
                v.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Collocation collocation = mCollocationList.get(position);
        holder.collocationProductView.setImageURI(collocation.getCollocationPicture());
        holder.collocationProductName.setText(collocation.getCollocationName());
        holder.collocationProductEfficacy.setText(collocation.getCollocationEfficacy());
    }

    @Override
    public int getItemCount() {
        return mCollocationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View collocationView;
        SimpleDraweeView collocationProductView;
        TextView collocationProductName;
        TextView collocationProductEfficacy;
        public ViewHolder(View itemView) {
            super(itemView);
            collocationView = itemView;
            collocationProductView = (SimpleDraweeView) itemView.findViewById(R.id.collocation_simple_drawe_view);
            collocationProductName = (TextView) itemView.findViewById(R.id.collocation_product_name);
            collocationProductEfficacy = (TextView) itemView.findViewById(R.id.collocation_product_efficacy);
        }
    }
}
