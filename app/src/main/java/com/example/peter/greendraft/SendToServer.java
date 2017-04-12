package com.example.peter.greendraft;

import android.os.AsyncTask;
import android.util.Log;

import com.example.peter.greendraft.data.URLs;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Peter on 5/8/2016.
 */
public class SendToServer extends AsyncTask<Integer,Void,String> {

    public String data;
        HttpURLConnection connection =null;
        @Override
        protected String  doInBackground(Integer... params) {

            _("doInBackG,called");
            _(params[0]+"");

            try {
                URL url= new URL(URLs.SLOTS_URL+params[0]);
                _(url+" URL");
                connection=(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("PUT");
//                connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("authorization", "Basic " + Login.AUTHENTICATION_KEY);
//                connection.setRequestProperty("Accept", "application/json");
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                String msg=connection.getResponseMessage();
                _(msg);
                int responseCode=connection.getResponseCode();
                StringBuilder sb = new StringBuilder();
                int HttpResult = connection.getResponseCode();
                _("Code now is "+ HttpResult);
            }  catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    public void setData(String s){data=s;}
        private void _(String s){
            Log.d("MY APPLICATION", "CALL SERVER " + "######" + s);
        }

    }






