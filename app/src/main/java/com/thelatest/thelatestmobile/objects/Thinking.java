package com.thelatest.thelatestmobile.objects;

import android.graphics.drawable.Drawable;

/**
 * Created by Jesse on 10/19/15.
 */
public class Thinking {

    private Drawable newsPic;
    private String title;
    private String summary;
    private String contents;
    private String category;
    private String date;

    public Thinking(Drawable newsPic, String title, String summary, String contents, String category, String date){
        this.newsPic = newsPic;
        this.title = title;
        this.summary = summary;
        this.contents = contents;
        this.category = category;
        this.date = date;
    }

    public void setNewsPic(Drawable newsPic){
        this.newsPic = newsPic;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }

    public void setContents(String contents){
        this.contents = contents;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Drawable getNewsPic(){
        return newsPic;
    }

    public String getTitle(){
        return title;
    }

    public String getSummary(){
        return summary;
    }

    public String getContents(){
        return contents;
    }

    public String getCategory(){
        return category;
    }

    public String getDate(){
        return date;
    }
}
