package com.example.calllogdelete;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.provider.CallLog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogModel {

    private String id;
    private String phoneNumber;
    private String name;
    private String timeString;
    public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MMM-yyyy HH:mm");


    public LogModel(String id, String phoneNumber, String name, String timeString) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.timeString = timeString;
    }

    public LogModel() {

    }

    //--------------------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
    //--------------------------------------------------------------------------------

    @SuppressLint(value = "Range")
    public static LogModel createLogFromCursor(Cursor cursor) {
        LogModel model = new LogModel();
        String id = cursor.getString(cursor.getColumnIndex(CallLog.Calls._ID));
        String callNumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
        String callName = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
        String callDate = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
        String dateString = FORMATTER.format(new Date(Long.parseLong(callDate)));

        model.setId(id);
        model.setPhoneNumber(callNumber);
        model.setName(callName);
        model.setTimeString(dateString);
        return model;

    }

    @Override
    public String toString() {
        return "(phoneNumber= " + phoneNumber + ")" +
                "Time=" + timeString + ")" +
                "Name: " + ((name != null) ? name : " ");
    }
}
