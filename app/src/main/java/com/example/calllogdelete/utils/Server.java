package com.example.calllogdelete.utils;

import ru.skornei.restserver.annotations.RestServer;
import ru.skornei.restserver.server.BaseRestServer;

@RestServer( port = Server.PORT,
        converter = JsonConverter.class, //Optional
        controllers = {CallLogController.class, PatternController.class} )

public class Server extends BaseRestServer {
    public static final int PORT = 8080;
}