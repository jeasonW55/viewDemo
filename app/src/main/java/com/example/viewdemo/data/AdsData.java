package com.example.viewdemo.data;

public class AdsData {
    private int currentTime;
    private String imageUrl;

    public AdsData(int currentTime) {
        this.currentTime = currentTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
