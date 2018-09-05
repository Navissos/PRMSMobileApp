package com.example.stefandragic.bookingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int mCount;
    private ArrayList<View> views = new ArrayList<View>();


    public ViewPagerAdapter(Context context, int count){
        mContext = context;
        mCount = count;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        int index = views.indexOf (object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;

    }

    @Override
    public Object instantiateItem (ViewGroup container, int position) {
        View v = views.get (position);
        container.addView (v);
        return v;
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object) {
        container.removeView (views.get (position));
    }

    @Override
    public int getCount() {
        return views.size();
    }

    public int addView (View v) {
        return addView (v, views.size());
    }

    public int addView (View v, int position) {
        views.add (position, v);
        return position;
    }

    public int removeView (ViewPager pager, int position) {
        pager.setAdapter (null);
        views.remove (position);
        pager.setAdapter (this);

        return position;
    }

    public int removeView (ViewPager pager, View v) {
        return removeView (pager, views.indexOf (v));
    }

    public View getView (int position) {
        return views.get (position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
