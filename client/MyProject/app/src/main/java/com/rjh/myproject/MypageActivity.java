package com.rjh.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MypageActivity extends AppCompatActivity {

    // 뷰 정의
    Button btn_mypage_logout, btn_go;
    EditText edt_mypage_ingredient1, edt_mypage_ingredient2;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        // 재료를 입력받아 db에서 select하는 구간
        btn_mypage_logout = findViewById(R.id.btn_mypage_logout);

        // logout 버튼 클릭시: MainActivity로 회귀
        btn_mypage_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 남은 재료를 입력할 칸
        edt_mypage_ingredient1 = findViewById(R.id.edt_mypage_ingredient1);
        edt_mypage_ingredient2 = findViewById(R.id.edt_mypage_ingredient2);
        btn_go=findViewById(R.id.btn_mypage_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ing1=edt_mypage_ingredient1.getText().toString();
                String ing2=edt_mypage_ingredient2.getText().toString();
                intent=new Intent(MypageActivity.this,SurveyActivity.class);
                intent.putExtra("ing1",ing1+"");
                intent.putExtra("ing2",ing2+"");
                startActivity(intent);

            }
        });

    }
}