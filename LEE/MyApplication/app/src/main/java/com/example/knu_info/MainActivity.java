package com.example.knu_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button btnchat = (Button)findViewById(R.id.btnchat);

            btnchat.setOnClickListener((view)->{
                Intent in = new Intent(MainActivity.this,ChatActivity.class) ;
                startActivity(in);
            });
        }

}
