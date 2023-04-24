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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public Button btn;
    public Button btn2;
    public TextView tv;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public CollectionReference user = db.collection("user");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView);
        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Map<String, Object> user = new HashMap<>();
                        user.put("first", "Ada");
                        user.put("last", "Lovelace");
                        user.put("born", 1815);

                        // Add a new document with a generated ID
                        db.collection("user")
                                .add(user);
                    }
                }
        );
        btn2 = (Button) findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                db.collection("user").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                String result = new String();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String info = document.getString("first") ;
                                    info += " " + document.getString("last");
                                    info += ", " + document.getLong("born");
                                    result += info + "\n";
                                }
                                Intent intent = new Intent(MainActivity.this, ListName.class);
                                intent.putExtra("listName", result);
                                startActivity(intent);
                            }
                        }
                    });

//                Intent intent = new Intent(MainActivity.this, ListName.class);
//                intent.putStringArrayListExtra("listName", result);
//                startActivity(intent);
            }
        });
    }
}