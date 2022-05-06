package com.example.knu_info;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knu_info.server.KnuInfoServer;
import com.example.knu_info.utils.SharedPrefUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginActivity extends AppCompatActivity {


    TextInputEditText etID, etPassword;
    String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String saveUserID = SharedPrefUtil.PreferenceManager.getString(this,"userID");
        if(!saveUserID.isEmpty()){
            // TODO: 2022-04-19 회원 정보 일치한지 유효성 체크 필요
            // TODO: 2022-05-06  서버 통신 에러인 경우 예외 처리 필요 
            Login(saveUserID);
            return;
        }

        Button btnJoin = (Button) findViewById(R.id.btnJoin);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        etID =  findViewById(R.id.etLid);
        etPassword =  findViewById(R.id.etLPass);
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


                //회원가입 버튼 눌렀을 때
        btnJoin.setOnClickListener((view) ->

        {
            Intent in = new Intent(LoginActivity.this, JoinActivity.class);
            startActivity(in);
        });
        //로그인 버튼 눌렀을 때
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,password;

                username = String.valueOf(etID.getText());
                password = String.valueOf(etPassword.getText());

                if(!username.equals("") && !password.equals("") ) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];

                            field[0] = "username";
                            field[1] = "password";

                            //Creating array for data
                            String[] data = new String[2];

                           data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData(KnuInfoServer.server+"/knuinfo/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){
                                        Login(username);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"All fields required",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void Login(String username){
        SharedPrefUtil.PreferenceManager.setString(LoginActivity.this,"userID",username);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_SHORT).show();
        finish();
    }
}
