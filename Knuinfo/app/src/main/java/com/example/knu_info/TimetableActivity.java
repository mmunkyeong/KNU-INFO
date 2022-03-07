package com.example.knu_info;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TimetableActivity extends AppCompatActivity {
    //private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //EditText etID, etPassword;
    //String TAG = "TimetableActivity";
   Dialog dilaog01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        dilaog01 = new Dialog(TimetableActivity.this);
        dilaog01.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dilaog01.setContentView(R.layout.activity_timetableadd);

      //  Button btnTimeadd = (Button) findViewById(R.id.btnTimeadd);
        //btnTimeadd.setOnClickListener(new View.OnClickListener() {
          findViewById(R.id.btnTimeadd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showDialog01();
                    }
                });
            }
     public void showDialog01(){
        dilaog01.show();
         Button noBtn = dilaog01.findViewById(R.id.noBtn);
         noBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // 원하는 기능 구현
                 dilaog01.dismiss(); // 다이얼로그 닫기
             }
         });


     }


}
