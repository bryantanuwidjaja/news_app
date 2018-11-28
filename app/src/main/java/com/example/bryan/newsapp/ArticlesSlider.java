package com.example.bryan.newsapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticlesSlider extends PagerAdapter {
    private static final String TAG = "ArticlesSlider";
    Activity context;
    LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> dataArticle;


    public ArticlesSlider(Activity context, ArrayList<HashMap<String, String>> dataArticle) {
        this.context = context;
        this.dataArticle = dataArticle;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider_articles,container,false);
        ConstraintLayout layoutSlide = view.findViewById(R.id.sliderLayout_ArticlesSlider);
        ImageView imageSlide = view.findViewById(R.id.imageView_ArticlesSlider);
        TextView textTitle = view.findViewById(R.id.textView_ArticlesSlider_Title);
        imageSlide.setId(position);
        textTitle.setId(position);
        HashMap<String,String>data;
        data = dataArticle.get(position);
        try{
            textTitle.setText(data.get(ArticlesActivity.TITLE));
            Picasso.with(context).load(data.get(ArticlesActivity.URLTOIMAGE).toString())
                    .resize(1600,900)
                    .into(imageSlide);
            container.addView(view);
        }catch (Exception e){}

        Log.d(TAG, "instantiateItem: TITLE"+textTitle);
        return view;
    }

    @Override
    public int getCount() {
        return dataArticle.size();
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
