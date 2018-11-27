package com.example.bryan.newsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
    String xml = "";


    ArrayList<HashMap<String, String>> dataArticle = new ArrayList<>();
    static final String TITLE= "title";
    static final String DESCRIPTION = "description";
    static final String URL = "url";
    static final String URLTOIMAGE = "urlToImage";
    ListView listView_Articles;
    ListArticlesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        Intent intent = getIntent();
        sourceKey = intent.getStringExtra("sourceKey");
        Log.d(TAG, "onCreate: In"+sourceKey);

        EditText editText_Search = findViewById(R.id.editText_ArticlesActivity_Search);
        Button button_Back = findViewById(R.id.button_ArticlesActivity_Back);
        listView_Articles = findViewById(R.id.listView_ArticlesActivity_Articles);

        if(Network.checkNetwork(getApplicationContext()))
        {
            final NewsIn newsIn = new NewsIn();
            newsIn.execute();
            editText_Search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                  if(s.toString().equals("")){
                      Log.d(TAG, "beforeTextChanged: IN");
                      adapter = new ListArticlesAdapter(ArticlesActivity.this,dataArticle);
                      listView_Articles.setAdapter(adapter);
                      listView_Articles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                          @Override
                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                              Log.d(TAG, "onItemClick: "+ dataArticle.get(position).get(URL));
                              Intent intent = new Intent(ArticlesActivity.this, WebViewActivity.class);
                              intent.putExtra("sourceKey",sourceKey);
                              intent.putExtra("url",dataArticle.get(position).get(URL));
                              startActivity(intent);
                          }
                      });
                   }

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    searchArticles(s.toString());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }

        button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),SourcesActivity.class);
                startActivity(intent);
            }
        });




    }

    public void searchArticles(String textSearch){
        final ArrayList<HashMap<String, String>> dataSearch = new ArrayList<>();
        for(HashMap data:dataArticle){
            Log.d(TAG, "searchArticlesText: "+textSearch);
            HashMap<String,String> search = new HashMap<>();
            String title = data.get(TITLE).toString().toLowerCase();
            Log.d(TAG, "searchArticles: "+title);
            if(title.contains(textSearch.toLowerCase())){
                search.put(TITLE,data.get(TITLE).toString());
                search.put(URL,data.get(URL).toString());
                search.put(URLTOIMAGE,data.get(URLTOIMAGE).toString());
                Log.d(TAG, "searchArticles: "+search);
                dataSearch.add(search);
            }
        }
        adapter = new ListArticlesAdapter(ArticlesActivity.this,dataSearch);
        listView_Articles.setAdapter(adapter);
        listView_Articles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: "+ dataSearch.get(position).get(URL));
                Intent intent = new Intent(ArticlesActivity.this, WebViewActivity.class);
                intent.putExtra("sourceKey",sourceKey);
                intent.putExtra("url",dataSearch.get(position).get(URL));
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
                        map.put(URL,jsonObject.optString(URL).toString());
                        map.put(URLTOIMAGE, jsonObject.optString(URLTOIMAGE).toString());
                        dataArticle.add(map);
                    }
                }catch (JSONException e){
                    Toast.makeText(getApplicationContext(),"Error, Please Try Again Later",Toast.LENGTH_LONG);
                }

                adapter = new ListArticlesAdapter(ArticlesActivity.this,dataArticle);
                listView_Articles.setAdapter(adapter);
                listView_Articles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d(TAG, "onItemClick: "+ dataArticle.get(position).get(URL));
                        Intent intent = new Intent(ArticlesActivity.this, WebViewActivity.class);
                        intent.putExtra("sourceKey",sourceKey);
                        intent.putExtra("url",dataArticle.get(position).get(URL));
                        startActivity(intent);
                    }
                });

            }else {
                Toast.makeText(getApplicationContext(), "No News Available", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
