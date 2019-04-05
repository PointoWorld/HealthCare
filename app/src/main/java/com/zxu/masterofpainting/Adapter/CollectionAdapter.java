package com.zxu.masterofpainting.Adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.CollocationDetailActivity;
import com.zxu.masterofpainting.bean.Collection;
import com.zxu.masterofpainting.bean.Collocation;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {
    private List<Collocation> mCollectionList;

    public CollectionAdapter(List<Collocation> mCollocationList) {
        this.mCollectionList = mCollocationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.collectionProductView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Collocation collocation = mCollectionList.get(position);
                Intent intent = new Intent(v.getContext(), CollocationDetailActivity.class);
                intent.putExtra("selectObjectId", collocation.getObjectId());
                v.getContext().startActivity(intent);
            }
        });
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Collocation collocation = mCollectionList.get(position);
                Intent intent = new Intent(v.getContext(), CollocationDetailActivity.class);
                intent.putExtra("selectObjectId", collocation.getObjectId());
                v.getContext().startActivity(intent);
            }
        });

        viewHolder.collectionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Collocation collocation = mCollectionList.get(position);
                Intent intent = new Intent(v.getContext(), CollocationDetailActivity.class);
                intent.putExtra("selectObjectId", collocation.getObjectId());
                v.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Collocation collocation = mCollectionList.get(position);
        holder.collectionProductView.setImageURI(collocation.getCollocationPicture());
        holder.collectionProductName.setText(collocation.getCollocationName());
        holder.collectionProductEfficacy.setText(collocation.getCollocationEfficacy());
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                BmobQuery<Collection> collectionBmobQuery = new BmobQuery<>("Collection");
                collectionBmobQuery.findObjects(new FindListener<Collection>() {
                    @Override
                    public void done(List<Collection> list, BmobException e) {
                        if (e == null) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getmObjectId().equals(collocation.getObjectId())) {
                                    Collection collectionDelete = new Collection();
                                    collectionDelete.setObjectId(list.get(i).getObjectId());
                                    collectionDelete.delete(new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null) {
                                                Toast.makeText(v.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                                mCollectionList.remove(position);
                                                holder.swipeMenuLayout.quickClose();
                                                notifyDataSetChanged();
                                            } else {
                                                Toast.makeText(v.getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                                                holder.swipeMenuLayout.quickClose();
                                            }
                                        }
                                    });
                                }
                            }
                        } else {
                            Toast.makeText(v.getContext(), "收藏查找失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                mCollectionList.remove(position);
//                notifyDataSetChanged();
//                holder.swipeMenuLayout.quickClose();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCollectionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View collectionView;
        SimpleDraweeView collectionProductView;
        TextView collectionProductName;
        TextView collectionProductEfficacy;
        SwipeMenuLayout swipeMenuLayout;
        Button buttonDelete;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            collectionView = itemView;
            linearLayout = (LinearLayout) itemView.findViewById(R.id.kk);
            collectionProductView = (SimpleDraweeView) itemView.findViewById(R.id.collection_simple_drawe_view);
            collectionProductName = (TextView) itemView.findViewById(R.id.collection_product_name);
            collectionProductEfficacy = (TextView) itemView.findViewById(R.id.collection_product_efficacy);
            swipeMenuLayout = (SwipeMenuLayout) itemView.findViewById(R.id.swipeMenuLayout);
            buttonDelete = (Button) itemView.findViewById(R.id.delete_btn);
        }
    }
}
