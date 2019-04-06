package com.zxu.masterofpainting.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moxun.tagcloudlib.view.TagCloudView;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;

public class HorizontalPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
    }

    @Override
    public int getCount() {
        return Constants.labelNameMenu.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.item, container, false);
        TextView textTig = (TextView) view.findViewById(R.id.tag_title);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.card_back);
        TagCloudView fragmentTagcloud = (TagCloudView) view.findViewById(R.id.fragment_tagcloud);
        //fragmentTagcloud.setBackgroundColor(R.color.colorAccentLight);
        //linearLayout.setBackgroundColor(R.color.colorAccentLight);
        textTig.setText(Constants.labelNameMenu[position]);

        TextTagsAdapter adapter = new TextTagsAdapter(fragmentTagcloud,Constants.labelMenuContent[position]);
        fragmentTagcloud.setAdapter(adapter);

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
