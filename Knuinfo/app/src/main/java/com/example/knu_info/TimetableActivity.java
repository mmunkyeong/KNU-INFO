package com.example.knu_info;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knu_info.data.TimetableData;
import com.example.knu_info.dialog.TimeTableAddDialog;
import com.example.knu_info.server.KnuInfoServer;
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

    private String[] week = {"월", "화", "수", "목", "금"};
    private Float[] week_width = {40f, 60f, 80f, 100f, 120f};

    private TextView mon1, mon2, mon3, mon4, mon5, mon6, mon7, mon8, mon9, mon10, mon11, mon12, mon13, mon14, mon15, mon16, mon17, mon18;
    private TextView tue1, tue2, tue3, tue4, tue5, tue6, tue7, tue8, tue9, tue10, tue11, tue12, tue13, tue14, tue15, tue16, tue17, tue18;
    private TextView wed1, wed2, wed3, wed4, wed5, wed6, wed7, wed8, wed9, wed10, wed11, wed12, wed13, wed14, wed15, wed16, wed17, wed18;
    private TextView thu1, thu2, thu3, thu4, thu5, thu6, thu7, thu8, thu9, thu10, thu11, thu12, thu13, thu14, thu15, thu16, thu17, thu18;
    private TextView fri1, fri2, fri3, fri4, fri5, fri6, fri7, fri8, fri9, fri10, fri11, fri12, fri13, fri14, fri15, fri16, fri17, fri18;

    private String[] timeTimeInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        mon1 = findViewById(R.id.time_mon1);
        mon2 = findViewById(R.id.time_mon2);
        mon3 = findViewById(R.id.time_mon3);
        mon4 = findViewById(R.id.time_mon4);
        mon5 = findViewById(R.id.time_mon5);
        mon6 = findViewById(R.id.time_mon6);
        mon7 = findViewById(R.id.time_mon7);
        mon8 = findViewById(R.id.time_mon8);
        mon9 = findViewById(R.id.time_mon9);
        mon10 = findViewById(R.id.time_mon10);
        mon11 = findViewById(R.id.time_mon11);
        mon12 = findViewById(R.id.time_mon12);
        mon13 = findViewById(R.id.time_mon13);
        mon14 = findViewById(R.id.time_mon14);
        mon15 = findViewById(R.id.time_mon15);
        mon16 = findViewById(R.id.time_mon16);
        mon17 = findViewById(R.id.time_mon17);
        mon18 = findViewById(R.id.time_mon18);
        tue1 = findViewById(R.id.time_tue1);
        tue2 = findViewById(R.id.time_tue2);
        tue3 = findViewById(R.id.time_tue3);
        tue4 = findViewById(R.id.time_tue4);
        tue5 = findViewById(R.id.time_tue5);
        tue6 = findViewById(R.id.time_tue6);
        tue7 = findViewById(R.id.time_tue7);
        tue8 = findViewById(R.id.time_tue8);
        tue9 = findViewById(R.id.time_tue9);
        tue10 = findViewById(R.id.time_tue10);
        tue11 = findViewById(R.id.time_tue11);
        tue12 = findViewById(R.id.time_tue12);
        tue13 = findViewById(R.id.time_tue13);
        tue14 = findViewById(R.id.time_tue14);
        tue15 = findViewById(R.id.time_tue15);
        tue16 = findViewById(R.id.time_tue16);
        tue17 = findViewById(R.id.time_tue17);
        tue18 = findViewById(R.id.time_tue18);
        wed1 = findViewById(R.id.time_wed1);
        wed2 = findViewById(R.id.time_wed2);
        wed3 = findViewById(R.id.time_wed3);
        wed4 = findViewById(R.id.time_wed4);
        wed5 = findViewById(R.id.time_wed5);
        wed6 = findViewById(R.id.time_wed6);
        wed7 = findViewById(R.id.time_wed7);
        wed8 = findViewById(R.id.time_wed8);
        wed9 = findViewById(R.id.time_wed9);
        wed10 = findViewById(R.id.time_wed10);
        wed11 = findViewById(R.id.time_wed11);
        wed12 = findViewById(R.id.time_wed12);
        wed13 = findViewById(R.id.time_wed13);
        wed14 = findViewById(R.id.time_wed14);
        wed15 = findViewById(R.id.time_wed15);
        wed16 = findViewById(R.id.time_wed16);
        wed17 = findViewById(R.id.time_wed17);
        wed18 = findViewById(R.id.time_wed18);
        thu1 = findViewById(R.id.time_thu1);
        thu2 = findViewById(R.id.time_thu2);
        thu3 = findViewById(R.id.time_thu3);
        thu4 = findViewById(R.id.time_thu4);
        thu5 = findViewById(R.id.time_thu5);
        thu6 = findViewById(R.id.time_thu6);
        thu7 = findViewById(R.id.time_thu7);
        thu8 = findViewById(R.id.time_thu8);
        thu9 = findViewById(R.id.time_thu9);
        thu10 = findViewById(R.id.time_thu10);
        thu11 = findViewById(R.id.time_thu11);
        thu12 = findViewById(R.id.time_thu12);
        thu13 = findViewById(R.id.time_thu13);
        thu14 = findViewById(R.id.time_thu14);
        thu15 = findViewById(R.id.time_thu15);
        thu16 = findViewById(R.id.time_thu16);
        thu17 = findViewById(R.id.time_thu17);
        thu18 = findViewById(R.id.time_thu18);
        fri1 = findViewById(R.id.time_fri1);
        fri2 = findViewById(R.id.time_fri2);
        fri3 = findViewById(R.id.time_fri3);
        fri4 = findViewById(R.id.time_fri4);
        fri5 = findViewById(R.id.time_fri5);
        fri6 = findViewById(R.id.time_fri6);
        fri7 = findViewById(R.id.time_fri7);
        fri8 = findViewById(R.id.time_fri8);
        fri9 = findViewById(R.id.time_fri9);
        fri10 = findViewById(R.id.time_fri10);
        fri11 = findViewById(R.id.time_fri11);
        fri12 = findViewById(R.id.time_fri12);
        fri13 = findViewById(R.id.time_fri13);
        fri14 = findViewById(R.id.time_fri14);
        fri15 = findViewById(R.id.time_fri15);
        fri16 = findViewById(R.id.time_fri16);
        fri17 = findViewById(R.id.time_fri17);
        fri18 = findViewById(R.id.time_fri18);
        findViewById(R.id.btnTimeadd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showactivity_timetableadd();
            }
        });

    }

    public void showactivity_timetableadd() {
        activity_timetableadd = new TimeTableAddDialog(TimetableActivity.this);
        activity_timetableadd.show();
        Button noBtn = activity_timetableadd.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                activity_timetableadd.dismiss();
                getTimetable();
            }
        });
    }

    private void getTimetable() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "studentid";
                String[] data = new String[1];
                data[0] = SharedPrefUtil.PreferenceManager.getString(getApplicationContext(), "userID");
                PutData putData = new PutData(KnuInfoServer.server+"/knuinfo/gettimetable.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        try {
                            String res = new String(result.getBytes("ISO-8859-1"), "UTF-8");
                            Log.i(TAG, "run: " + res);
                            if(timeTimeInfos!=null){
                                timeTimeInfos = null;
                            }
                            timeTimeInfos = res.split("\\$");
                            timetablemap = new HashMap<>();
                            Log.i(TAG, "run: " + timeTimeInfos.length);
                            if (!timeTimeInfos[0].isEmpty()) {
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
                                    timetableData.setProfessor(timeTimeInfo[5]);
                                    timetablemap.put(i, timetableData);
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //시간표 그리기
                                        if (!timetablemap.isEmpty()) {
                                            for (int i = 0; i < timetablemap.size(); i++) {
                                                Log.i(TAG, "classTime: " + timetablemap.get(i).getClasstime());
                                                String classTime = timetablemap.get(i).getClasstime().trim();
                                                classTime = classTime.replaceAll(" ", "");
                                                //classTime.replaceAll("\\p{Z}", "");
                                                ArrayList<Integer> weekIndex = new ArrayList<>();
                                                ArrayList<String> classTimeList = new ArrayList<>();

                                                for (int k = 0; k < week.length; k++) {
                                                    if (classTime.indexOf(week[k]) != -1)
                                                        weekIndex.add(classTime.indexOf(week[k]));
                                                }
                                                if (weekIndex.size() > 0) {
                                                    for (int p = 0; p < weekIndex.size(); p++) {
                                                        try {
                                                            classTimeList.add(classTime.substring(weekIndex.get(p), weekIndex.get(p + 1)));
                                                        } catch (IndexOutOfBoundsException e) {
                                                            classTimeList.add(classTime.substring(weekIndex.get(p)));
                                                        }
                                                    }
                                                }
                                                if (classTimeList.size() > 0) {
                                                    int b[] = new int[3];
                                                    for (int x = 0; x < 3; x++) {
                                                        b[x] = (int) (Math.random() * 10);
                                                        for (int z = 0; z < x; z++) {
                                                            if (b[x] == b[z]) {
                                                                x--;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    int color = getResources().getColor(R.color.time1);
                                                    if (i == 0) {
                                                        color = getResources().getColor(R.color.time2);
                                                    } else if (i == 1) {
                                                        color = getResources().getColor(R.color.time3);
                                                    } else if (i == 2) {
                                                        color = getResources().getColor(R.color.time4);
                                                    } else if (i == 3) {
                                                        color = getResources().getColor(R.color.time5);
                                                    } else if (i == 4) {
                                                        color = getResources().getColor(R.color.time6);
                                                    } else if (i == 5) {
                                                        color = getResources().getColor(R.color.time7);
                                                    } else if (i == 6) {
                                                        color = getResources().getColor(R.color.time8);
                                                    } else if (i == 7) {
                                                        color = getResources().getColor(R.color.time9);
                                                    } else if (i == 8) {
                                                        color = getResources().getColor(R.color.time10);
                                                    }

                                                    for (int j = 0; j < classTimeList.size(); j++) {
                                                        Log.i(TAG, "run: classTime List" + classTimeList.get(j));
                                                        double cellCount = 0;
                                                        cellCount += classTimeList.get(j).chars().filter(c -> c == 'A').count();
                                                        cellCount += classTimeList.get(j).chars().filter(c -> c == 'B').count();
                                                        cellCount /= 2.0;
                                                        double lastTime = parseClassTime(classTimeList.get(j));
                                                        String className = timetablemap.get(i).getClassname();
                                                        String professorName = timetablemap.get(i).getProfessor();
                                                        paintTimeTable(timetablemap.get(i), classTimeList.get(j), lastTime, cellCount, color);


                                                    }
                                                }

                                            }


                                            //classTime = classTime.substring()
                                            Log.i(TAG, "run: ");
                                            // TODO: 2022-04-17 테스트코드
//                                            TextView text = (TextView) tue4);
//                                            text.setText(timetablemap.get(0.getClassname());
//                                            text.setTextSize(10f);
//                                            text.setBackgroundColor(Color.parseColor("#00C3FF"));
//                                            text.setOnClickListener(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View view) {
//                                                    AlertDialog alertDialog;
//                                                    AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
//                                                    builder.setTitle(timetablemap.get(0.getClassname().setMessage(timetablemap.get(0.getClasstime() + "\n" + timetablemap.get(0.getClasslocation());
//                                                    builder.setNegativeButton("닫기", null);
//                                                    alertDialog = builder.create();
//                                                    alertDialog.show();
//                                                }
//                                            });
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

    public double parseClassTime(String classTime) {
        double cellSize = 0;
        if (classTime.contains("1A")) {
            cellSize = 30;
        }
        if (classTime.contains("1B")) {
            cellSize = 60;
        }
        if (classTime.contains("2A")) {
            cellSize = 90;
        }
        if (classTime.contains("2B")) {
            cellSize = 120;
        }
        if (classTime.contains("3A")) {
            cellSize = 150;
        }
        if (classTime.contains("3B")) {
            cellSize = 180;
        }
        if (classTime.contains("4A")) {
            cellSize = 210;
        }
        if (classTime.contains("4B")) {
            cellSize = 240;
        }
        if (classTime.contains("5A")) {
            cellSize = 270;
        }
        if (classTime.contains("5B")) {
            cellSize = 300;
        }
        if (classTime.contains("6A")) {
            cellSize = 330;
        }
        if (classTime.contains("6B")) {
            cellSize = 360;
        }
        if (classTime.contains("7A")) {
            cellSize = 390;
        }
        if (classTime.contains("7B")) {
            cellSize = 420;
        }
        if (classTime.contains("8A")) {
            cellSize = 450;
        }
        if (classTime.contains("8B")) {
            cellSize = 480;
        }
        if (classTime.contains("9A")) {
            cellSize = 510;
        }
        if (classTime.contains("9B")) {
            cellSize = 540;
        }


        return (cellSize / 60f) + 9;
    }

    public void paintTimeTable(TimetableData timetableData, String classTimeInfo, double lastTime, double cellCount, int color) {
        if (classTimeInfo.contains("월")) {
            if (lastTime == 9) {
            } else if (lastTime == 9.5) {
                if (cellCount == 0.5) {
                    mon1.setBackgroundColor(color);
                }
                mon1.setText(timetableData.getClassname());
            } else if (lastTime == 10.0) {
                if (cellCount == 0.5) {
                    mon2.setBackgroundColor(color);
                    mon2.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon1.setBackgroundColor(color);
                    mon2.setBackgroundColor(color);
                    mon1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 10.5) {
                if (cellCount == 0.5) {
                    mon3.setBackgroundColor(color);
                    mon3.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon2.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon1.setBackgroundColor(color);
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.0) {
                if (cellCount == 0.5) {
                    mon4.setBackgroundColor(color);
                    mon4.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon3.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon2.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon1.setBackgroundColor(color);
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.5) {
                if (cellCount == 0.5) {
                    mon5.setBackgroundColor(color);
                    mon5.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon4.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon3.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon2.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon1.setBackgroundColor(color);
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.0) {
                if (cellCount == 0.5) {
                    mon6.setBackgroundColor(color);
                    mon6.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon5.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon4.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon3.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon2.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon1.setBackgroundColor(color);
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.5) {
                if (cellCount == 0.5) {
                    mon7.setBackgroundColor(color);
                    mon7.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon6.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon5.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon4.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon3.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon2.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon1.setBackgroundColor(color);
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.0) {
                if (cellCount == 0.5) {
                    mon8.setBackgroundColor(color);
                    mon8.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon7.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon6.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon5.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon4.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon3.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon2.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon1.setBackgroundColor(color);
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.5) {
                if (cellCount == 0.5) {
                    mon9.setBackgroundColor(color);
                    mon9.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon8.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon7.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon6.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon5.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon4.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon3.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon2.setBackgroundColor(color);
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon2.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.0) {
                if (cellCount == 0.5) {
                    mon10.setBackgroundColor(color);
                    mon10.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon9.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon8.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon7.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon6.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon5.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon4.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon3.setBackgroundColor(color);
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon3.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.5) {
                if (cellCount == 0.5) {
                    mon11.setBackgroundColor(color);
                    mon11.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon10.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon9.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon8.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon7.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon6.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon5.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon4.setBackgroundColor(color);
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon4.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.0) {
                if (cellCount == 0.5) {
                    mon12.setBackgroundColor(color);
                    mon12.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon11.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon10.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon9.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon8.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon7.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon6.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon5.setBackgroundColor(color);
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon5.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.5) {
                if (cellCount == 0.5) {
                    mon13.setBackgroundColor(color);
                    mon13.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon12.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon11.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon10.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon9.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon8.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon7.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon6.setBackgroundColor(color);
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon6.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.0) {
                if (cellCount == 0.5) {
                    mon14.setBackgroundColor(color);
                    mon14.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon13.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon12.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon11.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon10.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon9.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon8.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon7.setBackgroundColor(color);
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon7.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.5) {
                if (cellCount == 0.5) {
                    mon15.setBackgroundColor(color);
                    mon15.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon14.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon13.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon12.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon11.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon10.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon9.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon8.setBackgroundColor(color);
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon8.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.0) {
                if (cellCount == 0.5) {
                    mon16.setBackgroundColor(color);
                    mon16.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon15.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon14.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon13.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon12.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon11.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon10.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon9.setBackgroundColor(color);
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon9.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.5) {
                if (cellCount == 0.5) {
                    mon17.setBackgroundColor(color);
                    mon17.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon16.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon15.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon14.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon13.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon12.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon11.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon10.setBackgroundColor(color);
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon10.setText(timetableData.getClassname());
                }
            } else if (lastTime == 18.0) {
                if (cellCount == 0.5) {
                    mon18.setBackgroundColor(color);
                    mon18.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    mon17.setBackgroundColor(color);
                    mon18.setBackgroundColor(color);
                    mon17.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon18.setBackgroundColor(color);
                    mon16.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon18.setBackgroundColor(color);
                    mon15.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon18.setBackgroundColor(color);
                    mon14.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon18.setBackgroundColor(color);
                    mon13.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon18.setBackgroundColor(color);
                    mon12.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    mon11.setBackgroundColor(color);
                    mon12.setBackgroundColor(color);
                    mon13.setBackgroundColor(color);
                    mon14.setBackgroundColor(color);
                    mon15.setBackgroundColor(color);
                    mon16.setBackgroundColor(color);
                    mon17.setBackgroundColor(color);
                    mon18.setBackgroundColor(color);
                    mon11.setText(timetableData.getClassname());
                }
            }
            if (((ColorDrawable) mon1.getBackground()).getColor() != -1) {
                mon1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon2.getBackground()).getColor() != -1) {
                mon2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon3.getBackground()).getColor() != -1) {
                mon3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon4.getBackground()).getColor() != -1) {
                mon4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon5.getBackground()).getColor() != -1) {
                mon5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon6.getBackground()).getColor() != -1) {
                mon6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon7.getBackground()).getColor() != -1) {
                mon7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon8.getBackground()).getColor() != -1) {
                mon8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon9.getBackground()).getColor() != -1) {
                mon9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon10.getBackground()).getColor() != -1) {
                mon10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon11.getBackground()).getColor() != -1) {
                mon11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon12.getBackground()).getColor() != -1) {
                mon12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon13.getBackground()).getColor() != -1) {
                mon13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon14.getBackground()).getColor() != -1) {
                mon14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon15.getBackground()).getColor() != -1) {
                mon15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon16.getBackground()).getColor() != -1) {
                mon16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon17.getBackground()).getColor() != -1) {
                mon17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) mon18.getBackground()).getColor() != -1) {
                mon18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
        } else if (classTimeInfo.contains("화")) {
            if (lastTime == 9) {
            } else if (lastTime == 9.5) {
                if (cellCount == 0.5) {
                    tue1.setBackgroundColor(color);
                }
                tue1.setText(timetableData.getClassname());
            } else if (lastTime == 10.0) {
                if (cellCount == 0.5) {
                    tue2.setBackgroundColor(color);
                    tue2.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue1.setBackgroundColor(color);
                    tue2.setBackgroundColor(color);
                    tue1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 10.5) {
                if (cellCount == 0.5) {
                    tue3.setBackgroundColor(color);
                    tue3.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue2.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue1.setBackgroundColor(color);
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.0) {
                if (cellCount == 0.5) {
                    tue4.setBackgroundColor(color);
                    tue4.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue3.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue2.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue1.setBackgroundColor(color);
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.5) {
                if (cellCount == 0.5) {
                    tue5.setBackgroundColor(color);
                    tue5.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue4.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue3.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue2.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue1.setBackgroundColor(color);
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.0) {
                if (cellCount == 0.5) {
                    tue6.setBackgroundColor(color);
                    tue6.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue5.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue4.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue3.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue2.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue1.setBackgroundColor(color);
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.5) {
                if (cellCount == 0.5) {
                    tue7.setBackgroundColor(color);
                    tue7.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue6.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue5.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue4.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue3.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue2.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue1.setBackgroundColor(color);
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.0) {
                if (cellCount == 0.5) {
                    tue8.setBackgroundColor(color);
                    tue8.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue7.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue6.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue5.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue4.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue3.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue2.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue1.setBackgroundColor(color);
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.5) {
                if (cellCount == 0.5) {
                    tue9.setBackgroundColor(color);
                    tue9.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue8.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue7.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue6.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue5.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue4.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue3.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue2.setBackgroundColor(color);
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue2.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.0) {
                if (cellCount == 0.5) {
                    tue10.setBackgroundColor(color);
                    tue10.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue9.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue8.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue7.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue6.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue5.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue4.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue3.setBackgroundColor(color);
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue3.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.5) {
                if (cellCount == 0.5) {
                    tue11.setBackgroundColor(color);
                    tue11.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue10.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue9.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue8.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue7.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue6.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue5.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue4.setBackgroundColor(color);
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue4.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.0) {
                if (cellCount == 0.5) {
                    tue12.setBackgroundColor(color);
                    tue12.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue11.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue10.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue9.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue8.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue7.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue6.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue5.setBackgroundColor(color);
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue5.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.5) {
                if (cellCount == 0.5) {
                    tue13.setBackgroundColor(color);
                    tue13.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue12.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue11.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue10.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue9.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue8.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue7.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue6.setBackgroundColor(color);
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue6.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.0) {
                if (cellCount == 0.5) {
                    tue14.setBackgroundColor(color);
                    tue14.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue13.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue12.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue11.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue10.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue9.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue8.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue7.setBackgroundColor(color);
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue7.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.5) {
                if (cellCount == 0.5) {
                    tue15.setBackgroundColor(color);
                    tue15.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue14.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue13.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue12.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue11.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue10.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue9.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue8.setBackgroundColor(color);
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue8.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.0) {
                if (cellCount == 0.5) {
                    tue16.setBackgroundColor(color);
                    tue16.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue15.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue14.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue13.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue12.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue11.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue10.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue9.setBackgroundColor(color);
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue9.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.5) {
                if (cellCount == 0.5) {
                    tue17.setBackgroundColor(color);
                    tue17.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue16.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue15.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue14.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue13.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue12.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue11.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue10.setBackgroundColor(color);
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue10.setText(timetableData.getClassname());
                }
            } else if (lastTime == 18.0) {
                if (cellCount == 0.5) {
                    tue18.setBackgroundColor(color);
                    tue18.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    tue17.setBackgroundColor(color);
                    tue18.setBackgroundColor(color);
                    tue17.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue18.setBackgroundColor(color);
                    tue16.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue18.setBackgroundColor(color);
                    tue15.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue18.setBackgroundColor(color);
                    tue14.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue18.setBackgroundColor(color);
                    tue13.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue18.setBackgroundColor(color);
                    tue12.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    tue11.setBackgroundColor(color);
                    tue12.setBackgroundColor(color);
                    tue13.setBackgroundColor(color);
                    tue14.setBackgroundColor(color);
                    tue15.setBackgroundColor(color);
                    tue16.setBackgroundColor(color);
                    tue17.setBackgroundColor(color);
                    tue18.setBackgroundColor(color);
                    tue11.setText(timetableData.getClassname());
                }
            }
            if (((ColorDrawable) tue1.getBackground()).getColor() != -1) {
                tue1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue2.getBackground()).getColor() != -1) {
                tue2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue3.getBackground()).getColor() != -1) {
                tue3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue4.getBackground()).getColor() != -1) {
                tue4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue5.getBackground()).getColor() != -1) {
                tue5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue6.getBackground()).getColor() != -1) {
                tue6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue7.getBackground()).getColor() != -1) {
                tue7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue8.getBackground()).getColor() != -1) {
                tue8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue9.getBackground()).getColor() != -1) {
                tue9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue10.getBackground()).getColor() != -1) {
                tue10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue11.getBackground()).getColor() != -1) {
                tue11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue12.getBackground()).getColor() != -1) {
                tue12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue13.getBackground()).getColor() != -1) {
                tue13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue14.getBackground()).getColor() != -1) {
                tue14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue15.getBackground()).getColor() != -1) {
                tue15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue16.getBackground()).getColor() != -1) {
                tue16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue17.getBackground()).getColor() != -1) {
                tue17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) tue18.getBackground()).getColor() != -1) {
                tue18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
        } else if (classTimeInfo.contains("수")) {
            if (lastTime == 9) {
            } else if (lastTime == 9.5) {
                if (cellCount == 0.5) {
                    wed1.setBackgroundColor(color);
                }
                wed1.setText(timetableData.getClassname());
            } else if (lastTime == 10.0) {
                if (cellCount == 0.5) {
                    wed2.setBackgroundColor(color);
                    wed2.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed1.setBackgroundColor(color);
                    wed2.setBackgroundColor(color);
                    wed1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 10.5) {
                if (cellCount == 0.5) {
                    wed3.setBackgroundColor(color);
                    wed3.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed2.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed1.setBackgroundColor(color);
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.0) {
                if (cellCount == 0.5) {
                    wed4.setBackgroundColor(color);
                    wed4.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed3.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed2.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed1.setBackgroundColor(color);
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.5) {
                if (cellCount == 0.5) {
                    wed5.setBackgroundColor(color);
                    wed5.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed4.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed3.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed2.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed1.setBackgroundColor(color);
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.0) {
                if (cellCount == 0.5) {
                    wed6.setBackgroundColor(color);
                    wed6.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed5.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed4.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed3.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed2.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed1.setBackgroundColor(color);
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.5) {
                if (cellCount == 0.5) {
                    wed7.setBackgroundColor(color);
                    wed7.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed6.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed5.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed4.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed3.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed2.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed1.setBackgroundColor(color);
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.0) {
                if (cellCount == 0.5) {
                    wed8.setBackgroundColor(color);
                    wed8.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed7.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed6.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed5.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed4.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed3.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed2.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed1.setBackgroundColor(color);
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.5) {
                if (cellCount == 0.5) {
                    wed9.setBackgroundColor(color);
                    wed9.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed8.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed7.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed6.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed5.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed4.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed3.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed2.setBackgroundColor(color);
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed2.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.0) {
                if (cellCount == 0.5) {
                    wed10.setBackgroundColor(color);
                    wed10.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed9.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed8.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed7.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed6.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed5.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed4.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed3.setBackgroundColor(color);
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed3.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.5) {
                if (cellCount == 0.5) {
                    wed11.setBackgroundColor(color);
                    wed11.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed10.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed9.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed8.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed7.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed6.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed5.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed4.setBackgroundColor(color);
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed4.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.0) {
                if (cellCount == 0.5) {
                    wed12.setBackgroundColor(color);
                    wed12.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed11.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed10.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed9.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed8.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed7.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed6.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed5.setBackgroundColor(color);
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed5.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.5) {
                if (cellCount == 0.5) {
                    wed13.setBackgroundColor(color);
                    wed13.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed12.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed11.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed10.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed9.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed8.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed7.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed6.setBackgroundColor(color);
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed6.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.0) {
                if (cellCount == 0.5) {
                    wed14.setBackgroundColor(color);
                    wed14.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed13.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed12.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed11.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed10.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed9.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed8.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed7.setBackgroundColor(color);
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed7.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.5) {
                if (cellCount == 0.5) {
                    wed15.setBackgroundColor(color);
                    wed15.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed14.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed13.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed12.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed11.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed10.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed9.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed8.setBackgroundColor(color);
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed8.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.0) {
                if (cellCount == 0.5) {
                    wed16.setBackgroundColor(color);
                    wed16.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed15.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed14.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed13.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed12.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed11.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed10.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed9.setBackgroundColor(color);
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed9.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.5) {
                if (cellCount == 0.5) {
                    wed17.setBackgroundColor(color);
                    wed17.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed16.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed15.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed14.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed13.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed12.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed11.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed10.setBackgroundColor(color);
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed10.setText(timetableData.getClassname());
                }
            } else if (lastTime == 18.0) {
                if (cellCount == 0.5) {
                    wed18.setBackgroundColor(color);
                    wed18.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    wed17.setBackgroundColor(color);
                    wed18.setBackgroundColor(color);
                    wed17.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed18.setBackgroundColor(color);
                    wed16.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed18.setBackgroundColor(color);
                    wed15.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed18.setBackgroundColor(color);
                    wed14.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed18.setBackgroundColor(color);
                    wed13.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed18.setBackgroundColor(color);
                    wed12.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    wed11.setBackgroundColor(color);
                    wed12.setBackgroundColor(color);
                    wed13.setBackgroundColor(color);
                    wed14.setBackgroundColor(color);
                    wed15.setBackgroundColor(color);
                    wed16.setBackgroundColor(color);
                    wed17.setBackgroundColor(color);
                    wed18.setBackgroundColor(color);
                    wed11.setText(timetableData.getClassname());
                }
            }
            if (((ColorDrawable) wed1.getBackground()).getColor() != -1) {
                wed1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed2.getBackground()).getColor() != -1) {
                wed2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed3.getBackground()).getColor() != -1) {
                wed3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed4.getBackground()).getColor() != -1) {
                wed4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed5.getBackground()).getColor() != -1) {
                wed5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed6.getBackground()).getColor() != -1) {
                wed6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed7.getBackground()).getColor() != -1) {
                wed7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed8.getBackground()).getColor() != -1) {
                wed8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed9.getBackground()).getColor() != -1) {
                wed9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed10.getBackground()).getColor() != -1) {
                wed10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed11.getBackground()).getColor() != -1) {
                wed11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed12.getBackground()).getColor() != -1) {
                wed12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed13.getBackground()).getColor() != -1) {
                wed13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed14.getBackground()).getColor() != -1) {
                wed14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed15.getBackground()).getColor() != -1) {
                wed15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed16.getBackground()).getColor() != -1) {
                wed16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed17.getBackground()).getColor() != -1) {
                wed17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) wed18.getBackground()).getColor() != -1) {
                wed18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
        } else if (classTimeInfo.contains("목")) {
            if (lastTime == 9) {
            } else if (lastTime == 9.5) {
                if (cellCount == 0.5) {
                    thu1.setBackgroundColor(color);
                }
                thu1.setText(timetableData.getClassname());
            } else if (lastTime == 10.0) {
                if (cellCount == 0.5) {
                    thu2.setBackgroundColor(color);
                    thu2.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu1.setBackgroundColor(color);
                    thu2.setBackgroundColor(color);
                    thu1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 10.5) {
                if (cellCount == 0.5) {
                    thu3.setBackgroundColor(color);
                    thu3.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu2.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu1.setBackgroundColor(color);
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.0) {
                if (cellCount == 0.5) {
                    thu4.setBackgroundColor(color);
                    thu4.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu3.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu2.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu1.setBackgroundColor(color);
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.5) {
                if (cellCount == 0.5) {
                    thu5.setBackgroundColor(color);
                    thu5.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu4.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu3.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu2.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu1.setBackgroundColor(color);
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.0) {
                if (cellCount == 0.5) {
                    thu6.setBackgroundColor(color);
                    thu6.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu5.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu4.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu3.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu2.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu1.setBackgroundColor(color);
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.5) {
                if (cellCount == 0.5) {
                    thu7.setBackgroundColor(color);
                    thu7.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu6.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu5.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu4.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu3.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu2.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu1.setBackgroundColor(color);
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.0) {
                if (cellCount == 0.5) {
                    thu8.setBackgroundColor(color);
                    thu8.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu7.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu6.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu5.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu4.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu3.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu2.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu1.setBackgroundColor(color);
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.5) {
                if (cellCount == 0.5) {
                    thu9.setBackgroundColor(color);
                    thu9.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu8.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu7.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu6.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu5.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu4.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu3.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu2.setBackgroundColor(color);
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu2.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.0) {
                if (cellCount == 0.5) {
                    thu10.setBackgroundColor(color);
                    thu10.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu9.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu8.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu7.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu6.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu5.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu4.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu3.setBackgroundColor(color);
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu3.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.5) {
                if (cellCount == 0.5) {
                    thu11.setBackgroundColor(color);
                    thu11.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu10.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu9.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu8.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu7.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu6.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu5.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu4.setBackgroundColor(color);
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu4.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.0) {
                if (cellCount == 0.5) {
                    thu12.setBackgroundColor(color);
                    thu12.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu11.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu10.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu9.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu8.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu7.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu6.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu5.setBackgroundColor(color);
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu5.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.5) {
                if (cellCount == 0.5) {
                    thu13.setBackgroundColor(color);
                    thu13.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu12.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu11.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu10.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu9.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu8.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu7.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu6.setBackgroundColor(color);
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu6.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.0) {
                if (cellCount == 0.5) {
                    thu14.setBackgroundColor(color);
                    thu14.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu13.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu12.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu11.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu10.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu9.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu8.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu7.setBackgroundColor(color);
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu7.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.5) {
                if (cellCount == 0.5) {
                    thu15.setBackgroundColor(color);
                    thu15.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu14.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu13.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu12.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu11.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu10.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu9.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu8.setBackgroundColor(color);
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu8.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.0) {
                if (cellCount == 0.5) {
                    thu16.setBackgroundColor(color);
                    thu16.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu15.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu14.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu13.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu12.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu11.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu10.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu9.setBackgroundColor(color);
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu9.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.5) {
                if (cellCount == 0.5) {
                    thu17.setBackgroundColor(color);
                    thu17.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu16.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu15.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu14.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu13.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu12.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu11.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu10.setBackgroundColor(color);
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu10.setText(timetableData.getClassname());
                }
            } else if (lastTime == 18.0) {
                if (cellCount == 0.5) {
                    thu18.setBackgroundColor(color);
                    thu18.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    thu17.setBackgroundColor(color);
                    thu18.setBackgroundColor(color);
                    thu17.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu18.setBackgroundColor(color);
                    thu16.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu18.setBackgroundColor(color);
                    thu15.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu18.setBackgroundColor(color);
                    thu14.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu18.setBackgroundColor(color);
                    thu13.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu18.setBackgroundColor(color);
                    thu12.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    thu11.setBackgroundColor(color);
                    thu12.setBackgroundColor(color);
                    thu13.setBackgroundColor(color);
                    thu14.setBackgroundColor(color);
                    thu15.setBackgroundColor(color);
                    thu16.setBackgroundColor(color);
                    thu17.setBackgroundColor(color);
                    thu18.setBackgroundColor(color);
                    thu11.setText(timetableData.getClassname());
                }
            }
            if (((ColorDrawable) thu1.getBackground()).getColor() != -1) {
                thu1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu2.getBackground()).getColor() != -1) {
                thu2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu3.getBackground()).getColor() != -1) {
                thu3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu4.getBackground()).getColor() != -1) {
                thu4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu5.getBackground()).getColor() != -1) {
                thu5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu6.getBackground()).getColor() != -1) {
                thu6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu7.getBackground()).getColor() != -1) {
                thu7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu8.getBackground()).getColor() != -1) {
                thu8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu9.getBackground()).getColor() != -1) {
                thu9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu10.getBackground()).getColor() != -1) {
                thu10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu11.getBackground()).getColor() != -1) {
                thu11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu12.getBackground()).getColor() != -1) {
                thu12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu13.getBackground()).getColor() != -1) {
                thu13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu14.getBackground()).getColor() != -1) {
                thu14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu15.getBackground()).getColor() != -1) {
                thu15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu16.getBackground()).getColor() != -1) {
                thu16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu17.getBackground()).getColor() != -1) {
                thu17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) thu18.getBackground()).getColor() != -1) {
                thu18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
        } else if (classTimeInfo.contains("금")) {
            if (lastTime == 9) {
            } else if (lastTime == 9.5) {
                if (cellCount == 0.5) {
                    fri1.setBackgroundColor(color);
                }
                fri1.setText(timetableData.getClassname());
            } else if (lastTime == 10.0) {
                if (cellCount == 0.5) {
                    fri2.setBackgroundColor(color);
                    fri2.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri1.setBackgroundColor(color);
                    fri2.setBackgroundColor(color);
                    fri1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 10.5) {
                if (cellCount == 0.5) {
                    fri3.setBackgroundColor(color);
                    fri3.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri2.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri1.setBackgroundColor(color);
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.0) {
                if (cellCount == 0.5) {
                    fri4.setBackgroundColor(color);
                    fri4.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri3.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri2.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri1.setBackgroundColor(color);
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 11.5) {
                if (cellCount == 0.5) {
                    fri5.setBackgroundColor(color);
                    fri5.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri4.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri3.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri2.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri1.setBackgroundColor(color);
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.0) {
                if (cellCount == 0.5) {
                    fri6.setBackgroundColor(color);
                    fri6.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri5.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri4.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri3.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri2.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri1.setBackgroundColor(color);
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 12.5) {
                if (cellCount == 0.5) {
                    fri7.setBackgroundColor(color);
                    fri7.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri6.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri5.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri4.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri3.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri2.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri1.setBackgroundColor(color);
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.0) {
                if (cellCount == 0.5) {
                    fri8.setBackgroundColor(color);
                    fri8.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri7.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri6.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri5.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri4.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri3.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri2.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri1.setBackgroundColor(color);
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri1.setText(timetableData.getClassname());
                }
            } else if (lastTime == 13.5) {
                if (cellCount == 0.5) {
                    fri9.setBackgroundColor(color);
                    fri9.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri8.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri7.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri6.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri5.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri4.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri3.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri2.setBackgroundColor(color);
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri2.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.0) {
                if (cellCount == 0.5) {
                    fri10.setBackgroundColor(color);
                    fri10.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri9.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri8.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri7.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri6.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri5.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri4.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri3.setBackgroundColor(color);
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri3.setText(timetableData.getClassname());
                }
            } else if (lastTime == 14.5) {
                if (cellCount == 0.5) {
                    fri11.setBackgroundColor(color);
                    fri11.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri10.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri9.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri8.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri7.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri6.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri5.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri4.setBackgroundColor(color);
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri4.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.0) {
                if (cellCount == 0.5) {
                    fri12.setBackgroundColor(color);
                    fri12.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri11.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri10.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri9.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri8.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri7.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri6.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri5.setBackgroundColor(color);
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri5.setText(timetableData.getClassname());
                }
            } else if (lastTime == 15.5) {
                if (cellCount == 0.5) {
                    fri13.setBackgroundColor(color);
                    fri13.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri12.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri11.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri10.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri9.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri8.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri7.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri6.setBackgroundColor(color);
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri6.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.0) {
                if (cellCount == 0.5) {
                    fri14.setBackgroundColor(color);
                    fri14.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri13.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri12.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri11.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri10.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri9.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri8.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri7.setBackgroundColor(color);
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri7.setText(timetableData.getClassname());
                }
            } else if (lastTime == 16.5) {
                if (cellCount == 0.5) {
                    fri15.setBackgroundColor(color);
                    fri15.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri14.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri13.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri12.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri11.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri10.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri9.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri8.setBackgroundColor(color);
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri8.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.0) {
                if (cellCount == 0.5) {
                    fri16.setBackgroundColor(color);
                    fri16.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri15.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri14.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri13.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri12.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri11.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri10.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri9.setBackgroundColor(color);
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri9.setText(timetableData.getClassname());
                }
            } else if (lastTime == 17.5) {
                if (cellCount == 0.5) {
                    fri17.setBackgroundColor(color);
                    fri17.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri16.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri15.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri14.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri13.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri12.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri11.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri10.setBackgroundColor(color);
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri10.setText(timetableData.getClassname());
                }
            } else if (lastTime == 18.0) {
                if (cellCount == 0.5) {
                    fri18.setBackgroundColor(color);
                    fri18.setText(timetableData.getClassname());
                } else if (cellCount == 1.0) {
                    fri17.setBackgroundColor(color);
                    fri18.setBackgroundColor(color);
                    fri17.setText(timetableData.getClassname());
                } else if (cellCount == 1.5) {
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri18.setBackgroundColor(color);
                    fri16.setText(timetableData.getClassname());
                } else if (cellCount == 2.0) {
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri18.setBackgroundColor(color);
                    fri15.setText(timetableData.getClassname());
                } else if (cellCount == 2.5) {
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri18.setBackgroundColor(color);
                    fri14.setText(timetableData.getClassname());
                } else if (cellCount == 3.0) {
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri18.setBackgroundColor(color);
                    fri13.setText(timetableData.getClassname());
                } else if (cellCount == 3.5) {
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri18.setBackgroundColor(color);
                    fri12.setText(timetableData.getClassname());
                } else if (cellCount == 4.0) {
                    fri11.setBackgroundColor(color);
                    fri12.setBackgroundColor(color);
                    fri13.setBackgroundColor(color);
                    fri14.setBackgroundColor(color);
                    fri15.setBackgroundColor(color);
                    fri16.setBackgroundColor(color);
                    fri17.setBackgroundColor(color);
                    fri18.setBackgroundColor(color);
                    fri11.setText(timetableData.getClassname());
                }
            }
            if (((ColorDrawable) fri1.getBackground()).getColor() != -1) {
                fri1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri2.getBackground()).getColor() != -1) {
                fri2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri3.getBackground()).getColor() != -1) {
                fri3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri4.getBackground()).getColor() != -1) {
                fri4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri5.getBackground()).getColor() != -1) {
                fri5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri6.getBackground()).getColor() != -1) {
                fri6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri7.getBackground()).getColor() != -1) {
                fri7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri8.getBackground()).getColor() != -1) {
                fri8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri9.getBackground()).getColor() != -1) {
                fri9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri10.getBackground()).getColor() != -1) {
                fri10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri11.getBackground()).getColor() != -1) {
                fri11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri12.getBackground()).getColor() != -1) {
                fri12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri13.getBackground()).getColor() != -1) {
                fri13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri14.getBackground()).getColor() != -1) {
                fri14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri15.getBackground()).getColor() != -1) {
                fri15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri16.getBackground()).getColor() != -1) {
                fri16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri17.getBackground()).getColor() != -1) {
                fri17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
            if (((ColorDrawable) fri18.getBackground()).getColor() != -1) {
                fri18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
                        builder.setTitle(timetableData.getClassname()+" ("+timetableData.getProfessor()+")")
                                .setMessage(timetableData.getClasstime() + "\n" +timetableData.getClasslocation());
                        builder.setNegativeButton("닫기", null);
                        alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
            }
        }
    }

}