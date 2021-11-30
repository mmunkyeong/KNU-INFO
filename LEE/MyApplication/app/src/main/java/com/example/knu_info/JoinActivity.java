package com.example.knu_info;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

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
        etJName = (EditText) findViewById(R.id.etJName);
        etJid = (EditText) findViewById(R.id.etJid);
        etJpass = (EditText) findViewById(R.id.etJpass);
        etJpass_check = (EditText) findViewById(R.id.etJPasswordCheck);
        etJPhone = (EditText) findViewById(R.id.etJPhone);
        etJEmail = (EditText) findViewById(R.id.etJEmail);

        Button btnJjoin = (Button) findViewById(R.id.btnJjoin);

        //가입하기 버튼
        btnJjoin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String stName = etJName.getText().toString();
                String stId = etJid.getText().toString();
                String stPass = etJpass.getText().toString();
                String stPass_check = etJpass_check.getText().toString();
                String stPhone = etJPhone.getText().toString();
                String stEmail = etJEmail.getText().toString();
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