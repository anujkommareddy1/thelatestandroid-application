package com.thelatest.thelatestmobile.objects;

import android.graphics.drawable.Drawable;

/**
 * Created by Jesse on 10/18/15.
 */
public class News {

    private Drawable newsPic;
    private String newsHead;
    private String newsFrom;
    private String newsDate;
    private String newsContent;

    public News(Drawable newsPic, String newsHead, String newsFrom, String newsDate, String newsContent){
        this.newsPic = newsPic;
        this.newsHead = newsHead;
        this.newsFrom = newsFrom;
        this.newsDate = newsDate;
        this.newsContent = newsContent;
    }

    public void setNewsPic(Drawable newsPic){
        this.newsPic = newsPic;
    }

    public void setNewsHead(String newsHead){
        this.newsHead = newsHead;
    }

    public void setNewsFrom(String newsFrom){
        this.newsFrom = newsFrom;
    }

    public void setNewsDate(String newsDate){
        this.newsDate = newsDate;
    }

    public void setNewsContent(String newsContent){
        this.newsContent = newsContent;
    }

    public Drawable getNewsPic(){
        return newsPic;
    }

    public String getNewsHead(){
        return newsHead;
    }

    public String getNewsFrom(){
        return newsFrom;
    }

    public String getNewsDate(){
        return newsDate;
    }

    public String getNewsContent(){
        return newsContent;
    }
}
