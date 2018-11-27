package com.example.bryan.newsapp;

public class Sources {
    private String source;
    private int drawable;
    private String sourceKey;

    public Sources() {

    }
    public Sources(String source, int drawable, String sourceKey) {
        this.source=source;
        this.drawable=drawable;
        this.sourceKey = sourceKey;
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

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }
}
