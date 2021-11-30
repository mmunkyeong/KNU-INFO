package com.example.knu_info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Hashtable;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText etText;
    Button btnFinish,btnSend;
    String stEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        btnFinish = (Button)findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener((v)->{finish();});
        btnSend = (Button)findViewById(R.id.btnSend);
        etText = (EditText)findViewById(R.id.etText);
        stEmail=getIntent().getStringExtra("email");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);



       recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        String[] mDataset = {"test1","test2","test3","test4"};
        mAdapter= new MyAdapter(mDataset);
        recyclerView.setAdapter(mAdapter);

        btnSend.setOnClickListener((v)->{

            String stText=etText.getText().toString();
            Toast.makeText(ChatActivity.this,"MSG:"+stText,Toast.LENGTH_LONG).show();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("message");
            myRef.setValue(stText);
            //Hashtable<String,String>numbers=new Hashtable<String,String>();
           // numbers.put("email",stEmail);
           // numbers.put("text",stText);
           // myRef.setValue(numbers);


        });
    }
}
