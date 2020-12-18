package com.rjh.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btn_main_login, btn_main_join;
    EditText edt_main_id, edt_main_pw;
    String id, pw;
    Intent intent;

    RequestQueue rq;
    StringRequest srq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_join = findViewById(R.id.btn_main_join);
        btn_main_login = findViewById(R.id.btn_main_login);
        edt_main_id = findViewById(R.id.edt_main_id);
        edt_main_pw = findViewById(R.id.edt_main_pw);

        // 데이터 전송통로 생성: context=MainActivity
        rq = Volley.newRequestQueue(this);

        // Eclipse Tomcat 서버 주소: Front를 거쳐 UserLoginService로 이동
        String url = "http://220.95.45.219:8099/SecondProject/login.do";

        srq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 서버로부터 응답받기
            @Override
            public void onResponse(String response) {
                if (response.equals("")) {
                    Log.v("ddd: ","name: "+response);
                    // response가 ""일 경우 -- 서버로부터 out.print 값을 돌려받는 법을 찾아야 한다
                    Toast.makeText(getApplicationContext(), "id 혹은 pw를 확인하세요", Toast.LENGTH_SHORT).show();
                } else {
                    // 로그인 성공했을 경우
                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                    intent = new Intent(MainActivity.this, MypageActivity.class);

                    // id 값을 담아서 pass
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 서버에러: SQLException 일 경우 Toast 메세지 출력
                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        }) {
            // 서버로 전송할 데이터
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> data = new HashMap<>();
                data.put("id", id);
                data.put("pw", pw);
                return data;
            }
        };

        srq.setTag("MAIN");

        // 버튼 클릭시 이벤트: 회원가입 페이지(JoinActivity)로 이동
        btn_main_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, JoinActivity.class);
                startActivity(intent);

            }
        });

        // 로그인 버튼 클릭시 이벤트: login시도
        btn_main_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // id 또는 pw 데이터 EditText로부터 가져오기
                id = edt_main_id.getText().toString();
                pw = edt_main_pw.getText().toString();

                if (id.equals("") || pw.equals("")) {
                    // id 또는 pw 창이 비어있을 경우 출력되는 오류메세지
                    Toast.makeText(getApplicationContext(), "ID 또는 PW는 비워둘 수 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    rq.add(srq);
                }

            }
        });

    }
}