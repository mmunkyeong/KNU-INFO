package com.example.knu_info.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.knu_info.R;

import com.example.knu_info.data.LectureData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class TimeTableAddDialog extends Dialog {

    private Spinner grade;
    private Spinner division;
    private Spinner department;
    private String TAG = "TimeTableAddDialog";
    private HashMap<Integer, LectureData> lectureArray;

    public TimeTableAddDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_timetableadd);
        grade = findViewById(R.id.gradeSpinner);
        division = findViewById(R.id.divisionSpinner);
        department = findViewById(R.id.departmentSpinner);
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
                                lectureData.setYear(lecture[0]);
                                lectureData.setSemester(lecture[1]);
                                lectureData.setGrade(lecture[2]);
                                lectureData.setDivison(lecture[3]);
                                lectureData.setDepartment(lecture[4]);
                                lectureData.setLecNum(lecture[5]);
                                lectureData.setLecName(lecture[6]);
                                lectureData.setLecGrade(lecture[7]);
                                lectureData.setTime(lecture[8]);
                                lectureData.setTraining(lecture[9]);
                                lectureData.setProfessor(lecture[10]);
                                lectureData.setLecTime(lecture[11]);
                                lectureData.setActTime(lecture[12]);
                                lectureData.setRoom(lecture[13]);
                                lectureData.setPersonnel(lecture[14]);
                                lectureData.setApplicants(lecture[15]);
                                lectureData.setApplicants(lecture[16]);
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
        String[] grades = new String[lectureArray.size()];
        String[] divisions = new String[lectureArray.size()];
        String[] departments = new String[lectureArray.size()];
        if(!lectureArray.isEmpty()) {
            for (int i = 0; i <lectureArray.size();i++){
                grades[i]=lectureArray.get(i).getGrade();
                divisions[i]=lectureArray.get(i).getDivison();
                departments[i]=lectureArray.get(i).getDepartment();
            }
        }
        ArrayAdapter<String> mGradeAdapter = new ArrayAdapter<String>(mContext,R.layout.support_simple_spinner_dropdown_item,grades);
        ArrayAdapter<String> mDivisionAdapter = new ArrayAdapter<String>(mContext,R.layout.support_simple_spinner_dropdown_item,divisions);
        ArrayAdapter<String> mDepartmentAdapter = new ArrayAdapter<String>(mContext,R.layout.support_simple_spinner_dropdown_item,departments);
        grade.setAdapter(mGradeAdapter);
        division.setAdapter(mDivisionAdapter);
        department.setAdapter(mDepartmentAdapter);

    }

}
