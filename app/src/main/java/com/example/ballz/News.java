package com.example.ballz;

public class News {
    String image, title, urlNews;

    public News(String image, String title, String urlNews) {
        this.image = image;
        this.title = title;
        this.urlNews = urlNews;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlNews() {
        return urlNews;
    }

    public void setUrlNews(String urlNews) {
        this.urlNews = urlNews;
    }
}


