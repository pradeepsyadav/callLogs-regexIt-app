package com.example.calllogdelete;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.calllogdelete.utils.Server;

import java.io.IOException;
import java.util.List;

import ru.skornei.restserver.RestServerManager;


public class MainActivity extends AppCompatActivity {

    Button startButton, stopButton;
    Server mainServer = new Server();
    TextView urlDisplayer;
    final String  SERVER_STOPPED = "Server not started yet please start the server";
    String SERVER_STARTED;
    private static final String ERROR_MESSAGE = "Some Error happened";
    String currentLocalIpAddress;
    public static AppState appState = new AppState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestServerManager.initialize(this.getApplication());
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        currentLocalIpAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        SERVER_STARTED = "Started at: "+ currentLocalIpAddress +":" + Server.PORT + "/logapi";

        appState.setGlobalContentResolver(this.getContentResolver());
        appState.setCallLogHelper(new CallLogHelper(this.getContentResolver()));
        appState.setContext(this.getApplicationContext());

        final View.OnClickListener buttonClickListeners = button -> {
            switch(button.getId()) {
                case R.id.startServer:
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    startTheServer();
                    break;
                case R.id.stopServer:
                    stopButton.setEnabled(false);
                    startButton.setEnabled(true);
                    stopTheServer();
                    break;
            }
        };

        startButton = findViewById(R.id.startServer);
        stopButton = findViewById(R.id.stopServer);
        urlDisplayer = findViewById(R.id.serverUrl);
        urlDisplayer.setText(SERVER_STOPPED);

        startButton.setOnClickListener(buttonClickListeners);
        stopButton.setOnClickListener(buttonClickListeners);
    }

    private void startTheServer() {
        try {
            mainServer.start();
            urlDisplayer.setText(SERVER_STARTED);
            Log.i("app", "Server started succesfully");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("app", "Error", e);
            urlDisplayer.setText(ERROR_MESSAGE);
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }

    }

    private void stopTheServer() {
        urlDisplayer.setText(SERVER_STOPPED);
        mainServer.stop();
        Log.i("app", "Server stopped succesfully");
    }

}