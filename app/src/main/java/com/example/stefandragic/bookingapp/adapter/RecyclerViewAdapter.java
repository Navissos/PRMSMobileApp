package com.example.stefandragic.bookingapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.stefandragic.bookingapp.R;
import com.example.stefandragic.bookingapp.dummies.ResourceDummy;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<ResourceDummy> mValues;
    Context mContext;
    protected ItemListener mListener;

    public RecyclerViewAdapter(Context mContext, ArrayList<ResourceDummy> mValues, ItemListener mListener) {

        this.mContext = mContext;
        this.mValues = mValues;
        this.mListener = mListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        ResourceDummy resItem;

        public ViewHolder(View v) {

            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(ResourceDummy resItem) {
            this.resItem = resItem;

            if(resItem.getRoomNumber()==0){
                textView.setText(resItem.getRoomType());
            }else{ textView.setText(resItem.getRoomType() + " " + resItem.getRoomNumber()); }

            switch(resItem.getRoomType()){

                    case "Scrum":
                        imageView.setImageResource(R.drawable.scrum_icon);
                        relativeLayout.setBackgroundColor(Color.parseColor("#09A9FF"));
                        break;
                    case "Conference":
                        imageView.setImageResource(R.drawable.conference_icon);
                        relativeLayout.setBackgroundColor(Color.parseColor("#3E51B1"));
                        break;
                    case "Breakroom":
                        imageView.setImageResource(R.drawable.break_room_icon_small);
                        relativeLayout.setBackgroundColor(Color.parseColor("#673BB7"));
                        break;
                    case "Boardroom":
                        imageView.setImageResource(R.drawable.boardroom_icon_small);
                        relativeLayout.setBackgroundColor(Color.parseColor("#4BAA50"));
                        break;
                    case "Recreation":
                        imageView.setImageResource(R.drawable.recreation_icon);
                        relativeLayout.setBackgroundColor(Color.parseColor("#F94336"));
                        break;
                }
            }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(resItem);
            }
        }
    }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);
            return new ViewHolder(view);
//          }
        }

        @Override
        public void onBindViewHolder(ViewHolder Vholder, int position) {
            Vholder.setData(mValues.get(position));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public interface ItemListener {
            void onItemClick(ResourceDummy resItem);
        }
    }
