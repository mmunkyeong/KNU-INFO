package com.example.knu_info;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class JoinActivity extends AppCompatActivity {
    String TAG="JoinActivity";
    EditText etJid;
    EditText etJpass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            public void onAuthStateChanged(FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG,"onAuthStateChanged:signed_in:"+user.getUid());
                }
                else{
                    Log.d(TAG,"onAuthStateChanged:signed_out");
                }

            }
        };
        etJid = (EditText)findViewById(R.id.etJid);
        etJpass=(EditText)findViewById(R.id.etJpass);
        Button btnJjoin=(Button)findViewById(R.id.btnJjoin);



        btnJjoin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String stId = etJid.getText().toString();
                String stPass=etJpass.getText().toString();
                joinUser(stId,stPass);
            }
        });
    }
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop(){
        super.onStop();
        if(mAuthListener!=null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void joinUser(String id,String pass){
        mAuth.createUserWithEmailAndPassword(id,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG,"createUserWithEmail:onComplete"+task.isSuccessful());
                if(!task.isSuccessful()){
                    Toast.makeText(JoinActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}