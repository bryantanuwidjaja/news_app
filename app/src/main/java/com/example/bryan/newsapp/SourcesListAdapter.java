package com.example.bryan.newsapp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SourcesListAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<Sources> sourcesList;

    public SourcesListAdapter(Activity context, ArrayList<Sources>sourcesList) {
        super(context,R.layout.sources_layout,sourcesList);
        this.context=context;
        this.sourcesList=sourcesList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewSource = inflater.inflate(R.layout.sources_layout,null,true);
        ImageView imageView_sourceLogo = listViewSource.findViewById(R.id.imageView_SourceLayout_sourceLogo);
        TextView textView_sourceTitle = listViewSource.findViewById(R.id.textView_SourceLayout_sourceTitle);

        Sources sources = sourcesList.get(position);
        textView_sourceTitle.setText(sources.getSource());
        imageView_sourceLogo.setImageResource(sources.getDrawable());
        return listViewSource;
    }

}
