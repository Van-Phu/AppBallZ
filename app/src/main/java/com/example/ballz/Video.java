package com.example.ballz;

public class Video {
    String image, videoTitle, streamUrl;

    public Video(String image, String videoTitle) {
        this.image = image;
        this.videoTitle = videoTitle;
        this.streamUrl = streamUrl;
    }

//    public static String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }
}




