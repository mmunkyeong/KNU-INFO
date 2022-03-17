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

import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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
        Button btnID=(Button)findViewById(R.id.btnID);

        etJpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etJpass_check.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        /*btnID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String UserEmail = etJid.getText().toString();
                if (validate) {
                    return; //검증 완료
                }

                if (UserEmail.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                etJid.setEnabled(false); //아이디값 고정
                                validate = true; //검증 완료
                               //btnID.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                dialog = builder.setMessage("이미 존재하는 아이디입니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(UserEmail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
                queue.add(validateRequest);
            }
        });*/
        //가입하기 버튼
        btnJjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname,username,password,email;
                fullname = String.valueOf(etJName.getText());
                username = String.valueOf(etJid.getText());
                password = String.valueOf(etJpass.getText());
                email = String.valueOf(etJEmail.getText());
                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData("http://59.151.245.72/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                Log.i(TAG, "run: put Start");
                                if (putData.onComplete()) {
                                    Log.i(TAG, "run: put onComplete");

                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Log.i(TAG, "run: put Success");

                                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Log.i(TAG, "run: put fail");

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
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