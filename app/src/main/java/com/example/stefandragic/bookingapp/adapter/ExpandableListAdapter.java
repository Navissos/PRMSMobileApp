package com.example.stefandragic.bookingapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.stefandragic.bookingapp.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> myBookingRooms;
    private HashMap<String, List<String>> myBookingDetails;

    public ExpandableListAdapter(Context context, List<String> myBookingRooms,
                                 HashMap<String, List<String>> myBookingDetails) {
        this.context = context;
        this.myBookingRooms = myBookingRooms;
        this.myBookingDetails = myBookingDetails;
    }

    public Object getChild(int roomListPosition, int roomDetailListPosition) {
        return this.myBookingDetails.get(this.myBookingRooms.get(roomListPosition))
                .get(roomDetailListPosition);
    }

    public long getChildId(int roomListPosition, int roomDetailListPosition) {
        return roomDetailListPosition;
    }

    public View getChildView(int roomListPosition, final int roomDetailListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(roomListPosition, roomDetailListPosition);
        if (isLastChild) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.my_booking_details_with_buttons, null);
        }
        else {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.my_booking_details, null);
        }

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.my_booking_details, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.myBookingDetails);
        if (expandedListText.equals("Accepted") || expandedListText.equals("Pending") || expandedListText.equals("Declined"))
            expandedListTextView.setTypeface(null, Typeface.BOLD);
        else
            expandedListTextView.setTypeface(null, Typeface.NORMAL);
        expandedListTextView.setText(expandedListText);

        return convertView;
    }

    public int getChildrenCount(int roomListPosition) {
        return this.myBookingDetails.get(this.myBookingRooms.get(roomListPosition))
                .size();
    }

    public Object getGroup(int roomListPosition) {
        return this.myBookingRooms.get(roomListPosition);
    }

    public int getGroupCount() {
        return this.myBookingRooms.size();
    }

    public long getGroupId(int roomListPosition) {
        return roomListPosition;
    }

    public View getGroupView(int roomListPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String myBookingRooms = (String) getGroup(roomListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.my_booking_rooms, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.myBookingRoom);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(myBookingRooms);
        return convertView;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
