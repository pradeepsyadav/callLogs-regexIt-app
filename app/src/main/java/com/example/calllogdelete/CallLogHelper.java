package com.example.calllogdelete;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CallLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CallLogHelper {

    public static final String SORT_ORDER = CallLog.Calls.DATE + " DESC";
    private final ContentResolver conResolver;
    private static final String[] PROJECTION = {CallLog.Calls._ID, CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME, CallLog.Calls.DATE};
    private Optional<Map<String, LogModel>> LOG_CACHE = Optional.empty();



    public CallLogHelper(ContentResolver cr) {
        this.conResolver = cr;
    }


    public List<LogModel> getCallLogswithNumberPattern(String pattern) {
        return this.fetchAllLogs().stream().filter(log -> log.getPhoneNumber().matches(pattern)).collect(Collectors.toList());
    }

//    private void deleteLogByID(String id) {
//        LogModel log = getLogByID(id);
//        System.out.printf("Deleting data of number : %s on this date: %s\n", log.getPhoneNumber(), log.getTimeString());
//        int delRows = conResolver.delete(CallLog.Calls.CONTENT_URI, whereIdEquals(id), null);
//        System.out.println("Number of rows deleted: " + delRows);
//
//    }

//    private int deleteThisLogs(List<LogModel> logsToDelete) {
//        logsToDelete.forEach( log -> deleteLogByID(log.getId()) );
//        return 1;
//    }

//    private LogModel getLogByID(String queriedId) {
//        Optional<LogModel> log = ifPresentInCache(queriedId).orElse();
//        refreshLogs();
//        getLogByID(queriedId);
//    }

    private String whereIdEquals(String id) {
        return CallLog.Calls._ID + " = " + id ;
    }

    private void refreshCache() {
        Map<String, LogModel> idToLogMap = new HashMap<>();
        fetchAllLogs().forEach(log -> idToLogMap.put(log.getId(), log));
        LOG_CACHE = Optional.of(idToLogMap);
    }

    public List<LogModel> fetchAllLogs() {
        List<LogModel> logs = new ArrayList<>();
        Cursor cursor = conResolver.query(CallLog.Calls.CONTENT_URI, PROJECTION, null, null, SORT_ORDER);
        while(cursor.moveToNext()) {
            logs.add(LogModel.createLogFromCursor(cursor));
        }
        cursor.close();
        return logs;
    }


    private Optional<LogModel> ifPresentInCache(String queriedId) {
        if(LOG_CACHE.isPresent() && LOG_CACHE.get().containsKey(queriedId)) {
            return Optional.of(LOG_CACHE.get().get(queriedId));
        }
        return Optional.empty();
    }

}
