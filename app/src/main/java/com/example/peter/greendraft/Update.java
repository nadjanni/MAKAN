package com.example.peter.greendraft;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.greendraft.data.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 6/24/2016.
 */
public class Update extends AppCompatActivity {
    public  String Hall;
    public String Day;
    public String finalJsonString = "";
    public  boolean slot_1,slot_2,slot_3,slot_4,slot_5;
    public CheckBox slot1,slot2,slot3,slot4,slot5;
    public Spinner spinner,spinner2;
    Context context=this;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatetable);
         spinner = (Spinner) findViewById(R.id.hallid);
        key=getIntent().getExtras().getString("Auth");
         spinner2 = (Spinner) findViewById(R.id.day);
         slot1 = (CheckBox) findViewById(R.id.first);
         slot2 = (CheckBox) findViewById(R.id.second);
         slot3 = (CheckBox) findViewById(R.id.third);
         slot4 = (CheckBox) findViewById(R.id.fourth);
        slot5 = (CheckBox) findViewById(R.id.fifth);

        Button update =(Button)findViewById(R.id.update);

        List<String> halls = new ArrayList<String>();
        halls.add("8208");
        halls.add("8209");
        halls.add("8309");
        halls.add("8310");
        List<String> days = new ArrayList<String>();
        days.add("Sun");
        days.add("Mon");
        days.add("Tue");
        days.add("Wed");
        days.add("Thu");


//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, halls);
//        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinneritem, halls);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, R.layout.spinneritem, days);
        Typeface hall_font = Typeface.createFromAsset(getAssets(), "Dyane Regular.ttf");
        Typeface title_font = Typeface.createFromAsset(getAssets(), "CantataOne-Regular.ttf");
        Typeface header_font = Typeface.createFromAsset(getAssets(), "SAMBAHOLLYC.otf");
        TextView title =(TextView)findViewById(R.id.updatetitle);
        title.setTypeface(header_font);
        TextView spinnerItem = (TextView)findViewById(R.id.spinneritem);

        slot1.setTypeface(title_font);
        slot2.setTypeface(title_font);
        slot3.setTypeface(title_font);
        slot4.setTypeface(title_font);
        slot5.setTypeface(title_font);
        update.setTypeface(title_font);
        spinner.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter2);
//        spinnerItem.setTypeface(hall_font);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                slot_1=slot1.isChecked();
                slot_2=slot2.isChecked();
                slot_3=slot3.isChecked();
                slot_4=slot4.isChecked();
                slot_5=slot5.isChecked();

                Hall = spinner.getSelectedItem().toString();
                Day = spinner2.getSelectedItem().toString();

                JSONObject newJson = new JSONObject();
                try {
                    newJson.accumulate("first_slot", slot_1);
                    newJson.accumulate("second_slot", slot_2);
                    newJson.accumulate("third_slot", slot_3);
                    newJson.accumulate("fourth_slot", slot_4);
                    newJson.accumulate("fifth_slot", slot_5);
                    newJson.accumulate("hall", Hall);
                    finalJsonString = newJson.toString();
                    Log.d("JSON FINAL ", finalJsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                new PostToServer().execute(finalJsonString);

                Toast.makeText(context, "New Update at: "+Day+" Day - Hall "+Hall, Toast.LENGTH_LONG).show();
            }
        });
        ///////////////////////////////////////////////////////////////////////////////


    }

    class PostToServer extends AsyncTask<String,Void,String>
        {
            HttpURLConnection connection =null;
            @Override
            protected String  doInBackground(String... params) {

                try {
                    String ur= URLs.SLOTS_URL+Day+"/";
                    URL url= new URL(ur);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("authorization", "Basic " + key);
                    BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                    bufferedWriter.write(params[0]);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    String msg=connection.getResponseMessage();
                    int responseCode=connection.getResponseCode();
                    StringBuilder sb = new StringBuilder();
                    int HttpResult = connection.getResponseCode();
                    int response=HttpResult;
                    if (HttpResult == 200) {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(connection.getInputStream(), "utf-8"));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                            JSONObject jsonKey= new JSONObject(sb.toString());
//                            key=jsonKey.getString("key");
                        }
                        br.close();
                    } else {
                    }

                }  catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }


}
