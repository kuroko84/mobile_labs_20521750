package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView CalPersonSalary;
    EditText inputTextName;
    EditText inputTextSal;
    public class Employee {
        String Name;
        Long Sal;
        public Employee(){};
        public Employee(String name, Long sal){
            this.Name = name;
            this.Sal = sal;
        }
    }
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalPersonSalary = (TextView) findViewById(R.id.CalPersonSalary);
        inputTextName = (EditText) findViewById(R.id.inputName);
        inputTextSal = (EditText) findViewById(R.id.inputSal);
        btn = (Button)  findViewById(R.id.btn_cal_sal);
        btn.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    String name = inputTextName.getText().toString();
                    Long sal = Long.parseLong(inputTextSal.getText().toString());
                    Employee emp = new Employee(name, sal);
                    CalPersonSalary.setText(emp.Name + emp.Sal );
                }
            }
        );
    }

}