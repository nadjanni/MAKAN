package com.example.peter.greendraft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peter.greendraft.data.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {
    public static String AUTHENTICATION_KEY;
    String username = null;
    String password =null;
    String email =null;
    public Context c = this;
    String key;
    int response;
    public String base64Encoded=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText name, pass,mail;
        Button login;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SharedPreferences preferences;
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "CantataOne-Regular.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "Dyane Regular.ttf");

//        this.getActionBar().hide();
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);
        mail = (EditText) findViewById(R.id.email);
        login = (Button) findViewById(R.id.login);
        login.setTypeface(custom_font);
        name.setTypeface(custom_font2);
        pass.setTypeface(custom_font2);
        mail.setTypeface(custom_font2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = name.getText().toString();
                password = pass.getText().toString();
                email = mail.getText().toString();
                if (password.length() == 0 || username.length() == 0) {
                    Toast.makeText(c, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    String data;
                    data=createJSON(username,password,email);
                    _(data);
                    new LoginActivity().execute(data);
                    /////////////////////FAKE LOGIN /////////////////////
                    Intent i= new Intent(c,Update.class);
//                    _("AUTH TOKEN = "+ base64Encoded);
                    i.putExtra("Auth", "ABC");
                    startActivity(i);
                    /////////////////////FAKE LOGIN /////////////////////

//                    if(response!=200)
//                        Toast.makeText(c, "Wrong Username or Password", Toast.LENGTH_LONG).show();

                    try {
                        byte[] datanew = (username+":"+password).getBytes("UTF-8");
                        base64Encoded = Base64.encodeToString(datanew, Base64.DEFAULT);
                       AUTHENTICATION_KEY=base64Encoded;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
//                    _("Encoded= "+ base64Encoded);
//                    if(response==200)
//                    {Intent i=new Intent(Login.this,EditSlots.class);
//
//                    i.putExtra("Encoded",base64Encoded);
//                    startActivity(i);}
                }


            }
        });
    }



    public class LoginActivity extends AsyncTask<String,Void,String>
    {
        HttpURLConnection connection =null;
        @Override
        protected String  doInBackground(String... params) {

            _("doInBackG,called");
            _(params[0]);

            try {
                URL url= new URL(URLs.LOGIN_URL);
                connection=(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestProperty("Content-Type", "application/json");

                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                bufferedWriter.write(params[0]);
                bufferedWriter.flush();
                bufferedWriter.close();
                String msg=connection.getResponseMessage();
                _(msg);
                int responseCode=connection.getResponseCode();
                StringBuilder sb = new StringBuilder();
                int HttpResult = connection.getResponseCode();
                response=HttpResult;
                _("Code now is "+ HttpResult);
                if (HttpResult == 200) {
                    _("HTTP IS OK");
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(), "utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                        JSONObject jsonKey= new JSONObject(sb.toString());
                        key=jsonKey.getString("key");
                    }
                    br.close();
                    _("Reply= " + sb.toString());
                    _("KEY EQUALS  " + key);

                    Intent i= new Intent(c,Update.class);
                    _("AUTH TOKEN = "+ base64Encoded);
                    i.putExtra("Auth",base64Encoded);
                    startActivity(i);

                } else {
                    _(responseCode + "");
                }

            }  catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public String createJSON(String user,String pass,String email){
        String finalJsonString="";
        JSONObject newJson= new JSONObject();
        try {
            newJson.accumulate("username",user);
            newJson.accumulate("email",email);
            newJson.accumulate("password",pass);
            finalJsonString=newJson.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        _(finalJsonString);
        return finalJsonString;}

    private void _(String s){
        Log.d("MY APPLICATION", "LOGIN" + "######" + s);
    }}
