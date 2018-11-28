package com.example.bryan.newsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SourcesActivity extends AppCompatActivity {
    private static final String TAG = "SourcesActivity";
    ArrayList<Sources> sourcesArrayList = new ArrayList<>();
    int position=0;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);

        Sources source1 = new Sources("Wired",R.drawable.ic_wired,"wired",R.drawable.wired,"WIRED is where tomorrow is realized.");
        sourcesArrayList.add(source1);
        Sources source2 = new Sources("TechCrunch",R.drawable.ic_techcrunch,"techcrunch",R.drawable.techcrunch,"Breaking technology news, analysis, and opinions from TechCrunch. Home to Disrupt, TC Sessions, and Startup Battlefield. Got a tip? tips@techcrunch.com");
        sourcesArrayList.add(source2);
        Sources source3 = new Sources("TechRadar",R.drawable.ic_techradar,"techradar",R.drawable.techradar,"Tech news, reviews and how-to guides - the best tech buying advice on the web. ");
        sourcesArrayList.add(source3);
        Sources source4 = new Sources("The Verge",R.drawable.ic_theverge,"the-verge",R.drawable.theverge,"The latest tech news about the world's best (and sometimes worst) hardware, apps, and much more. Verge Tech has the latest in what matters in technology daily.");
        sourcesArrayList.add(source4);
        Sources source5 = new Sources("Engadget",R.drawable.ic_engadget,"engadget",R.drawable.engadget,"Engadget is a multilingual technology blog network with daily coverage of gadgets and consumer electronics.");
        sourcesArrayList.add(source5);


        Button button_Favorite = findViewById(R.id.button_SourcesActivity_Favorite);
        Button button_Person = findViewById(R.id.button_SourcesActivity_Person);


        final ViewPager viewPager = findViewById(R.id.viewPager_SourceActivity_Featured);
        SourcesSlider viewPagerAdapter = new SourcesSlider(this,sourcesArrayList);
        ListView listView_Sources = findViewById(R.id.listView_SourceActivity_Sources);
        SourcesListAdapter listAdapter = new SourcesListAdapter(this,sourcesArrayList);
        listView_Sources.setAdapter(listAdapter);
        viewPager.setAdapter(viewPagerAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if(position == sourcesArrayList.size()-1){
                    position = 0;
                }
                viewPager.setCurrentItem(position++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },DELAY_MS,PERIOD_MS);


        button_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Dummy Button for Favorite Source", Toast.LENGTH_SHORT).show();
            }
        });

        button_Person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Dummy Button for Profile information", Toast.LENGTH_SHORT).show();
            }
        });


        listView_Sources.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(SourcesActivity.this, ArticlesActivity.class);
                intent.putExtra("sourceKey",sourcesArrayList.get(position).getSourceKey());
                intent.putExtra("logo",sourcesArrayList.get(position).getDrawable());
                intent.putExtra("title",sourcesArrayList.get(position).getSource());
                startActivity(intent);
            }
        });


    }

}
