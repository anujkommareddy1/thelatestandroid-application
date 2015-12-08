package com.thelatest.thelatestmobile.objects;

import android.graphics.drawable.Drawable;

/**
 * Created by Jesse on 10/17/15.
 */
public class User {

    int ID;
    String name;
    Drawable pic;

    public User(int ID, String name, Drawable pic){
        this.ID = ID;
        this.name = name;
        this.pic = pic;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public Drawable getPic(){
        return pic;
    }
}
