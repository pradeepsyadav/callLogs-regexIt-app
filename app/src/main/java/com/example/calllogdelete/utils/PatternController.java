package com.example.calllogdelete.utils;

import com.example.calllogdelete.CallLogHelper;
import com.example.calllogdelete.LogModel;
import com.example.calllogdelete.MainActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import ru.skornei.restserver.annotations.Accept;
import ru.skornei.restserver.annotations.Produces;
import ru.skornei.restserver.annotations.RestController;
import ru.skornei.restserver.annotations.methods.GET;
import ru.skornei.restserver.annotations.methods.POST;
import ru.skornei.restserver.server.dictionary.ContentType;
import ru.skornei.restserver.server.protocol.RequestInfo;

@RestController("/logapi/pattern")
public class PatternController {
    private CallLogHelper callLogHelper = MainActivity.appState.getCallLogHelper();
    ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces(ContentType.APPLICATION_JSON)
    public List<LogModel> getAllLogsWithPattern(RequestInfo request) {
        PatternRep ptrn = null;
        try {
            ptrn = mapper.<PatternRep>readValue(request.getBody(), PatternRep.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ptrn.getPattern());
        return callLogHelper.getCallLogswithNumberPattern(ptrn.getPattern());
    }

}
