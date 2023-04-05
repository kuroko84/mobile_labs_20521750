package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView ResultView;
    EditText inputTextName;
    EditText inputTextSal;

    public class Employee {
        String Name;
        Double Sal;
        public Employee(){};
        public Employee(String name, Double sal){
            this.Name = name;
            this.Sal = sal;
        }
        public Double CalculateSalary(Double sal){
            if(sal <= 11000000)
                return sal;
            else
                return(sal - 11000000) * 0.95 + 11000000;
        }
    }
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResultView = (TextView) findViewById(R.id.Result);
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
                    Double sal = Double.parseDouble(inputTextSal.getText().toString());
                    Employee emp = new Employee(name, sal);
                    String ResultName = ResultView.getText().toString();
                    String Result = ResultName + "\n" + emp.Name;
                    Result += " " + String.format("%.0f",emp.CalculateSalary(emp.Sal));
                    ResultView.setText(Result);
                }
            }
        );
    }

}