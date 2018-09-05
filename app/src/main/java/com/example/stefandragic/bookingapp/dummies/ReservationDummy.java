package com.example.stefandragic.bookingapp.dummies;

import android.support.v7.widget.CardView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class ReservationDummy {

    private CalendarDay startDate;
    private CalendarDay endDate;

    private ResourceDummy resRoom;

    public ReservationDummy (CalendarDay startDate, CalendarDay endDate, ResourceDummy resRoom){

        this.startDate = startDate;
        this.endDate = endDate;
        this.resRoom = resRoom;
    }

    public ReservationDummy() {
        startDate = null;
        endDate = null;
        resRoom = null;

    }

    public CalendarDay getStartDate() {
        return startDate;
    }

    public void setStartDate(CalendarDay startDate) {
        this.startDate = startDate;
    }

    public CalendarDay getEndDate() {
        return endDate;
    }

    public void setEndDate(CalendarDay endDate) {
        this.endDate = endDate;
    }

    public ResourceDummy getResRoom() {
        return resRoom;
    }

    public void setResRoom(ResourceDummy resRoom) {
        this.resRoom = resRoom;
    }

}
