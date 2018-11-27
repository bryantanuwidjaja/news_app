package com.example.bryan.newsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticlesActivity extends AppCompatActivity {
    private static final String TAG = "ArticlesActivity";
    String API_KEY = "b013eb3ada5b49438417fdd444ed5981";
    String sourceKey;

    ArrayList<HashMap<String, String>> dataArticle = new ArrayList<>();
    static final String TITLE= "title";
    static final String DESCRIPTION = "description";
    static final String URL = "url";
    static final String URLTOIMAGE = "urlToImage";
    ListView listView_Articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        Intent intent = getIntent();
        sourceKey = intent.getStringExtra("sourceKey");
        Log.d(TAG, "onCreate: In"+sourceKey);


        Button button_Back = findViewById(R.id.button_ArticlesActivity_Back);
        listView_Articles = findViewById(R.id.listView_ArticlesActivity_Articles);

        if(Network.checkNetwork(getApplicationContext()))
        {
            NewsIn newsIn = new NewsIn();
            newsIn.execute();
        }

        button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),SourcesActivity.class);
                startActivity(intent);
            }
        });


    }

    class NewsIn extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        protected String doInBackground(String... args){
            String xml = "";
            String urlParam = "";
            xml = Network.excuteGet("https://newsapi.org/v2/top-headlines?sources="+sourceKey+"&apiKey="+API_KEY,urlParam);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml){
            if(xml.length()>10){
                try{
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");
                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String,String> map = new HashMap<>();
                        map.put(TITLE, jsonObject.optString(TITLE).toString());
                        map.put(DESCRIPTION, jsonObject.optString(DESCRIPTION).toString());
                        map.put(URLTOIMAGE, jsonObject.optString(URLTOIMAGE).toString());
                        dataArticle.add(map);
                    }
                }catch (JSONException e){
                    Toast.makeText(getApplicationContext(),"Error, Please Try Again Later",Toast.LENGTH_LONG);
                }

                ListArticlesAdapter adapter = new ListArticlesAdapter(ArticlesActivity.this,dataArticle);
                listView_Articles.setAdapter(adapter);

                listView_Articles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
            }else {
                Toast.makeText(getApplicationContext(), "No News Available", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
