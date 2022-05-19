package com.example.knu_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.knu_info.server.KnuInfoServer;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class UserupdateActivity extends AppCompatActivity {
    String TAG = "UserupdateActivity";
    TextInputEditText etJName, etJid, etJpass, etJEmail, etJPhone;
    Button updateBtn;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userupdate);
        mContext = this;
        etJName = findViewById(R.id.fulluname);
        etJid = findViewById(R.id.useruname);
        etJpass = findViewById(R.id.upassword);

        etJPhone = findViewById(R.id.uphone);
        etJEmail = findViewById(R.id.uemail);

        updateBtn = findViewById(R.id.btnupdate);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, email, phone;
                username = etJid.getText().toString();
                email = etJEmail.getText().toString();
                phone = etJPhone.getText().toString();
                if (!username.equals("") && !email.equals("") && !phone.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];

                            field[0] = "username";
                            field[1] = "email";
                            field[2] = "phone";

                            //Creating array for data
                            String[] data = new String[3];

                            data[0] = username;
                            data[1] = email;
                            data[2] = phone;

                            PutData putData = new PutData(KnuInfoServer.server + "/knuinfo/infoupdate.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    Log.i(TAG, "run: result"+result);
                                    if (result.equals("update Success")) {
                                        Intent i = new Intent(mContext, MainActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
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