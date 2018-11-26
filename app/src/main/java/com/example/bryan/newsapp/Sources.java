package com.example.bryan.newsapp;

public class Sources {
    private String source;
    private int drawable;

    public Sources() {

    }
    public Sources(String source, int drawable) {
        this.source=source;
        this.drawable=drawable;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
