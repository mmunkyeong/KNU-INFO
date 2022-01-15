package com.example.knu_info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class JoinActivity extends AppCompatActivity {
    String TAG = "JoinActivity";
    EditText etJName;
    EditText etJid;
    EditText etJpass;
    EditText etJpass_check;
    EditText etJEmail;
    EditText etJPhone;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private AlertDialog dialog;
    private boolean validate = false;
    private DatabaseReference mDatabase;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }

        };
        Button btnIDcheck = (Button) findViewById(R.id.btnID);
        btnIDcheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = etJid.getText().toString().trim();
                //String pass = etPassword.getText().toString().trim();
                    validate=true;
                db.collection("users")
                        .whereEqualTo("Id", id)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                    if (document.getData().get("Id").equals(id)) {
                                        Log.e(TAG, "onSuccess:아이디 중복");
                                        Toast.makeText(getApplicationContext(), "이미 아이디가 존재합니다.", Toast.LENGTH_LONG).show();
                                        etJid.setText(null);
                                        return;
                                    }
                                }
                                Toast.makeText(getApplicationContext(), "아이디 사용 가능", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "onFailure: 실패 ");
                            }
                        });

                //LoginUser(id,pass);
            }
        });



        etJName = (EditText) findViewById(R.id.etJName);
        etJid = (EditText) findViewById(R.id.etJid);
        etJpass = (EditText) findViewById(R.id.etJpass);
        etJpass_check = (EditText) findViewById(R.id.etJPasswordCheck);
        etJPhone = (EditText) findViewById(R.id.etJPhone);
        etJEmail = (EditText) findViewById(R.id.etJEmail);

        Button btnJjoin = (Button) findViewById(R.id.btnJjoin);
        Button btnID=(Button)findViewById(R.id.btnID);


        //가입하기 버튼
        btnJjoin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String stName = etJName.getText().toString();
                String stId = etJid.getText().toString();
                String stPass = etJpass.getText().toString();
                String stPass_check = etJpass_check.getText().toString();
                String stPhone = etJPhone.getText().toString();
                String stEmail = etJEmail.getText().toString();
                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("중복된 아이디가 있는지 확인하세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                if (stName.equals("") || stId.equals("") || stPass.equals("")  || stPass_check.equals("") || stPhone.equals("")|| stEmail.equals("")  ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                if (stPass.equals(stPass_check)) {
                    HashMap<String, String> user = new HashMap<>();
                    user.put("Name", stName);
                    user.put("Id", stId);
                    user.put("Password", stPass);
                    user.put("Phone", stPhone);
                    user.put("Email", stEmail);
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getApplicationContext(), "가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });

                } else {
                    Toast.makeText(getApplicationContext(), "비밀번호가 서로 다릅니다.", Toast.LENGTH_LONG).show();
                }

                //joinUser(stId,stPass);
            }
        });
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void joinUser(String id, String pass) {
        mAuth.createUserWithEmailAndPassword(id, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete" + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Toast.makeText(JoinActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}