package com.example.knu_info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText etID, etPassword;
    String TAG = "LoginActivity";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = mAuth.getInstance();

        Button btnJoin = (Button) findViewById(R.id.btnJoin);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        etID = (EditText) findViewById(R.id.etID);
        etPassword = (EditText) findViewById(R.id.etPassword);

        //로그인 버튼 눌렀을 때
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = etID.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();

                db.collection("users")
                        .whereEqualTo("Id", id)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                    if (document.getData().get("Password").equals(pass)) {
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        i.putExtra("id",id);
                                        startActivity(i);
                                    } else {
                                        Log.e(TAG, "onSuccess: 비밀번호가 일치하지 않음");
                                        Toast.makeText(getApplicationContext(), "회원정보가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "onFailure: 로그인 실패");
                            }
                        });

                //LoginUser(id,pass);
            }
        });
        //회원가입 버튼 눌렀을 때
        btnJoin.setOnClickListener((view) ->

        {
            Intent in = new Intent(LoginActivity.this, JoinActivity.class);
            startActivity(in);
        });

    }

    public void LoginUser(String id, String pass) {
        mAuth.signInWithEmailAndPassword(id, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete" + task.isSuccessful());
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.w(TAG, "signInWithEmail", task.getException());
                    Toast.makeText(LoginActivity.this, "로그인오류", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}