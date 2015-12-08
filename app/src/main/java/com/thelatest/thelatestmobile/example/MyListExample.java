package com.thelatest.thelatestmobile.example;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/20/15.
 */
public class MyListExample {

    public static ArrayList<String> getMyCities(){
        ArrayList<String> cities = new ArrayList<String>();

        cities.add("AB");
        cities.add("CDE");
        cities.add("EFGH");

        return cities;
    }

    public static ArrayList<News> getNewsForCity(String city, Context context){
        ArrayList<News> myLocalNews = new ArrayList<News>();
        Drawable newsPic = null;

        switch(city){
            case "AB":

                newsPic = context.getResources().getDrawable(R.drawable.news);
                myLocalNews.add(new News(newsPic,
                        "AB 1",
                        "WashPost",
                        "Augest 5, 2015 21:16",
                        "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));
                myLocalNews.add(new News(newsPic,
                        "AB 2",
                        "WashPost",
                        "Augest 5, 2015 21:16",
                        "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));
                myLocalNews.add(new News(newsPic,
                        "AB 3",
                        "WashPost",
                        "Augest 5, 2015 21:16",
                        "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));

                break;
            case "CDE":

                newsPic = context.getResources().getDrawable(R.drawable.news);
                myLocalNews.add(new News(newsPic,
                        "CDE 1",
                        "WashPost",
                        "Augest 5, 2015 21:16",
                        "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));
                myLocalNews.add(new News(newsPic,
                        "CDE 2",
                        "WashPost",
                        "Augest 5, 2015 21:16",
                        "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));
                myLocalNews.add(new News(newsPic,
                        "CDE 3",
                        "WashPost",
                        "Augest 5, 2015 21:16",
                        "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));

                break;
            case "EFGH":

                newsPic = context.getResources().getDrawable(R.drawable.news);
                myLocalNews.add(new News(newsPic,
                        "EFGH 1",
                        "WashPost",
                        "Augest 5, 2015 21:16",
                        "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));
                myLocalNews.add(new News(newsPic,
                        "EFGH 2",
                        "WashPost",
                        "Augest 5, 2015 21:16",
                        "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));
                myLocalNews.add(new News(newsPic,
                        "EFGH 3",
                        "WashPost",
                        "Augest 5, 2015 21:16",
                        "Comments about women's health, the Islamic State and his dad highlight the drawback of his off-the-cuff style."));

                break;
        }

        return myLocalNews;
    }
}
