package com.example.peter.greendraft.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Peter on 5/13/2016.
 */
public class GetData extends AsyncTask<Integer,Void,Integer> {


    private Context mContext;
    public String[] DaysArray={"Sun","Mon","Tue","Wed","Thu"};
    public int counter;
    public DbHelper dbHelper;
    public SQLiteDatabase database;

    public GetData (Context context,SQLiteDatabase liteDatabase,DbHelper helper){
        database=liteDatabase;
        dbHelper=helper;
        mContext = context;
    }
//    DbHelper dbHelper=new DbHelper(mContext);
//    SQLiteDatabase database=dbHelper.getWritableDatabase();

    @Override
    protected Integer doInBackground(Integer... params) {
        counter=params[0];
        _(""+params[0]);
        _("Counter "+counter);

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String replyJSONStr;
        try {
            final String BASE_URL = URLs.SLOTS_URL+DaysArray[counter]+"/";
            URL url = new URL(BASE_URL);
            _("Built URI " + BASE_URL);
            connection = (HttpURLConnection) url.openConnection();
            _("CONNECTION" + connection.toString());
            connection.setRequestMethod("GET");
            connection.connect();
            _("RESponse "+connection.getResponseMessage());
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            replyJSONStr = buffer.toString();
            _("JSON REPLYY  " + replyJSONStr);
            JSONArray jsonArray= new JSONArray(replyJSONStr);
            _(jsonArray.length()+"");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject finalObject = jsonArray.getJSONObject(i);
                String hall = finalObject.getString("hall");
                String admin = finalObject.getString("admin");
                String first = finalObject.getString("first_slot");
                String sec = finalObject.getString("second_slot");
                String third= finalObject.getString("third_slot");
                String fourth = finalObject.getString("fourth_slot");
                String fifth = finalObject.getString("fifth_slot");
                ContentValues values= new ContentValues();
                values.put(DataContract.DAY,DaysArray[counter]);
                values.put(DataContract.HALL,hall);
                values.put(DataContract.ADMIN,admin);
                values.put(DataContract.SLOT_1,first);
                values.put(DataContract.SLOT_2,sec);
                values.put(DataContract.SLOT_3,third);
                values.put(DataContract.SLOT_4,fourth);
                values.put(DataContract.SLOT_5, fifth);
                if(database!=null && !dbHelper.searchHallAdmin(hall,admin))
                database.insert(DataContract.TABLE_NAME, null, values);
                else {
                    database.execSQL("DELETE FROM " + DataContract.TABLE_NAME+ " WHERE "+DataContract.HALL+"='"+hall+"'"
                            + " AND "+DataContract.ADMIN+" =  \"" +admin + "\"" + " AND "+DataContract.DAY+" =  \"" +DaysArray[counter] + "\"");
                    database.insert(DataContract.TABLE_NAME, null, values);
                }
                _("Database Updated");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {if(connection != null)
            connection.disconnect();
            try {
                if (reader != null )
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        counter++;
        return  counter;
    }

    @Override
    protected  void onPostExecute(Integer s) {
        if(s<5){
            new GetData(mContext,database,dbHelper).execute(s);
//            counter++;
        }
//        counter++;
        _("Incremented Counter "+counter+"");
    }


    private void _(String s){
        Log.d("GREENAPP ", "FETCH " + "######" + s);
    }
}