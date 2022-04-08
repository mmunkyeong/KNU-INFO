package com.example.knu_info.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.knu_info.R;

import com.example.knu_info.data.LectureData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;

public class TimeTableAddDialog extends Dialog {

    private Spinner SC1;
    private Spinner SC2;
    private Spinner SC3;
    private String TAG = "TimeTableAddDialog";
    private HashMap<Integer, LectureData> lectureArray;

    public TimeTableAddDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_timetableadd);
        SC1 = findViewById(R.id.SC1Spinner);
        SC2 = findViewById(R.id.SC2Spinner);
        SC3 = findViewById(R.id.SC3Spinner);
        //db 읽기
        readLecture(context);
    }

    private void readLecture(Context mContext) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[1];
                field[0]="";
                String[] data = new String[1];
                data[0]="";
                PutData putData = new PutData("http://192.168.0.9/knuinfo/timetable.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        try {
                            String res = new String(result.getBytes("ISO-8859-1"), "UTF-8");
                            String[] lectures = res.split("\\$");
                            lectureArray = new HashMap<>();
                            for(int i=0;i<lectures.length-1;i++){
                                String[] lecture = lectures[i].split("\\|");
                                LectureData lectureData = new LectureData();
                                Log.i(TAG, "run: "+lecture[0]);
                                lectureData.setYear(lecture[1]);
                                lectureData.setSemester(lecture[2]);
                                lectureData.setGrade(lecture[3]);
                                lectureData.setDivison(lecture[4]);
                                lectureData.setDepartment(lecture[5]);
                                lectureData.setLecNum(lecture[6]);
                                lectureData.setLecName(lecture[7]);
                                lectureData.setLecGrade(lecture[8]);
                                lectureData.setTime(lecture[9]);
                                lectureData.setTraining(lecture[10]);
                                lectureData.setProfessor(lecture[11]);
                                lectureData.setLecTime(lecture[12]);
                                lectureData.setActTime(lecture[13]);
                                lectureData.setRoom(lecture[14]);
                                lectureData.setRoomNumber(lecture[15]);
                                lectureData.setPersonnel(lecture[16]);
                                lectureData.setApplicants(lecture[17]);
                                lectureData.setPackageCount(lecture[18]);
                                lectureData.setLecmethod(lecture[19]);
                                lectureData.setClassDivison(lecture[20]);
                                lectureData.setSC1(lecture[21]);
                                lectureData.setSC2(lecture[22]);
                                lectureData.setSC3(lecture[23]);

                                lectureArray.put(i,lectureData);

                            }
                            Log.d(TAG, "run: add complete");
                            init(mContext);

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                        if(result.equals("read Success")){

                           //성공
                            //TODO 데이터 저장, 파싱
                        }
                        else{
                            Toast.makeText(mContext,result,Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                //End Write and Read data with URL
            }
        });
    }

    public void init(Context mContext){

        String[] testItem = {"abc","2","3","5"};
        String[] SC1Items = new String[lectureArray.size()+1];
        String[] SC2Items = new String[lectureArray.size()+1];
        String[] SC3Items = new String[lectureArray.size()+1];
        SC1Items[0]="선택";
        SC2Items[0]="선택";
        SC3Items[0]="선택";
        if(!lectureArray.isEmpty()) {
            for (int i = 1; i <=lectureArray.size();i++){
                SC1Items[i]=lectureArray.get(i-1).getSC1();
                SC2Items[i]=lectureArray.get(i-1).getSC2();
                SC3Items[i]=lectureArray.get(i-1).getSC3();
            }
        }
        String[] SC1Item = Arrays.stream(SC1Items).distinct().toArray(String[]::new);
        String[] SC2Item = Arrays.stream(SC2Items).distinct().toArray(String[]::new);
        String[] SC3Item = Arrays.stream(SC3Items).distinct().toArray(String[]::new);


        ArrayAdapter<String> mSC1 = new ArrayAdapter<String>(mContext,R.layout.support_simple_spinner_dropdown_item,SC1Item);
        ArrayAdapter<String> mSC2 = new ArrayAdapter<String>(mContext,R.layout.support_simple_spinner_dropdown_item,SC2Item);
        ArrayAdapter<String> mSC3 = new ArrayAdapter<String>(mContext,R.layout.support_simple_spinner_dropdown_item,SC3Item);

        SC1.setAdapter(mSC1);
        SC2.setAdapter(mSC2);
        SC3.setAdapter(mSC3);


        setSpinnerListener();



    }

    private void setSpinnerListener() {

        SC1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "onItemSelected: "+i);

                if(i!=0){
                    SC2.setVisibility(View.VISIBLE);
                } else if(i==0){
                    SC2.setSelection(0);
                    SC3.setSelection(0);
                    SC2.setVisibility(View.INVISIBLE);
                    SC3.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SC2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0){
                    SC3.setVisibility(View.VISIBLE);
                }else{
                    SC3.setSelection(0);
                    SC3.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
