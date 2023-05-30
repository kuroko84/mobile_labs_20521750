package com.example.lab03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.mindrot.jbcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {
    public Button btn;
    public Button btn2;
    public TextView tv;
    public EditText etName, etPhone, etUsername, etPassword;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public CollectionReference user = db.collection("user");

    // Kiểm tra username
    public boolean isUsernameValid(String username) {
        // Kiểm tra độ dài tối thiểu là 6 ký tự và chỉ chứa chữ cái
        String pattern = "[a-zA-Z]{6,}";
        return username.matches(pattern);
    }

    // Kiểm tra password
    public boolean isPasswordValid(String password) {
        // Kiểm tra độ dài tối thiểu là 6 ký tự
        return password.length() >= 6;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog); // Tạo layout tùy chỉnh cho Dialog

//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));

        btn = (Button) findViewById(R.id.button);

        etName = (EditText) findViewById(R.id.editTextName);
        etPhone = (EditText) findViewById(R.id.editTextPhone);
        etUsername = (EditText) findViewById(R.id.editTextUsername);
        etPassword = (EditText) findViewById(R.id.editTextPassword);

        btn.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    //Get values
                    String name = etName.getText().toString();
                    String phone = etPhone.getText().toString();
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();

                    if(isUsernameValid(username) && isPasswordValid(password)){
                        //mã hoá password
                        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(6));

                        //check password
                        boolean isPasswordMatched = BCrypt.checkpw(password, hashedPassword);

                        //Thêm user
                        Map<String, Object> user = new HashMap<>();
                        user.put("Name", name);
                        user.put("Phone", phone);
                        user.put("Username", username);
                        user.put("HashedPassword", hashedPassword);
                        // Add a new document with a generated ID
                        db.collection("user").add(user);

                        // thông báo dăng ký thành công
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(0, 255, 128)));
                        TextView textMessage = dialog.findViewById(R.id.text_notify);
                        textMessage.setText("Register Successfully");
                        dialog.show();

                        // Đóng Dialog sau vài giây
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 5000);

                        // direct to login
                        Intent intent = new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                    } else {
                        // thông báo dăng ký thất bại
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
                        TextView textMessage = dialog.findViewById(R.id.text_notify);
                        textMessage.setText("Register Failed");
                        dialog.show();

                        // Đóng Dialog sau vài giây
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        }, 5000);
                    }
                }
            }
        );
        // back to login
        tv = (TextView) findViewById(R.id.backtologin);
        tv.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        // direct to login
                        Intent intent = new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                    }
                }
        );




//        btn2 = (Button) findViewById(R.id.button2);
//
//        btn2.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                db.collection("user").get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                String result = new String();
//                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                    String info = document.getString("first") ;
//                                    info += " " + document.getString("last");
//                                    info += ", " + document.getLong("born");
//                                    result += info + "\n";
//                                }
//                                Intent intent = new Intent(MainActivity.this, ListName.class);
//                                intent.putExtra("listName", result);
//                                startActivity(intent);
//                            }
//                        }
//                    });
//
////                Intent intent = new Intent(MainActivity.this, ListName.class);
////                intent.putStringArrayListExtra("listName", result);
////                startActivity(intent);
//            }
//        });
    }
}