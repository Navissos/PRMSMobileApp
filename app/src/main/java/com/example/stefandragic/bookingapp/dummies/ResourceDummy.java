package com.example.stefandragic.bookingapp.dummies;

import java.util.ArrayList;

public class ResourceDummy {

    private String roomType;
    private int roomNumber;
    private ArrayList<String> featList;

    public ResourceDummy(String roomType, int roomNumber) {

        this.roomType = roomType;
        this.roomNumber = roomNumber;
        ArrayList<String> featList= new ArrayList<>();
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

}