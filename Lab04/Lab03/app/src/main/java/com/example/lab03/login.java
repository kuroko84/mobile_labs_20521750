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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    public Button btnLogin;
    public TextView tvSignUp;
    public EditText etLoginUsername, etLoginPassword;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog); // Tạo layout tùy chỉnh cho Dialog

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        tvSignUp = (TextView) findViewById(R.id.backtologin);

        btnLogin.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    etLoginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
                    etLoginUsername = (EditText) findViewById(R.id.editTextLoginUsername);
                    String username = etLoginUsername.getText().toString();
                    String password = etLoginPassword.getText().toString();

                    // Tạo truy vấn để kiểm tra sự tồn tại của username
                    CollectionReference userCollection = db.collection("user");
                    Query query = userCollection.whereEqualTo("Username", username);
                    //check password
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                QuerySnapshot querySnapshot = task.getResult();
                                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                    // Tồn tại username và password trong Firestore
                                    // Thực hiện các thao tác khi xác thực thành công
                                    DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                    //obj user
                                    Map<String, Object> user = documentSnapshot.getData();
                                    String hashedPassword = (String) user.get("HashedPassword");
                                    boolean isPasswordMatched = BCrypt.checkpw(password, hashedPassword);
                                    if(isPasswordMatched){
                                        // thông báo dăng nhập thành công
                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(0, 255, 128)));
                                        TextView textMessage = dialog.findViewById(R.id.text_notify);
                                        textMessage.setText("Login Successfully");
                                        dialog.show();

                                        // Đóng Dialog sau vài giây
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                dialog.dismiss();
                                            }
                                        }, 5000);

                                        //direct to homepage
                                        Intent intent = new Intent(login.this, home.class);
                                        startActivity(intent);
                                    } else{
                                        // thông báo sai mật khẩu
                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
                                        TextView textMessage = dialog.findViewById(R.id.text_notify);
                                        textMessage.setText("Wrong password");
                                        dialog.show();

                                        // Đóng Dialog sau vài giây
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                dialog.dismiss();
                                            }
                                        }, 5000);
                                    }
                                } else {
                                    // thông báo không tồn tại user
                                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
                                    TextView textMessage = dialog.findViewById(R.id.text_notify);
                                    textMessage.setText("Username doesn't exist");
                                    dialog.show();

                                    // Đóng Dialog sau vài giây
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialog.dismiss();
                                        }
                                    }, 5000);
                                }
                            } else {
                                // thông báo lỗi bất ngờ
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
                                TextView textMessage = dialog.findViewById(R.id.text_notify);
                                textMessage.setText("Something wrong");
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
                    });
                }
            }
        );

        tvSignUp.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    // direct to login
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        );

    }
}