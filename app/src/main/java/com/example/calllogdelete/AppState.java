package com.example.calllogdelete;

import android.content.ContentResolver;
import android.content.Context;

public class AppState {

    private ContentResolver globalContentResolver;
    private CallLogHelper callLogHelper;
    private Context context;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CallLogHelper getCallLogHelper() {
        return callLogHelper;
    }

    public void setCallLogHelper(CallLogHelper callLogHelper) {
        this.callLogHelper = callLogHelper;
    }

    public ContentResolver getGlobalContentResolver() {
        return globalContentResolver;
    }

    public void setGlobalContentResolver(ContentResolver globalContentResolver) {
        this.globalContentResolver = globalContentResolver;
    }
}
