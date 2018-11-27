package com.example.bryan.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ListArticlesAdapter extends BaseAdapter{
    private Activity context;
    private ArrayList<HashMap<String,String>> dataArticle;

    public ListArticlesAdapter(Activity context, ArrayList<HashMap<String,String>> dataArticle){
        this.context = context;
        this.dataArticle = dataArticle;
    }

    @Override
    public int getCount() {
        return dataArticle.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewArticle = inflater.inflate(R.layout.articles_layout,null,true);
        ImageView imageView_ArticleImage = listViewArticle.findViewById(R.id.imageView_ArticlesLayout_ArticleImage);
        TextView textView_ArticleTitle = listViewArticle.findViewById(R.id.textView_ArticlesLayout_ArticleTitle);
        TextView textView_ArticleDescription = listViewArticle.findViewById(R.id.textView_ArticleLayout_ArticleDescription);
        imageView_ArticleImage.setId(position);
        textView_ArticleTitle.setId(position);
        textView_ArticleDescription.setId(position);

        HashMap<String,String> data;
        data = dataArticle.get(position);

        try{
            textView_ArticleTitle.setText(data.get(ArticlesActivity.TITLE));
            textView_ArticleDescription.setText(data.get(ArticlesActivity.DESCRIPTION));
            Picasso.with(context).load(data.get(ArticlesActivity.URLTOIMAGE).toString())
                    .resize(350,200)
                    .into(imageView_ArticleImage);

        }catch (Exception e){}

        return listViewArticle;
    }
}
