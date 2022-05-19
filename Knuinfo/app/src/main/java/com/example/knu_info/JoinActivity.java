package com.example.knu_info;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.knu_info.server.KnuInfoServer;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinActivity extends AppCompatActivity {
    String TAG = "JoinActivity";
    TextInputEditText etJName,etJid,etJpass,etJpass_check,etJEmail,etJPhone;
    private boolean validate=false;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        etJName =  findViewById(R.id.fullname);
        etJid =  findViewById(R.id.username);
        etJpass =  findViewById(R.id.password);
        etJpass_check =  findViewById(R.id.password_ck);
        etJPhone = findViewById(R.id.phone);
        etJEmail = findViewById(R.id.email);

        Button btnJjoin = (Button) findViewById(R.id.btnJjoin);


        etJpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etJpass_check.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        Button btnID=(Button)findViewById(R.id.btnID);

        //id중복체크
        btnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID=etJid.getText().toString();
                if(validate)
                {
                    return;
                }
                if(userID.equals("")){
                    AlertDialog.Builder builder=new AlertDialog.Builder( JoinActivity.this );
                    dialog=builder.setMessage("아이디는 빈 칸일 수 없습니다")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i(TAG, "onResponse validation : "+response);
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder=new AlertDialog.Builder( JoinActivity.this );
                                dialog=builder.setMessage("사용할 수 있는 아이디입니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                etJid.setEnabled(false);
                                validate=true;
                                btnID.setText("확인");
                            }
                            else{
                                AlertDialog.Builder builder=new AlertDialog.Builder( JoinActivity.this );
                                dialog=builder.setMessage("사용할 수 없는 아이디입니다.")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest=new ValidateRequest(userID,responseListener);
                RequestQueue queue= Volley.newRequestQueue(JoinActivity.this);
                queue.add(validateRequest);
            }
        });

        //가입하기 버튼
        btnJjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate){
                    Toast.makeText(getApplicationContext(),"아이디 중복 확인 해주세요.",Toast.LENGTH_LONG).show();
                    return;
                }
                String fullname, username, password, phone, email;
                fullname = String.valueOf(etJName.getText());
                username = String.valueOf(etJid.getText());
                password = String.valueOf(etJpass.getText());
                phone = String.valueOf(etJPhone.getText());
                email = String.valueOf(etJEmail.getText());
                Log.i(TAG, "onClick: "+fullname);
                Log.i(TAG, "onClick: "+username);
                Log.i(TAG, "onClick: "+password);
                Log.i(TAG, "onClick: "+phone);
                Log.i(TAG, "onClick: "+email);
                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !phone.equals("") && !email.equals("")) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "phone";
                            field[4] = "email";

                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = phone;
                            data[4] = email;
                            PutData putData = new PutData(KnuInfoServer.server+"/knuinfo/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                Log.i(TAG, "run: put Start");
                                if (putData.onComplete()) {
                                    Log.i(TAG, "run: put onComplete");

                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Log.i(TAG, "run: put Success");
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getApplicationContext(),"회원가입 완료",Toast.LENGTH_LONG).show();
                                            }
                                        });


                                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Log.i(TAG, "run: put fail");
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                } else {
                                    Log.i(TAG, "run: Time out?");
                                }
                            }
                        }
                    }).start();
//                    Handler handler = new Handler();
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            //End Write and Read data with URL
//
//                        }
//                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"All fields required",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}