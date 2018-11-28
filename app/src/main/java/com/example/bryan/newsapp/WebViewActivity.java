package com.example.bryan.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {
    String url;
    String sourceKey;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        TextView textView_Header = findViewById(R.id.textView_WebViewActivity_Header);
        TextView textView_HeaderURL = findViewById(R.id.textView_WebViewActivity_HeaderURL);
        Button button_Back = findViewById(R.id.button_WebViewActivity_Back);
        WebView webView=findViewById(R.id.webView_WebViewActivity);

        Intent intent = getIntent();
        sourceKey = intent.getStringExtra("sourceKey");
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");

        textView_Header.setText(title);
        textView_HeaderURL.setText(url);

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
