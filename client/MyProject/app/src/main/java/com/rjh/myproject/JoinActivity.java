package com.rjh.myproject;

import androidx.appcompat.app.AppCompatActivity;

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

public class JoinActivity extends AppCompatActivity {

    EditText edt_join_id, edt_join_pw, edt_join_name;
    Button btn_join_finish;

    String id, pw, name;

    RequestQueue rq;
    StringRequest srq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_join_id = findViewById(R.id.edt_join_id);
        edt_join_pw = findViewById(R.id.edt_join_pw);
        edt_join_name = findViewById(R.id.edt_join_name);

        btn_join_finish = findViewById(R.id.btn_join_finish);

        rq = Volley.newRequestQueue(this);

        // Tomcat 서버 주소: Front를 거쳐 UserJoinService로 연결
        String url = "http://220.95.45.219:8099/SecondProject/join.do";

        srq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("1")) {
                    // out.print값으로 되돌려받은 preparedstatement.executeUpdate()의 결과값
                    Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                    finish(); // Activity 종료: MainActivity로 회귀
                } else if(response.equals("-1")){ // SQLException
                    Toast.makeText(getApplicationContext(),"회원가입 실패",Toast.LENGTH_SHORT).show();
                } else{ // 기타 Exception
                    Toast.makeText(getApplicationContext(),"잘못된 접근입니다.",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override // to RequestQueue
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> data = new HashMap<String, String>();

                data.put("id", id);
                data.put("pw", pw);
                data.put("name", name);

                return data;
            }
        };
        srq.setTag("MAIN");


        // join 버튼 클릭시 이벤트: 톰캣 서버로 데이터를 전송하고 액티비티 종료
        btn_join_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = edt_join_id.getText().toString();
                pw = edt_join_pw.getText().toString();
                name = edt_join_name.getText().toString();
                if(id.equals("")||pw.equals("")||name.equals("")){
                    Toast.makeText(getApplicationContext(),"ID, PW, 이름 항목은 비워둘 수 없습니다",Toast.LENGTH_SHORT).show();
                } else{
                    rq.add(srq);
                }
            }
        });
    }
}