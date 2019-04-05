package com.zxu.masterofpainting.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.paulyung.laybellayout.LaybelLayout;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.activity.LabelDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class LabelMenuContenAdapter extends BaseAdapter {
    private Context context;
    private List<String> foodDatas;

    public LabelMenuContenAdapter(Context context, List<String> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }

    @Override
    public int getCount() {
        if (foodDatas != null) {
            return foodDatas.size();
        } else {
            return 11;
        }
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final String dataBean = foodDatas.get(position);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_label_content, null);
            viewHold = new ViewHold();
            viewHold.lable = (TextView) convertView.findViewById(R.id.haha);
            viewHold.lable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,LabelDetailActivity.class);
                    intent.putExtra("labelName", dataBean);
                    context.startActivity(intent);
                }
            });
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.lable.setText(dataBean);
        return convertView;
    }

    private static class ViewHold {
        private TextView lable;
    }
}
