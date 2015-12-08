package com.thelatest.thelatestmobile.volley.request;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Jesse on 11/10/15.
 */
public class CustomErrorListener implements Response.ErrorListener {

    String listeningType;

    public CustomErrorListener(String listeningType){
        this.listeningType = listeningType;
    }

    @Override
    public void onErrorResponse(VolleyError error){
        Log.i(this.listeningType, "Something wrong while listening Volley");
        error.printStackTrace();
    }
}
