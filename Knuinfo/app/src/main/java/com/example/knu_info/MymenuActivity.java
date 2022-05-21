package com.example.knu_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.knu_info.server.KnuInfoServer;
import com.example.knu_info.utils.SharedPrefUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class MymenuActivity extends AppCompatActivity {
    TextInputEditText etmpass;
    String TAG = "MymenuActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymenu);
        Button btnmcheck = (Button) findViewById(R.id.btnmcheck);
        etmpass =  findViewById(R.id.mpassword);
        btnmcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password;
                password = String.valueOf(etmpass.getText());
                if (!password.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0]="username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = SharedPrefUtil.PreferenceManager.getString(getApplicationContext(), "userID");
                            data[1]=password;

                            PutData putData = new PutData(KnuInfoServer.server + "/knuinfo/passcheck.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("password check success")) {
                                        Log.i(TAG, "run: password check");
                                        Intent i = new Intent(MymenuActivity.this,UserupdateActivity.class);
                                        i.putExtra("userID",data[0]);
                                        i.putExtra("password",data[1]);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}