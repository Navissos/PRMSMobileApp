package com.example.stefandragic.bookingapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.stefandragic.bookingapp.adapter.ExpandableListAdapter;
import com.example.stefandragic.bookingapp.adapter.RecyclerViewAdapter;
import com.example.stefandragic.bookingapp.adapter.ViewPagerAdapter;
import com.example.stefandragic.bookingapp.dummies.ReservationDummy;
import com.example.stefandragic.bookingapp.dummies.ResourceDummy;
import com.example.stefandragic.bookingapp.manager.AutoFitGridLayoutManager;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;
import com.example.stefandragic.bookingapp.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemListener {

    private SlidingUpPanelLayout mLayout;
    private ViewPager pager = null;
    private ViewPagerAdapter vpAdapter = null;
    private android.widget.ExpandableListAdapter expandableListAdapter;
    private Button strTimeBtn;
    private Button endTimeBtn;
    private Button submitTimeBtn;

    private ArrayList<String> dateList;
    private ArrayList<String> roomList;
    private ArrayList<String> timeList;

    private ArrayList<ResourceDummy> menuList;
    private ArrayList<ResourceDummy> resourceList;
    private ArrayList<ResourceDummy> queryList;
    private ArrayList<ReservationDummy> resrvList;
    private List<String> myBookingRooms;
    private List<CalendarDay> calList;
    private HashMap<String, List<String>> myBookingDetails;

    private ListViewAdapter LVadapter;
    private RecyclerViewAdapter RVadapter1;
    private RecyclerViewAdapter RVadapter2;
    private MaterialCalendarView bookingCal;

    private ReservationDummy unidum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = findViewById(R.id.activity_main);

        initDummyData();
        initial();
        pager.setCurrentItem(5);
        mLayout.setPanelState(PanelState.HIDDEN);

        Button bookRes_btn = vpAdapter.getView(1).findViewById(R.id.book_resource_btn);
        bookRes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unidum = new ReservationDummy();
                pager.setCurrentItem(2);
            }
        });

        ImageButton home_btn = findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });

        Button my_res_btn = vpAdapter.getView(1).findViewById(R.id.my_reserv_btn);
        my_res_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(0);
            }
        });

        ImageButton stngBtn = findViewById(R.id.settingsBtn);
        stngBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLayout.getPanelState() == PanelState.EXPANDED || mLayout.getPanelState() == PanelState.ANCHORED){
                    filterDialog();
                }
                else
                    pager.setCurrentItem(6);
            }
        });

        Button loginButton = vpAdapter.getView(5).findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(1);
                mLayout.setPanelState(PanelState.COLLAPSED);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(R.layout.my_toolbar);
                }
            }
        });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.action_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        private void initial() {

            LayoutInflater myInflator = getLayoutInflater();
            vpAdapter = new ViewPagerAdapter(this, 0);
            pager = (ViewPager) findViewById(R.id.main_pager);
            View reservationView = myInflator.inflate(R.layout.activity_expandable, null);
            View landView = myInflator.inflate(R.layout.activity_landing_page, null);
            View bookView = myInflator.inflate(R.layout.activity_blank, null);
            View loginView = myInflator.inflate(R.layout.login, null);
            View settingView = myInflator.inflate(R.layout.settings, null);
            RecyclerView resTypeView = (RecyclerView) myInflator.inflate(R.layout.recycler_view_element, pager, false).findViewById(R.id.recyclerView);
            RecyclerView resNumView = (RecyclerView) myInflator.inflate(R.layout.recycler_view_element, pager, false).findViewById(R.id.recyclerView);
            vpAdapter.addView(reservationView); //0
            vpAdapter.addView(landView);        //1
            vpAdapter.addView(resTypeView);     //2
            vpAdapter.addView(resNumView);      //3
            vpAdapter.addView(bookView);        //4
            vpAdapter.addView(loginView);       //5
            vpAdapter.addView(settingView);     //6

            AutoFitGridLayoutManager layoutManager1 = new AutoFitGridLayoutManager(this, 500);
            resTypeView.setLayoutManager(layoutManager1);

            AutoFitGridLayoutManager layoutManager2 = new AutoFitGridLayoutManager(this, 500);
            resNumView.setLayoutManager(layoutManager2);

            RVadapter1 = new RecyclerViewAdapter(vpAdapter.getView(2).getContext(), menuList, this);
            resTypeView.setAdapter(RVadapter1);

            RVadapter2 = new RecyclerViewAdapter(vpAdapter.getView(3).getContext(), queryList, this);
            resNumView.setAdapter(RVadapter2);

            if (resTypeView.getParent() != null || resNumView.getParent() != null) {
                ((ViewGroup) resTypeView.getParent()).removeView(resTypeView);
                vpAdapter.addView(resTypeView);
                ((ViewGroup) resNumView.getParent()).removeView(resNumView);
                vpAdapter.addView(resNumView);
            }

            vpAdapter.notifyDataSetChanged();
            pager.setAdapter(vpAdapter);

            ListView listView = listView = (ListView) findViewById(R.id.bookingsLV);
            View view = getLayoutInflater().inflate(R.layout.list_header, null);
            LinearLayout listViewHeader = (LinearLayout) view.findViewById(R.id.list_view_header);
            LVadapter = new ListViewAdapter(this, dateList, roomList, timeList);
            listView.addHeaderView(listViewHeader);
            listView.setAdapter(LVadapter);
            listView.setSelected(true);

            ExpandableListView expandView = vpAdapter.getView(0).findViewById(R.id.expandableListView);
            myBookingDetails = MyBookingListPushData.pushData();
            myBookingRooms = new ArrayList<String>(myBookingDetails.keySet());
            expandableListAdapter = new ExpandableListAdapter(this, myBookingRooms, myBookingDetails);
            expandView.setAdapter(expandableListAdapter);

            CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
            calendarView.showCurrentMonthPage();
            calendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    Calendar cal = eventDay.getCalendar();
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH)+1;
                    int year = cal.get(Calendar.YEAR);
                    dateList.clear();   roomList.clear();   timeList.clear();
                    LVadapter.notifyDataSetChanged();

                    for(ReservationDummy rd : resrvList){
                        for(CalendarDay select : rd.getDates()){
                            if(select.getYear() == year && select.getMonth()+1 == month && select.getDay() == day) {
                                dateList.add(select.getMonth() + "-" + select.getDay() + "-" + select.getYear());
                                roomList.add(rd.getResRoom().getRoomType() + " " + rd.getResRoom().getRoomNumber());
                                timeList.add(rd.getStartTime() + " - " + rd.getEndTime());
                                LVadapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
            });

            bookingCal = vpAdapter.getView(4).findViewById(R.id.bookingCalendar);
            strTimeBtn = vpAdapter.getView(4).findViewById(R.id.start_time_btn);
            strTimeBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                setTime(vpAdapter.getView(4), 0);
            }
            });

            endTimeBtn = vpAdapter.getView(4).findViewById(R.id.end_time_btn);
            endTimeBtn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View view) {
                    setTime(vpAdapter.getView(4), 1);
                }
            });

            submitTimeBtn = vpAdapter.getView(4).findViewById(R.id.submit_time_btn);
            submitTimeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((strTimeBtn.getText().toString().isEmpty())){
                        Toast.makeText(MainActivity.this, "Starting time not set!", Toast.LENGTH_SHORT).show();
                    }else if((endTimeBtn.getText().toString().isEmpty())){
                        Toast.makeText(MainActivity.this, ""+strTimeBtn.getText().toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        calList = (bookingCal.getSelectedDates());
                        confirmBooking();
                    }
            }});
        }

    private void confirmBooking() {
        AlertDialog.Builder dialogBuilder = new TimePickerDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View picker = inflater.inflate(R.layout.confirmation_dialog, null);
        dialogBuilder.setView(picker);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        final TextView roomName = (TextView) picker.findViewById(R.id.room_cnf);
        final TextView startTime = (TextView) picker.findViewById(R.id.start_time_cnf);
        final TextView endTime = (TextView) picker.findViewById(R.id.end_time_cnf);
        final TextView startDate = (TextView) picker.findViewById(R.id.start_date_cnf);
        final TextView endDate = (TextView) picker.findViewById(R.id.end_date_cnf);

        roomName.setText("Room: "+unidum.getResRoom().getRoomType()+" "+unidum.getResRoom().getRoomNumber());
        startTime.setText("Start Time:  "+strTimeBtn.getText());
        endTime.setText("End time:  "+endTimeBtn.getText());

        startDate.setText("Start date:  "+ calList.get(0).getMonth() +
                "/"+calList.get(0).getDay() +
                "/"+calList.get(0).getYear());
        endDate.setText("End date:  "+ calList.get(calList.size()-1).getMonth() +
                "/"+calList.get(calList.size()-1).getDay() +
                "/"+calList.get(calList.size()-1).getYear());

        final Button cancelRoom = (Button) picker.findViewById(R.id.cancel_cnf_btn);
        final Button confirmRoom = (Button) picker.findViewById(R.id.confirm_cnf_btn);

        cancelRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        confirmRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                resrvList.add(new ReservationDummy(calList, unidum.getResRoom(), unidum.getStartTime(), unidum.getEndTime()));
                Toast.makeText(MainActivity.this, "Reservation created!", Toast.LENGTH_SHORT).show();
                pager.setCurrentItem(0);
            }
        });
    }

    public void filterDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
//w        final View picker = inflater.inflate(R.layout.filterpicker, null);
//        dialogBuilder.setView(picker);
        String[] types = {"Scrum", "Conference", "Breakroom", "Boardroom", "Recreation"};
        dialogBuilder.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                switch(i){
                    case 0:
                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:

                }
            }
        });
//        final AlertDialog dialog = dialogBuilder.create();
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogBuilder.show();
    }

        @Override
        public void onBackPressed() {
            if (mLayout != null &&
                (mLayout.getPanelState() == PanelState.EXPANDED || mLayout.getPanelState() == PanelState.ANCHORED)) {
                    mLayout.setPanelState(PanelState.COLLAPSED);
            } else if (pager.getCurrentItem()>1){
                pager.setCurrentItem(pager.getCurrentItem()-1);
            }else if (pager.getCurrentItem()<1){
                pager.setCurrentItem(pager.getCurrentItem()+1);
            }else
                super.onBackPressed();
            }

        @Override
        public void onItemClick(ResourceDummy dummy) {

            if (dummy.getRoomNumber() == 0)    {
                queryList.clear();
                for (ResourceDummy d : resourceList) {
                    if (d.getRoomType().equals(dummy.getRoomType()))
                        queryList.add(d);
                }
                RVadapter2.notifyDataSetChanged();
                pager.setCurrentItem(3);
            }

            if(dummy.getRoomNumber()!=0)   {
                displayDetail(dummy);
            }
        }

    private void displayDetail(final ResourceDummy dummy) {
        AlertDialog.Builder dialogBuilder = new TimePickerDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View picker = inflater.inflate(R.layout.room_info_dialog, null);
        dialogBuilder.setView(picker);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        final Button cancelRoom = (Button) picker.findViewById(R.id.cancel_room_btn);
        final Button confirmRoom = (Button) picker.findViewById(R.id.confirm_room_btn);

        final TextView roomNum = (TextView) picker.findViewById(R.id.roomNumber_txt);
        roomNum.setText("Room #:    "+dummy.getRoomNumber());

        cancelRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        confirmRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                unidum.setResRoom(new ResourceDummy(dummy.getRoomType(), dummy.getRoomNumber()));
                pager.setCurrentItem(4);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
        public void setTime(View view, final int extraInfo){

            TimePickerDialog.Builder dialogBuilder = new TimePickerDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View picker = inflater.inflate(R.layout.timepicker, null);
            dialogBuilder.setView(picker);
            final AlertDialog dialog = dialogBuilder.create();
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            final TimePicker timePicker = picker.findViewById(R.id.timePicker);
            final Button setTime = (Button) picker.findViewById(R.id.set_time_btn);

            setTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int hour =  timePicker.getHour();
                    final int min = timePicker.getMinute();
                    if (extraInfo==0) {
                        String time = updateTime(hour, min);
                        unidum.setStartTime(time);
                        strTimeBtn.setText(time);
                    }
                    if (extraInfo==1) {
                        String time = updateTime(hour, min);
                        unidum.setEndTime(time);
                        endTimeBtn.setText(time);
                    }
                    dialog.cancel();
                }
            });
            dialog.show();
        }

    private String updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";

        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        return aTime;
        }

        public void initDummyData(){

            dateList = new ArrayList<String>();
            roomList = new ArrayList<String>();
            timeList = new ArrayList<String>();

            resrvList = new ArrayList<ReservationDummy>();
            menuList = new ArrayList<ResourceDummy>();
            resourceList = new ArrayList<ResourceDummy>();
            queryList = new ArrayList<ResourceDummy>();

            menuList.add(new ResourceDummy("Scrum",0));
            menuList.add(new ResourceDummy("Conference",0));
            menuList.add(new ResourceDummy("Breakroom",0));
            menuList.add(new ResourceDummy("Boardroom",0));
            menuList.add(new ResourceDummy("Recreation",0));

            resourceList.add(new ResourceDummy("Scrum",1));
            resourceList.add(new ResourceDummy("Scrum",2));
            resourceList.add(new ResourceDummy("Scrum",3));
            resourceList.add(new ResourceDummy("Scrum",4));
            resourceList.add(new ResourceDummy("Scrum",5));
            resourceList.add(new ResourceDummy("Scrum",6));

            resourceList.add(new ResourceDummy("Conference",1));
            resourceList.add(new ResourceDummy("Conference",2));

            resourceList.add(new ResourceDummy("Breakroom",1));
            resourceList.add(new ResourceDummy("Breakroom",2));
            resourceList.add(new ResourceDummy("Breakroom",3));

            resourceList.add(new ResourceDummy("Boardroom",1));
            resourceList.add(new ResourceDummy("Boardroom",2));
            resourceList.add(new ResourceDummy("Boardroom",3));
            resourceList.add(new ResourceDummy("Boardroom",4));

            resourceList.add(new ResourceDummy("Recreation",1));
            resourceList.add(new ResourceDummy("Recreation",2));
            resourceList.add(new ResourceDummy("Recreation",3));
            resourceList.add(new ResourceDummy("Recreation",4));
            resourceList.add(new ResourceDummy("Recreation",5));
            resourceList.add(new ResourceDummy("Recreation",6));
            resourceList.add(new ResourceDummy("Recreation",7));

        }
    }