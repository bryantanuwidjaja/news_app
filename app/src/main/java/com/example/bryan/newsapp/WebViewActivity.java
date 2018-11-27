package com.example.bryan.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity {
    String url;
    String sourceKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Button button_Back = findViewById(R.id.button_WebViewActivity_Back);
        WebView webView=findViewById(R.id.webView_WebViewActivity);

        Intent intent = getIntent();
        sourceKey = intent.getStringExtra("sourceKey");
        url = intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebViewActivity.this,ArticlesActivity.class);
                intent.putExtra("sourceKey",sourceKey);
                startActivity(intent);
            }
        });

    }
}
