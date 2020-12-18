package com.rjh.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ErrorActivity extends AppCompatActivity {

    TextView tv_error_msg;
    Button btn_error_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        tv_error_msg=findViewById(R.id.tv_error_msg);
        btn_error_back=findViewById(R.id.btn_error_back);
        tv_error_msg.setText("검색 결과가 없어요!");
        btn_error_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}