package com.example.knu_info;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knu_info.data.LectureData;
import com.example.knu_info.data.TimetableData;
import com.example.knu_info.dialog.TimeTableAddDialog;
import com.example.knu_info.utils.SharedPrefUtil;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class TimetableActivity extends AppCompatActivity {
    //private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //EditText etID, etPassword;
    //String TAG = "TimetableActivity";
    TimeTableAddDialog activity_timetableadd;
    private ArrayAdapter yearAdapter;
    private String TAG = "TimetableActivity";
    private HashMap<Integer, TimetableData> timetablemap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        findViewById(R.id.btnTimeadd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showactivity_timetableadd();
            }
        });
        getTimetable();

    }

    public void showactivity_timetableadd() {
        activity_timetableadd = new TimeTableAddDialog(TimetableActivity.this);
        activity_timetableadd.show();
        Button noBtn = activity_timetableadd.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                activity_timetableadd.dismiss();
            }
        });
    }
    private void getTimetable(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "studentid";
                String[] data = new String[1];
                data[0] = SharedPrefUtil.PreferenceManager.getString(getApplicationContext(),"userID");
                PutData putData = new PutData("http://192.168.0.9/knuinfo/gettimetable.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        try {
                            String res = new String(result.getBytes("ISO-8859-1"), "UTF-8");
                            Log.i(TAG, "run: "+res);
                            String[] timeTimeInfos = res.split("\\$");
                            timetablemap=new HashMap<>();
                            Log.i(TAG, "run: "+timeTimeInfos.length);
                            if(!timeTimeInfos[0].isEmpty()) {
                                for (int i = 0; i < timeTimeInfos.length; i++) {
                                    Log.i(TAG, "run: " + timeTimeInfos[i]);
                                    //CLTR0210-001|m1|명저읽기와 토론|월 7A,7B,8A금 7A,7B,8A|인문대학 불어불문학과 강의실 205
                                    //CLTR0210-001
                                    // m1
                                    // 명저읽기와 토론
                                    // 월 7A,7B,8A금 7A,7B,8A
                                    // 인문대학 불어불문학과 강의실 205
                                    String[] timeTimeInfo = timeTimeInfos[i].split("\\|");
                                    TimetableData timetableData = new TimetableData();
                                    timetableData.setClassid(timeTimeInfo[0]);
                                    timetableData.setStudentid(timeTimeInfo[1]);
                                    timetableData.setClassname(timeTimeInfo[2]);
                                    timetableData.setClasstime(timeTimeInfo[3]);
                                    timetableData.setClasslocation(timeTimeInfo[4]);
                                    timetablemap.put(i, timetableData);
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //시간표 그리기
                                        if (!timetablemap.isEmpty()) {
                                            for(int i=0;i<timetablemap.size();i++){
                                                Log.i(TAG, "classTime: " +  timetablemap.get(i).getClasstime());
                                                String classTime = timetablemap.get(i).getClasstime().trim();
                                                classTime = classTime.replaceAll(" ","");
                                                //classTime.replaceAll("\\p{Z}", "");
                                                ArrayList<Integer> weekIndex = new ArrayList<>();
                                                ArrayList<String> classTimeList = new ArrayList<>();
                                                String[] week = {"월", "화", "수", "목", "금"};
                                                for(int k=0;k<week.length;k++){
                                                    if(classTime.indexOf(week[k])!=-1)
                                                        weekIndex.add(classTime.indexOf(week[k]));
                                                }
                                                if (weekIndex.size() > 0) {
                                                    for (int p = 0; p < weekIndex.size(); p++) {
                                                        try {
                                                            classTimeList.add(classTime.substring(weekIndex.get(p),weekIndex.get(p+1)));
                                                        }
                                                        catch (IndexOutOfBoundsException e){
                                                            classTimeList.add(classTime.substring(weekIndex.get(p)));
                                                        }
                                                    }
                                                }
                                                if (classTimeList.size()>0) {
                                                    for (int j=0;j<classTimeList.size();j++){
                                                        Log.i(TAG, "run: classTime List" + classTimeList.get(j));
                                                    }
                                                }

                                            }
                                            String classTime = timetablemap.get(0).getClasstime();
                                            String[] week = {"월", "화", "수", "목", "금"};

                                            //월 9A 9B 10A 금
                                            for (int i = 0; i < week.length; i++) {


                                            }

                                            //classTime = classTime.substring()
                                            Log.i(TAG, "run: ");
                                            // TODO: 2022-04-17 테스트코드
                                            TextView text = (TextView) findViewById(R.id.time_tue4);
                                            text.setText(timetablemap.get(0).getClassname());
                                            text.setTextSize(10f);
                                            text.setBackgroundColor(Color.parseColor("#00C3FF"));
                                            text.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    AlertDialog alertDialog;
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                                                    builder.setTitle(timetablemap.get(0).getClassname()).setMessage(timetablemap.get(0).getClasstime() + "\n" + timetablemap.get(0).getClasslocation());
                                                    builder.setNegativeButton("닫기", null);
                                                    alertDialog = builder.create();
                                                    alertDialog.show();
                                                }
                                            });
                                        }

                                    }
                                });
                            }
                            Log.d(TAG, "run: add complete");

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                        if (result.equals("read Success")) {

                            //성공
                            //TODO 데이터 저장, 파싱
                        } else {

                        }

                    }
                }
                //End Write and Read data with URL
            }
        });
    }



}