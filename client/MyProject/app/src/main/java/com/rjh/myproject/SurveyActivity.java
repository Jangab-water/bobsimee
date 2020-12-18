package com.rjh.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SurveyActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    Button btn_survey_val1, btn_survey_val2, btn_survey_val3;

    String ing1, ing2;

    String title, ingredient, recipe, url;

    StringRequest srq;
    RequestQueue rq;

    String URL;

    ArrayList<ChatDTO> chats;
    FoodDTO dto;

    ChatAdapter adapter;

    int step = 0;
    RecyclerView rv_chat;

    String[] questions = {"오늘 날씨는 어때요?", "밖은 많이 추워요?", "기분은 어때요?", "싫은 이유는요?", "좋은 이유는요?"};
    String[][] answers = {{"맑아", "구름이 좀 있어", "비가 오네"}, // step=1, 날씨 묻기 set rain
            {"오늘은 더워", "보통이야", "오늘 좀 추워"}, // step=2, 기온 묻기 set hot, cold
            {"기분 좋아", "그저 그래", "기분 안 좋아"}, // step=3, 기분 묻기 ? 4 : 5 set good, stress
            {"싸웠어", "입맛이 없어", "오늘 힘들었어"}, // step=4, 이유 묻기 set fight, tasty, reward
            {"친구가 왔거든", "오늘은 기념일이야", "기분좋은 일이 있어"}}; // step=4 이유 묻기 set friend, congratulation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        // 저번 레이아웃에서 가져오기
        intent = getIntent();
        dto=new FoodDTO();
        try {
            ing1 = intent.getStringExtra("ing1");

            ing2 = intent.getStringExtra("ing2");
        } catch(Exception e){
            ing1="no data";
            ing2="no data";
        }
        ing1="ing1";
        ing2="ing2";

        // button id 선언
        btn_survey_val1 = findViewById(R.id.btn_survey_value1);
        btn_survey_val1.setOnClickListener(this);
        btn_survey_val2 = findViewById(R.id.btn_survey_value2);
        btn_survey_val2.setOnClickListener(this);
        btn_survey_val3 = findViewById(R.id.btn_survey_value3);
        btn_survey_val3.setOnClickListener(this);


        // RecyclerView 선언
        rv_chat = findViewById(R.id.rv_chat);

        // RecyclerView의 타입 선언: Linear/Grid, context:this(RecyclerView인 chatPage가 activity_talk.xml내에 있어서)
        rv_chat.setLayoutManager(new LinearLayoutManager(this));

        // 초기 세팅: 첫번째 답변
        btn_survey_val1.setText("맑아");
        btn_survey_val2.setText("구름이 좀 있어");
        btn_survey_val3.setText("비가 오고 있어");
        chats=new ArrayList<>();
        chats.add(new ChatDTO(R.drawable.desayuno,"밥심이",questions[step]));

        // adapter 세팅
        adapter=new ChatAdapter(chats);
        rv_chat.setAdapter(adapter);
        adapter.notifyItemInserted(chats.size());

        ing1=intent.getStringExtra("ing1");
        ing2=intent.getStringExtra("ing2");

        // test from log
    }

    @Override
    public void onClick(View v) {
        Log.v("btn","btn clicked");
        // 단계에 따른 각 세팅
        if(step==0){ // 비가 오는가?
            if(v.getId()==R.id.btn_survey_value1){
                dto.setRain(1);
            } else if(v.getId()==R.id.btn_survey_value2){
                dto.setRain(2);
            } else{
                dto.setRain(3);
            }
        } else if(step==1){ // 기온은 어떤가?
            if(v.getId()==R.id.btn_survey_value1){
                dto.setHot(3);
                dto.setCold(1);
            } else if(v.getId()==R.id.btn_survey_value2){
                dto.setHot(2);
                dto.setCold(2);
            } else{
                dto.setCold(3);
                dto.setHot(1);
            }
        } else if(step==2){ // 기분은 어떤가?
            if(v.getId()==R.id.btn_survey_value1){
                dto.setStress(1);
                dto.setGood(3);
                step++;
            } else if(v.getId()==R.id.btn_survey_value2){
                dto.setStress(2);
                dto.setGood(2);
                dto.setFight(2);
                dto.setTasty(2);
                dto.setReward(2);
                dto.setFriend(2);
                dto.setCongratulation(2);
                Toast.makeText(getApplicationContext(),"질문이 모두 끝났어요!",Toast.LENGTH_SHORT).show();
                goPython();
            } else{
                dto.setStress(3);
                dto.setGood(1);
            }
        } else if(step==3){ // 왜 안좋은가?
            if(v.getId()==R.id.btn_survey_value1){
                dto.setFight(3);
                dto.setTasty(1);
                dto.setReward(1);
            } else if(v.getId()==R.id.btn_survey_value2){
                dto.setTasty(3);
                dto.setFight(1);
                dto.setReward(1);
            } else{
                dto.setReward(3);
                dto.setTasty(1);
                dto.setFight(1);
            }
            Toast.makeText(getApplicationContext(),"질문이 모두 끝났어요!",Toast.LENGTH_SHORT).show();
            goPython();
        } else if(step==4){ // 왜 좋은가?
            if(v.getId()==R.id.btn_survey_value1){
                dto.setFriend(3);
                dto.setCongratulation(2);
            } else if(v.getId()==R.id.btn_survey_value2){
                dto.setFriend(3);
                dto.setCongratulation(3);
            } else{
                dto.setFriend(2);
                dto.setCongratulation(3);
            }
            Toast.makeText(getApplicationContext(),"질문이 모두 끝났어요!",Toast.LENGTH_SHORT).show();
            // 하단의 메소드로 이동: flask 서버로 데이터 보내기
            goPython();
        }
        // 누른 button의 Text값 가져오기
        String ans=((Button)v).getText().toString();

        // 누른 button의 텍스트 값으로 새 채팅 생성
        chats.add(new ChatDTO(R.drawable.comestibles,"",ans));

        // 단계 증가
        step++;

        // 버튼에 질문 세팅하기
        try {
            for (int i = 0; i < answers[step].length; i++) {
                btn_survey_val1.setText(answers[step][0]);
                btn_survey_val2.setText(answers[step][1]);
                btn_survey_val3.setText(answers[step][2]);
            }
        } catch (Exception e){
            Log.v("question","질문 끝");
        }
        try {
            chats.add(new ChatDTO(R.drawable.desayuno, "밥심이", questions[step]));
        } catch(Exception e){
        }
        adapter.notifyItemInserted(chats.size());

    }


    public void goPython() {
        rq = Volley.newRequestQueue(this);

        URL = "http://220.95.45.219:9010/SendSurveyResult";

        srq = new StringRequest(Request.Method.POST,URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("response","response on??");
                if (!response.equals("null")) {
                    try {
                        response = URLDecoder.decode(response, "EUC-KR");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);

                        title = jsonObject.getString("TITLE");
                        url = jsonObject.getString("URL");
                        ingredient = jsonObject.getString("INGREDIENT");
                        recipe = jsonObject.getString("METHOD");
//                try {
//                    JsonParser parser = new JsonParser();
//                    JsonElement jsonElement = parser.parse(response);
//                    Log.v("response","response::"+response);
//                    title=jsonElement.getAsJsonObject().get("title").getAsString();
//                    Log.v("response","title::"+title);
//                    url=jsonElement.getAsJsonObject().get("url").getAsString();
//                    Log.v("response","url::"+url);
//                    ingredient=jsonElement.getAsJsonObject().get("ingredient").getAsString();
//                    Log.v("response","ingredient::"+ingredient);
//                    recipe=jsonElement.getAsJsonObject().get("recipe").getAsString();
//                    Log.v("response","recipe::"+recipe);

//                    title = jsonElement.getString("title");
//                    Log.v("response","response::"+title);
//                    url = response.getString("URL");
//                    Log.v("response","response::"+url);
//                    ingredient = response.getString("INGREDIENT");
//                    Log.v("response","response::"+ingredient);
//                    recipe = response.getString("METHOD");
//                    Log.v("response","response::"+recipe);
                    } catch (Exception e) {
                        Log.e("response", "response::JSONException " + e);
                    }

                    intent = new Intent(SurveyActivity.this, ProposeActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("url", url);
                    intent.putExtra("ingredient", ingredient);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("response","VolleyError="+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> data=new HashMap<>();
                data.put("ing1",ing1);
                data.put("ing2",ing2);
                data.put("rain", dto.getRain() + "");
                data.put("cold", dto.getCold() + "");
                data.put("hot", dto.getHot() + "");
                data.put("stress", dto.getStress() + "");
                data.put("good",dto.getGood()+"");
                data.put("fight", dto.getFight() + "");
                data.put("friend", dto.getFriend() + "");
                data.put("tasty", dto.getTasty() + "");
                data.put("reward", dto.getReward() + "");
                data.put("congratulation", dto.getCongratulation() + "");
                Log.v("params","params::in");
                return data;
            }
        };
        srq.setTag("TAG");
        rq.add(srq);

    }

    private JSONObject getParams() {
        JSONObject data = new JSONObject();

        Log.v("response", "getParams called::ON");
        try {
            data.put("ing1",ing1);
            data.put("ing2",ing2);
            data.put("rain", dto.getRain() + "");
            data.put("cold", dto.getCold() + "");
            data.put("hot", dto.getHot() + "");
            data.put("stress", dto.getStress() + "");
            data.put("good",dto.getGood()+"");
            data.put("fight", dto.getFight() + "");
            data.put("friend", dto.getFriend() + "");
            data.put("tasty", dto.getTasty() + "");
            data.put("reward", dto.getReward() + "");
            data.put("congratulation", dto.getCongratulation() + "");
            Log.v("response","response DTO::"+dto.toString());
        } catch (Exception e) {
            Log.v("response","ERROR");
        }
        return data;
    }


}




