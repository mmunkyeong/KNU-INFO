package com.example.knu_info;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.knu_info.dialog.TimeTableAddDialog;

import org.w3c.dom.Text;

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