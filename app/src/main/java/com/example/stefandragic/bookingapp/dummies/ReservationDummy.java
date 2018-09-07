package com.example.stefandragic.bookingapp.dummies;

import android.support.v7.widget.CardView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservationDummy {

    private List<CalendarDay> resDates;
    private ResourceDummy resRoom;
    private String startTime;
    private String endTime;

    public ReservationDummy (List<CalendarDay> resDates, ResourceDummy resRoom, String startTime, String endTime){

        this.resDates = resDates;
        this.resRoom = resRoom;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public ReservationDummy() {
        resDates = null;
        resRoom = null;
        startTime = null;
        endTime = null;

    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<CalendarDay> getDates() {
        return resDates;
    }

    public void setDates(List<CalendarDay> resDates) {
        this.resDates = resDates;
    }

    public ResourceDummy getResRoom() {
        return resRoom;
    }

    public void setResRoom(ResourceDummy resRoom) {
        this.resRoom = resRoom;
    }

}
