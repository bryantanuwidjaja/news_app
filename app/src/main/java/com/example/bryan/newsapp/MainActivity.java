package com.example.bryan.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.transition);
        ImageView imageView_Logo = findViewById(R.id.imageView_MainActivity_Logo);
        TextView textView_Title = findViewById(R.id.textView_MainActivity_Title);
        imageView_Logo.startAnimation(animation);
        textView_Title.startAnimation(animation);
        final Intent i = new Intent(this,SourcesActivity.class);
        Thread timer = new Thread()
        {
            public void run ()
            {
                try
                {
                    sleep(5000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
