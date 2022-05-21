package com.example.knu_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.knu_info.utils.SharedPrefUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    Button btnchat;
    Button btntime;
    Button btnlogout;
    Button btnalarm;
    Button btnmymenu;



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);
            btnchat=(Button)findViewById(R.id.btnchat);
            btntime=(Button)findViewById(R.id.btntime);
            btnlogout =(Button)findViewById(R.id.btnlogout);
            btnalarm=(Button)findViewById(R.id.btnalarm);
            btnmymenu=(Button)findViewById(R.id.btnmymenu);
            String stId=getIntent().getStringExtra("id");

            btnlogout.setOnClickListener((view)->{
                SharedPrefUtil.PreferenceManager.clear(getApplicationContext());
                Intent in = new Intent(MainActivity.this,LoginActivity.class) ;
                //in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                Toast.makeText(getApplicationContext(),"로그아웃 되었습니다.",Toast.LENGTH_LONG).show();
                startActivity(in);


            });

            btnchat.setOnClickListener((view)->{
                Intent in = new Intent(MainActivity.this,ChatActivity.class) ;
                in.putExtra("id",stId);

                startActivity(in);
            });
            btntime.setOnClickListener((view)->{
                Intent in = new Intent(MainActivity.this,TimetableActivity.class) ;
                in.putExtra("id",stId);

                startActivity(in);
            });
            btnalarm.setOnClickListener((view)->{
                Intent in = new Intent(MainActivity.this,AlarmActivity.class) ;
                in.putExtra("id",stId);

                startActivity(in);
            });
            btnmymenu.setOnClickListener((view)->{
                Intent in = new Intent(MainActivity.this,MymenuActivity.class) ;
                in.putExtra("id",stId);

                startActivity(in);
            });

        }
}
