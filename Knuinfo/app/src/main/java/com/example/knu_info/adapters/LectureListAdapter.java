package com.example.knu_info.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knu_info.LoginActivity;
import com.example.knu_info.R;
import com.example.knu_info.data.LectureListItemData;
import com.example.knu_info.server.KnuInfoServer;
import com.example.knu_info.utils.SharedPrefUtil;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

public class LectureListAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<LectureListItemData> lectureData;

    private String TAG = "LectureListAdapter";

    public LectureListAdapter(Context context, ArrayList<LectureListItemData> data) {
        mContext = context;
        lectureData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return lectureData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public LectureListItemData getItem(int position) {
        return lectureData.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.lecture_list_item, null);

        TextView grade = (TextView) view.findViewById(R.id.grade);
        TextView className = (TextView) view.findViewById(R.id.className);
        TextView professor = (TextView) view.findViewById(R.id.professor);
        TextView lecGrade = (TextView) view.findViewById(R.id.lecGrade);
        TextView personnel = (TextView) view.findViewById(R.id.personnel);
        TextView lecTime = (TextView) view.findViewById(R.id.lecTime);

        grade.setText(lectureData.get(position).getGrade() + "학년");
        className.setText(lectureData.get(position).getClassName());
        professor.setText(lectureData.get(position).getProfessor() + " 교수님");
        lecGrade.setText(lectureData.get(position).getLecGrade() + "학점");
        personnel.setText("제한 인원: " + lectureData.get(position).getPersonnel() + "명");
        lecTime.setText(lectureData.get(position).getLecTime());
        Button addBtn = (Button) view.findViewById(R.id.addbtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestAdd(lectureData.get(position));

            }
        });
        return view;
    }

    private void requestAdd(LectureListItemData lectureListItemData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
//                classid	studentid	classname	classtime	classlocation

                String[] field = new String[8];
                field[0] = "classid";
                field[1] = "studentid";
                field[2] = "classname";
                field[3] = "classtime";
                field[4] = "classlocation";
                field[5] = "professor";
                field[6] = "actTime";
                field[7] = "diffCheck";
                //Creating array for data
                String[] data = new String[8];
                data[0] = lectureListItemData.getLecId();
                data[1] = SharedPrefUtil.PreferenceManager.getString(mContext, "userID");
                data[2] = lectureListItemData.getClassName();
                data[3] = lectureListItemData.getLecTime();
                data[4] = lectureListItemData.getLecLocation();
                data[5] = lectureListItemData.getProfessor();
                data[6] = lectureListItemData.getActTime();
                String actTime = lectureListItemData.getActTime();
                actTime = actTime.replaceAll(" ", "");
                actTime = actTime.replaceAll("월", "\n10#");
                actTime = actTime.replaceAll("화", "\n20#");
                actTime = actTime.replaceAll("수", "\n30#");
                actTime = actTime.replaceAll("목", "\n40#");
                actTime = actTime.replaceAll("금", "\n50#");
                actTime = actTime.replaceAll(":", "");
                actTime = actTime.replaceAll("~", "|");
                data[7] = actTime.trim();

                PutData putData = new PutData(KnuInfoServer.server + "/knuinfo/addtimetable.php", "POST", field, data);
                if (putData.startPut()) {
                    Log.i(TAG, "run: put Start");
                    if (putData.onComplete()) {
                        Log.i(TAG, "run: put onComplete");

                        String result = putData.getResult();

                        if (result.equals("add timetable Success")) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext, "강의가 추가되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Log.i(TAG, "run: timetable put Success");

                        } else if (result.equals("add timetable Failed_duplicate")) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext, "이미 추가된 강의와 시간이 겹칩니다.", Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Log.i(TAG, "run: timetable put fail" + result);

                        }

                    } else {
                        Log.i(TAG, "run: Time out?");
                    }
                }
            }
        }).start();
    }

}
