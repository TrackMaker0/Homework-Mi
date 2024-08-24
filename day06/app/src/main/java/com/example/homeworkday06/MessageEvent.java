package com.example.homeworkday06;

public class MessageEvent {
    public final int clickCount;

    public final boolean loveImage;

    public final boolean loveText;

    public MessageEvent(int clickCount) {
        this.clickCount = clickCount;
        this.loveImage=false;
        this.loveText=false;
    }

    public MessageEvent(boolean loveImage, boolean loveText) {
        this.clickCount = 0;
        this.loveImage=loveImage;
        this.loveText=loveText;
    }
}