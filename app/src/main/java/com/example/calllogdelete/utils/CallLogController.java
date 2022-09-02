package com.example.calllogdelete.utils;

import android.content.ContentResolver;
import android.content.Context;

import com.example.calllogdelete.CallLogHelper;
import com.example.calllogdelete.LogModel;
import com.example.calllogdelete.MainActivity;

import java.util.List;

import ru.skornei.restserver.annotations.Accept;
import ru.skornei.restserver.annotations.Produces;
import ru.skornei.restserver.annotations.RestController;
import ru.skornei.restserver.annotations.methods.GET;
import ru.skornei.restserver.annotations.methods.POST;
import ru.skornei.restserver.server.dictionary.ContentType;
import ru.skornei.restserver.server.protocol.RequestInfo;
import ru.skornei.restserver.server.protocol.ResponseInfo;

@RestController("/logapi")
public class CallLogController {
    private CallLogHelper callLogHelper = MainActivity.appState.getCallLogHelper();

    @GET
    @Produces(ContentType.APPLICATION_JSON)
    public List<LogModel> getAllLogs() {
        return callLogHelper.fetchAllLogs();
    }

    @POST
    @Produces(ContentType.APPLICATION_JSON)
    @Accept(ContentType.APPLICATION_JSON)
    public void test(Context context, RequestInfo request, ResponseInfo response, LogModel log) {
//        return callLogHelper.getCallLogswithNumberPattern(request.toString());
        System.out.println(request.getParameters());
        System.out.println(log.toString());
    }

//    @GET
//    @Produces(ContentType.APPLICATION_JSON)
//    public LogModel getLogById(RequestInfo request) {
//        return
//    }
}
