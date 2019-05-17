package com.example.u6743886.comp2100_lab9;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connector extends AsyncTask<String, String, String> {
    Activity activity;
    TextView text;

    public Connector(Activity mainActivity, int viewId) {
        this.activity = mainActivity;
        this.text = activity.findViewById(viewId);
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL("http://partch.anu.edu.au/~u6743886/"); // TODO: Replace with your URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 200) {
                // 200 OK is the response normally used for successful requests in HTTP

                InputStream response = connection.getInputStream();

                // Parse data received
                String res = "";
                int data;

                while ((data = response.read()) != -1) {
                    res += (char) data;
                }

                // Close InputStream
                response.close();

                return "Received: " + res;
            } else {
                return "Connection Failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception Occurred";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        // Update TextView
        text.setText(s);
    }
}