package com.example.knu_info;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knu_info.data.TimetableData;
import com.example.knu_info.server.KnuInfoServer;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class AlarmActivity extends AppCompatActivity {
    private static int ONE_MINUTE = 5626;
    String JSON_STRING;
    private ListView listview;
    ArrayList<HashMap<String,String>>nameList;
    private ProgressDialog progressDialog;
    ArrayList<TimetableData> items= new ArrayList<>();
    String TAG = "AlarmActivity";
    Switch sw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        //listview = findViewById(R.id.listView);
        nameList=new ArrayList<>();



        new AlarmHATT(getApplicationContext()).Alarm();

    }

    /*class getNames extends AsyncTask<Void,Void,Void>{
        String json_url;


        protected void onPreExecute() {
            super.onPreExecute();
            json_url = KnuInfoServer.server + "/knuinfo/gettimetable_json.php";
            progressDialog = new ProgressDialog(AlarmActivity.this);
            progressDialog.setMessage("Loading....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_STRING+"\n");
                }
                JSON_STRING = stringBuilder.toString();
                if(JSON_STRING != null){
                    try {
                        JSONArray ja = new JSONArray(JSON_STRING);

                        for(int i = 0 ; i < ja.length();i++){
                            JSONObject order = ja.getJSONObject(i);
                            String classname = order.getString("classname");
                            String classlocation = order.getString("classlocation");
                            String actTime = order.getString("actTime");

                            HashMap<String,String>nameMap = new HashMap<>();
                            nameMap.put("classname",classname);
                            nameMap.put("classlocation",classlocation);
                            nameMap.put("actTime",actTime);


                            nameList.add(nameMap);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),"Json ParsingError", Toast.LENGTH_LONG).show();
                    }
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            ListAdapter listAdapter = new SimpleAdapter(AlarmActivity.this,nameList,R.layout.item,new String[]{"classname"},new int[]{R.id.name});
            listview.setAdapter(listAdapter);
        }
    }*/

    public class AlarmHATT {
        private Context context;


        public AlarmHATT(Context context) {
            this.context = context;
        }

        public void Alarm() {
            sw=(Switch)findViewById(R.id.switch2) ;
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(AlarmActivity.this, BroadcastD.class);

                        PendingIntent sender = PendingIntent.getBroadcast(AlarmActivity.this, (int)System.currentTimeMillis()/1000, intent, 0);

                        Calendar calendar = Calendar.getInstance();
                        //알람시간 calendar에 set해주기


                        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 13, 51, 0);
                        //알람 예약
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                        Toast.makeText(getApplicationContext(),"알람 ON.",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"알람 OFF.",Toast.LENGTH_LONG).show();
                    }
                }
            });


        }

    }


}
