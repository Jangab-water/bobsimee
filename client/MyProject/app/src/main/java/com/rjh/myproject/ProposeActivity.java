package com.rjh.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ProposeActivity extends AppCompatActivity {
    TextView tv_propose_name, tv_propose_ingredient, tv_propose_recipe, tv_propose_url;
    Intent intent;
    private WebView mWebView;


    String intent_title, intent_url, intent_ingredient, intent_recipe;
    String[] ingredient, recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose);
        intent = getIntent();
        intent_title = intent.getStringExtra("title").substring(6).replace("\"}","");
        intent_url = intent.getStringExtra("url").substring(6).replace("\"}","");
        intent_ingredient = intent.getStringExtra("ingredient").substring(6).replace("\"}","").replace("__","\n");
        intent_recipe = intent.getStringExtra("recipe").substring(6).replace("\"}","").replace("$","\n");
        int titleLength = intent_title.length();
        int urlLength = intent_url.length();
        int ingLength = intent_ingredient.length();
        int recipeLength = intent_recipe.length();

        //ingredient = intent_ingredient.split("\r\n");
        // intent_ingredient.replace(System.getProperty("line.seperator","\n"));
        intent_ingredient = intent_ingredient.replace("__", ", ");
        intent_ingredient = intent_ingredient.replace("*", "\n");
        recipe = intent_recipe.split("\n");
        // 레시피 엑셀파일에서 데이터 정리하기!

        tv_propose_name = findViewById(R.id.tv_propose_name);
        tv_propose_ingredient = findViewById(R.id.tv_propose_ingredient);
        tv_propose_recipe = findViewById(R.id.tv_propose_recipe);
        // tv_propose_url = findViewById(R.id.tv_propose_url);

        tv_propose_name.setTextColor(Color.parseColor("#000000"));
        tv_propose_name.setText(intent_title);

        tv_propose_ingredient.setTextColor(Color.parseColor("#000000"));
        tv_propose_ingredient.setText(intent_ingredient);

        tv_propose_recipe.setTextColor(Color.parseColor("#000000"));
        tv_propose_recipe.setText(intent_recipe);
//
//        tv_propose_url.setTextColor(Color.parseColor("#000000"));
//        tv_propose_url.setText(intent_url);


        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(intent_url);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClientClass());


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class WebViewClientClass extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL", url);
            view.loadUrl(url);
            return true;
        }


    }
}