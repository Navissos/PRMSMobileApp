package com.example.stefandragic.bookingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stefandragic.bookingapp.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final ArrayList<String> dateList, roomList, timeList;
    private Context mContext;

    public ListViewAdapter(Context context, ArrayList<String> dateList, ArrayList<String> roomList, ArrayList<String> timeList) {
        this.mContext = context;
        this.dateList = dateList;
        this.roomList = roomList;
        this.timeList = timeList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void clear(){
        dateList.clear();
        roomList.clear();
        timeList.clear();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dateList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentView = convertView;

        if(currentView == null) {
            currentView = mInflater.inflate(R.layout.list_item, parent, false);
        }

        TextView tView = (TextView) currentView.findViewById(R.id.tv1);
        tView.setText(dateList.get(position));

        tView = (TextView) currentView.findViewById(R.id.tv2);
        tView.setText(roomList.get(position));

        tView = (TextView) currentView.findViewById(R.id.tv3);
        tView.setText(timeList.get(position));

        return currentView;
    }


}
