package com.example.knu_info.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.knu_info.R;

import com.example.knu_info.adapters.LectureListAdapter;
import com.example.knu_info.data.LectureData;
import com.example.knu_info.data.LectureListItemData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TimeTableAddDialog extends Dialog {
    private Spinner Year;
    private Spinner SC1;
    private Spinner SC2;
    private Spinner SC3;
    private String TAG = "TimeTableAddDialog";
    ArrayList<String> YearItems;
    ArrayList<String> SC1Items;
    ArrayList<String> SC2Items;
    ArrayList<String> SC3Items;
    String[] YearItem;
    String[] SC1Item;
    String[] SC2Item;
    String[] SC3Item;
    ArrayAdapter<String> mYear;
    ArrayAdapter<String> mSC1;
    ArrayAdapter<String> mSC2;
    ArrayAdapter<String> mSC3;
    private int prevYearPostion = 0;
    private int prevSC1Postion = 0;
    private int prevSC2Postion = 0;
    private int prevSC3Postion = 0;
    Button addbtn = (Button) findViewById(R.id.addbtn);
    ArrayList<LectureListItemData> lectureListItemDatas;
    private HashMap<Integer, LectureData> lectureArray;

    public TimeTableAddDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_timetableadd);
        findViewById(R.id.noBtn).setForegroundGravity(Gravity.CENTER);
        Year = findViewById(R.id.YearSpinner);
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
                field[0] = "";
                String[] data = new String[1];
                data[0] = "";
                PutData putData = new PutData("http://192.168.0.9/knuinfo/timetable.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        try {
                            String res = new String(result.getBytes("ISO-8859-1"), "UTF-8");
                            String[] lectures = res.split("\\$");
                            lectureArray = new HashMap<>();
                            for (int i = 0; i < lectures.length - 1; i++) {
                                String[] lecture = lectures[i].split("\\|");
                                LectureData lectureData = new LectureData();
                                Log.i(TAG, "run: " + lecture[0]);
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
                                lectureData.setSC1(lecture[21].trim());
                                lectureData.setSC2(lecture[22]);
                                lectureData.setSC3(lecture[23]);

                                lectureArray.put(i, lectureData);

                            }
                            Log.d(TAG, "run: add complete");
                            init(mContext, "", "", "", "");

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

    public void init(Context mContext, String f0, String f1, String f2, String f3) {
        Log.i(TAG, "init: call init");
        if (YearItems != null) {
            YearItems.clear();
        }
        if (SC1Items != null) {
            SC1Items.clear();
        }
        if (SC2Items != null) {
            SC2Items.clear();
        }
        if (SC3Items != null) {
            SC3Items.clear();
        }

        YearItems = new ArrayList<String>();
        SC1Items = new ArrayList<String>();
        SC2Items = new ArrayList<String>();
        SC3Items = new ArrayList<String>();

        YearItems.add("선택");
        SC1Items.add("선택");
        SC2Items.add("선택");
        SC3Items.add("선택");

        if (f0.equals("") && f1.equals("") && f2.equals("") && f3.equals("")) {
            if (!lectureArray.isEmpty()) {
                for (int i = 1; i <= lectureArray.size(); i++) {
                    YearItems.add(lectureArray.get(i - 1).getYear());
                    SC1Items.add(lectureArray.get(i - 1).getSC1());
                    SC2Items.add(lectureArray.get(i - 1).getSC2());
                    SC3Items.add(lectureArray.get(i - 1).getSC3());
                }
            }
        } else if (f0.equals("2022") && f1.equals("") && f2.equals("") && f3.equals("")) {
            if (!lectureArray.isEmpty()) {
                for (int i = 1; i <= lectureArray.size(); i++) {
                    YearItems.add(lectureArray.get(i - 1).getYear());
                    if (lectureArray.get(i - 1).getYear().contains(f0)) {
                        SC1Items.add(lectureArray.get(i - 1).getSC1());
                        SC2Items.add(lectureArray.get(i - 1).getSC2());
                        SC3Items.add(lectureArray.get(i - 1).getSC3());
                    }
                }
            }
        } else if (!f0.equals("") && !f1.equals("") && f2.equals("") && f3.equals("")) {
            if (!lectureArray.isEmpty()) {
                for (int i = 1; i <= lectureArray.size(); i++) {
                    YearItems.add(lectureArray.get(i - 1).getYear());
                    if (lectureArray.get(i - 1).getYear().contains(f0)) {
                        SC1Items.add(lectureArray.get(i - 1).getSC1());
                        if (lectureArray.get(i - 1).getSC1().trim().contains(f1)) {
                            SC2Items.add(lectureArray.get(i - 1).getSC2());
                            SC3Items.add(lectureArray.get(i - 1).getSC3());
                        }
                    }
                }
            }
        } else if (!f0.equals("") && !f1.equals("") && !f2.equals("") && f3.equals("")) {
            if (!lectureArray.isEmpty()) {
                for (int i = 1; i <= lectureArray.size(); i++) {
                    YearItems.add(lectureArray.get(i - 1).getYear());
                    SC1Items.add(lectureArray.get(i - 1).getSC1());
                    if (lectureArray.get(i - 1).getSC1().trim().contains(f1)) {
                        SC2Items.add(lectureArray.get(i - 1).getSC2());
                        if (lectureArray.get(i - 1).getSC2().trim().contains(f2)) {
                            Log.i(TAG, "init: "+lectureArray.get(i-1).getSC3());
                            SC3Items.add(lectureArray.get(i - 1).getSC3());
                        }
                    }
                }
            }
        }

        if (YearItem != null) {
            YearItem = null;
        }
        if (SC1Item != null) {
            SC1Item = null;
        }
        if (SC2Item != null) {
            SC2Item = null;
        }
        if (SC3Item != null) {
            SC3Item = null;
        }

        ArrayList<String> YearresultList = new ArrayList<String>();
        for (int i = 0; i < YearItems.size(); i++) {
            if (!YearresultList.contains(YearItems.get(i))) {
                YearresultList.add(YearItems.get(i));
            }
        }

        ArrayList<String> SC1resultList = new ArrayList<String>();
        for (int i = 0; i < SC1Items.size(); i++) {
            if (!SC1resultList.contains(SC1Items.get(i))) {
                SC1resultList.add(SC1Items.get(i));
            }
        }
        ArrayList<String> SC2resultList = new ArrayList<String>();
        for (int i = 0; i < SC2Items.size(); i++) {
            if (!SC2resultList.contains(SC2Items.get(i))) {
                SC2resultList.add(SC2Items.get(i));
            }
        }
        ArrayList<String> SC3resultList = new ArrayList<String>();
        for (int i = 0; i < SC3Items.size(); i++) {
            if (!SC3resultList.contains(SC3Items.get(i))) {
                SC3resultList.add(SC3Items.get(i));
            }
        }

//
//        SC1Item = Arrays.stream(SC1Items).distinct().toArray(String[]::new);
//        SC2Item = Arrays.stream(SC2Items).distinct().toArray(String[]::new);
//        SC3Item = Arrays.stream(SC3Items).distinct().toArray(String[]::new);
        if (mYear != null) {
            mYear = null;
        }
        if (mSC1 != null) {
            mSC1 = null;
        }
        if (mSC2 != null) {
            mSC2 = null;
        }
        if (mSC3 != null) {
            mSC3 = null;
        }

        mYear = new ArrayAdapter<String>(mContext, R.layout.support_simple_spinner_dropdown_item, YearresultList);
        mSC1 = new ArrayAdapter<String>(mContext, R.layout.support_simple_spinner_dropdown_item, SC1resultList);
        mSC2 = new ArrayAdapter<String>(mContext, R.layout.support_simple_spinner_dropdown_item, SC2resultList);
        mSC3 = new ArrayAdapter<String>(mContext, R.layout.support_simple_spinner_dropdown_item, SC3resultList);
        if (Year.getAdapter() != null) {
            Year.setAdapter(null);
        }
        if (SC1.getAdapter() != null) {
            SC1.setAdapter(null);
        }
        if (SC2.getAdapter() != null) {
            SC2.setAdapter(null);
        }
        if (SC3.getAdapter() != null) {
            SC3.setAdapter(null);
        }

        Year.setAdapter(mYear);
        SC1.setAdapter(mSC1);
        SC2.setAdapter(mSC2);
        SC3.setAdapter(mSC3);

        if (f0.equals("") && f1.equals("") && f2.equals("") && f3.equals(""))
            setSpinnerListener();
        if (prevYearPostion != 0) {
            Year.setSelection(prevYearPostion);
        }
        if (prevSC1Postion != 0) {
            SC1.setSelection(prevSC1Postion);
        }
        if (prevSC2Postion != 0) {
            SC2.setSelection(prevSC2Postion);
        }

    }


    private void setSpinnerListener() {
        Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                boolean sameSelected = i == prevYearPostion;
                prevYearPostion = i;
                if (!sameSelected) {
                    if (i != 0) {
                        if (i == 1) {
                            init(getContext(), "2022", "", "", "");
                        }
                        SC1.setVisibility(View.VISIBLE);
                    } else if (i == 0) {
                        SC1.setSelection(0);
                        SC2.setSelection(0);
                        SC3.setSelection(0);
                        SC1.setVisibility(View.INVISIBLE);
                        SC2.setVisibility(View.INVISIBLE);
                        SC3.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SC1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                boolean sameSelected = i == prevSC1Postion;
                prevSC1Postion = i;
                if (!sameSelected) {
                    if (i != 0) {
                        if (i == 1) {
                            init(getContext(), "2022", "교양", "", "");
                        } else if (i == 2) {
                            init(getContext(), "2022", "전공", "", "");
                        }
                        SC2.setSelection(0);
                        SC2.setVisibility(View.VISIBLE);
                    } else if (i == 0) {
                        SC2.setSelection(0);
                        SC3.setSelection(0);
                        SC2.setVisibility(View.INVISIBLE);
                        SC3.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SC2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                boolean sameSelected = i == prevSC2Postion;
                prevSC2Postion = i;
                if (!sameSelected) {
                    if (i != 0) {
                        if (prevSC1Postion == 1) {
                            //교양
                            switch (i) {
                                case 1:
                                    //첨성인 기초
                                    init(getContext(), "2022", "교양", "첨성인기초", "");
                                    break;
                                case 2:
                                    //첨성인 핵심
                                    init(getContext(), "2022", "교양", "첨성인핵심", "");
                                    break;
                                case 3:
                                    //첨성인 소양
                                    init(getContext(), "2022", "교양", "첨성인소양", "");
                                    break;
                            }
                        } else if (prevSC1Postion == 2) {
                            //전공
                            switch (i) {
                                case 1:
                                    //생환대
                                    init(getContext(), "2022", "전공", "생태환경대학", "");
                                    break;
                                case 2:
                                    //과기대
                                    init(getContext(), "2022", "전공", "과학기술대학", "");

                                    break;
                            }
                        }
                        SC3.setSelection(0);
                        SC3.setVisibility(View.VISIBLE);

                    } else if (i == 0) {

                        SC3.setSelection(0);
                        SC3.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SC3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                boolean sameSelected = pos == prevSC3Postion;
                prevSC3Postion = pos;
                if (!sameSelected) {
                    if (pos != 0) {
                        lectureListItemDatas = new ArrayList<>();
                        for (int i = 0; i < lectureArray.size(); i++) {
                            if (lectureArray.get(i).getSC3().equals(SC3.getSelectedItem().toString())) {
                                Log.i(TAG, "onItemSelected: " + i);
                                lectureListItemDatas.add(new LectureListItemData(lectureArray.get(i).getGrade(), lectureArray.get(i).getLecName(),
                                        lectureArray.get(i).getProfessor(), lectureArray.get(i).getLecGrade(),
                                        lectureArray.get(i).getPersonnel(), lectureArray.get(i).getLecTime()));
                                // Log.i(TAG, "onItemSelected: !!"+ lectureArray.get(i).getProfessor());

                                Log.i(TAG, "onItemSelected: " + lectureListItemDatas.size());
                            }
                        }
                        ListView LectureListView = (ListView) findViewById(R.id.LectureListView);
                        LectureListAdapter lectureListAdapter = new LectureListAdapter(getContext(), lectureListItemDatas);
                        LectureListView.setAdapter(lectureListAdapter);

                        LectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                            }
                        });
                    } else if (pos == 0) {

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}