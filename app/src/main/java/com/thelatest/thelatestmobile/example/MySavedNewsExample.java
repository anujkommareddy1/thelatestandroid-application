package com.thelatest.thelatestmobile.example;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/20/15.
 */
public class MySavedNewsExample {

    public static ArrayList<News> getMySavedNews(Context context){
        ArrayList<News> mySavedNews = new ArrayList<News>();

        Drawable newsPic = context.getResources().getDrawable(R.drawable.news);
        mySavedNews.add(new News(newsPic,
                "Heading into debate, Jeb Bush stumbles again",
                "WashPost",
                "Augest 5, 2015 21:16",
                "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));
        mySavedNews.add(new News(newsPic,
                "Heading into debate, Jeb Bush stumbles again",
                "WashPost",
                "Augest 5, 2015 21:16",
                "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));
        mySavedNews.add(new News(newsPic,
                "Heading into debate, Jeb Bush stumbles again",
                "WashPost",
                "Augest 5, 2015 21:16",
                "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));

        return mySavedNews;
    }
}
