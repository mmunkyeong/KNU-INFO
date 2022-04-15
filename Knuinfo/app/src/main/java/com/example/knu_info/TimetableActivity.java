package com.example.knu_info;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knu_info.dialog.TimeTableAddDialog;

public class TimetableActivity extends AppCompatActivity {
    //private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //EditText etID, etPassword;
    //String TAG = "TimetableActivity";
    TimeTableAddDialog activity_timetableadd;
    private ArrayAdapter yearAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        activity_timetableadd = new TimeTableAddDialog(TimetableActivity.this);


        findViewById(R.id.btnTimeadd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showactivity_timetableadd();
            }
        });
    }

    public void showactivity_timetableadd() {
        activity_timetableadd.show();
        Button noBtn = activity_timetableadd.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                activity_timetableadd.dismiss();
            }
        });


    }
}