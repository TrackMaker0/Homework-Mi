package com.example.homeworkday06;


public class ContentItem {

    private String imageUrl;

    private String text;

    private boolean loveImage;

    private boolean loveText;

    public ContentItem() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLoveImage() {
        return loveImage;
    }

    public void setLoveImage(boolean loveImage) {
        this.loveImage = loveImage;
    }

    public boolean isLoveText() {
        return loveText;
    }

    public void setLoveText(boolean loveText) {
        this.loveText = loveText;
    }
}
