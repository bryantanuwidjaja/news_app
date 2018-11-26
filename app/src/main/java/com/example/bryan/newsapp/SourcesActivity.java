package com.example.bryan.newsapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SourcesActivity extends AppCompatActivity {
    private static final String TAG = "SourcesActivity";
    ArrayList<Sources> sourcesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);

        Sources source1 = new Sources("ESPN",R.drawable.ic_homelogo);
        sourcesArrayList.add(source1);
        Sources source2 = new Sources("CNN",R.drawable.ic_homelogo);
        sourcesArrayList.add(source2);
        Sources source3 = new Sources("MTV News",R.drawable.ic_homelogo);
        sourcesArrayList.add(source3);
        Sources source4 = new Sources("Reddit /r/",R.drawable.ic_homelogo);
        sourcesArrayList.add(source4);
        Sources source5 = new Sources("Polygon",R.drawable.ic_homelogo);
        sourcesArrayList.add(source5);
        Sources source6 = new Sources("NFL News",R.drawable.ic_homelogo);
        sourcesArrayList.add(source6);
        Sources source7 = new Sources("Newsweek",R.drawable.ic_homelogo);
        sourcesArrayList.add(source7);

        ListView listView = findViewById(R.id.listView_SourceActivity_Sources);
        SourcesListAdapter listAdapter = new SourcesListAdapter(this,sourcesArrayList);
        listView.setAdapter(listAdapter);

    }

}
