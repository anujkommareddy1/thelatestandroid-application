package com.thelatest.thelatestmobile.volley.request;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by Jesse on 11/10/15.
 */
public class SignInRequest implements Response.Listener<JSONObject> {

    TextView tv;

    public SignInRequest(TextView textView){
        this.tv = tv;
    }

    @Override
    public void onResponse(JSONObject response){
        Log.i(getClass().getName(), "Success!!!");
        Log.i(getClass().getName(), response.toString());
        tv.setText(response.toString());
    }
}
