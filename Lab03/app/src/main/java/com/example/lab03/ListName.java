package com.example.lab03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListName extends AppCompatActivity {
    public TextView tv;
    public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_name);

        tv = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.show);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String value = new String();
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    value = extras.getString("listName");
                }
                tv.setText(value);
            }
        });
//
    }
}