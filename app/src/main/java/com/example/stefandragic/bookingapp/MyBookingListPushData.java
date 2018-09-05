package com.example.stefandragic.bookingapp;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MyBookingListPushData {
    public static HashMap<String, List<String>> pushData() {
        HashMap<String, List<String>> myBookingDetails = new HashMap<String, List<String>>();

        List<String> Scrum = new ArrayList<String>();
        Scrum.add("Accepted");
        Scrum.add("John Doe");
        Scrum.add("Pending");
        Scrum.add("John Doe");
        Scrum.add("John Doe");
        Scrum.add("John Doe");
        Scrum.add("Declined");
        Scrum.add("John Doe");
        Scrum.add("John Doe");

        List<String> Conference = new ArrayList<String>();
        Conference.add("Accepted");
        Conference.add("Pending");
        Conference.add("John Doe");
        Conference.add("Declined");

        List<String> Training = new ArrayList<String>();
        Training.add("Accepted");
        Training.add("John Doe");
        Training.add("Pending");
        Training.add("John Doe");
        Training.add("Declined");

        myBookingDetails.put("Scrum 1         " + "  9AM-10AM " + " \nPhoenix ", Scrum);
        myBookingDetails.put("Conference 2 " + " 11AM-12PM " + " \nMemphis ", Conference);
        myBookingDetails.put("Training 1      " + "  1PM-2PM " + " \nPhoenix ", Training);
        return myBookingDetails;
    }
}