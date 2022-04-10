package com.example.knu_info.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.knu_info.R;
import com.example.knu_info.data.LectureListItemData;

import java.util.ArrayList;

public class LectureListAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<LectureListItemData> lecureData;

    public LectureListAdapter(Context context, ArrayList<LectureListItemData> data) {
        mContext = context;
        lecureData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return lecureData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public LectureListItemData getItem(int position) {
        return lecureData.get(position);
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

        grade.setText(lecureData.get(position).getGrade());
        className.setText(lecureData.get(position).getClassName());
        professor.setText(lecureData.get(position).getProfessor());
        lecGrade.setText(lecureData.get(position).getLecGrade());
        personnel.setText(lecureData.get(position).getPersonnel());
        lecTime.setText(lecureData.get(position).getLecTime());

        return view;
    }

}
