package com.zxu.masterofpainting.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 右侧主界面ListView的适配器
 *
 * @author zxu
 */
public class HomeAdapter extends BaseAdapter {

    private Context context;
    private List<String[] > foodDatas;
    private List<String> menuList;

    public HomeAdapter(Context context, List<String[]> foodDatas,List<String> menuList) {
        this.context = context;
        this.foodDatas = foodDatas;
        this.menuList = menuList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        String[] dataBean = foodDatas.get(position);
        List<String> dataList = Arrays.asList(dataBean);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home, null);
            viewHold = new ViewHold();
            viewHold.gridView = (GridViewForScrollView) convertView.findViewById(R.id.gridView_home);
            viewHold.blank = (TextView) convertView.findViewById(R.id.blank_home);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        LabelMenuContenAdapter adapter = new LabelMenuContenAdapter(context, dataList);
        viewHold.blank.setText(menuList.get(position));
        viewHold.gridView.setAdapter(adapter);
        return convertView;
    }

    private static class ViewHold {
        private GridViewForScrollView gridView;
        private TextView blank;
    }

}
