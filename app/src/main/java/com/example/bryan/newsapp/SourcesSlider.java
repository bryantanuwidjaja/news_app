package com.example.bryan.newsapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class SourcesSlider extends PagerAdapter {
    Activity context;
    LayoutInflater inflater;
    ArrayList<Sources> sourcesArrayList;

    public SourcesSlider(Activity context,ArrayList<Sources> sourcesArrayList) {
        this.context = context;
        this.sourcesArrayList = sourcesArrayList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider_news,container,false);
        ConstraintLayout layoutSlide = view.findViewById(R.id.sliderLayout);
        ImageView imageSlide = view.findViewById(R.id.imageView_NewsSlider);
        TextView textTitle = view.findViewById(R.id.textView_NewsSlider_Title);
        imageSlide.setImageResource(sourcesArrayList.get(position).getDrawableLogo());
        textTitle.setText(sourcesArrayList.get(position).getSource()+"\n"+sourcesArrayList.get(position).getDescription());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return sourcesArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==object);
    }
    @Override
    public void destroyItem (ViewGroup container, int position, Object object){
        container.removeView((ConstraintLayout)object);
    }
}
