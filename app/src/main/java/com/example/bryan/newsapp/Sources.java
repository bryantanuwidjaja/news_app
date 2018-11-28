package com.example.bryan.newsapp;

public class Sources {
    private String source;
    private int drawable;
    private String sourceKey;
    private int drawableLogo;
    private String description;

    public Sources() {

    }

    public Sources(String source, int drawable, String sourceKey, int drawableLogo, String description) {
        this.source=source;
        this.drawable=drawable;
        this.sourceKey = sourceKey;
        this.drawableLogo = drawableLogo;
        this.description = description;

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

    public int getDrawableLogo() {
        return drawableLogo;
    }

    public void setDrawableLogo(int drawableLogo) {
        this.drawableLogo = drawableLogo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
