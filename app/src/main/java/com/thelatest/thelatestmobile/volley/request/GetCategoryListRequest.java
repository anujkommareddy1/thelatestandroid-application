package com.thelatest.thelatestmobile.volley.request;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by Logan on 11/24/15.
 */
public class GetCategoryListRequest implements Response.Listener<JSONObject> {

    TextView tv;

    public GetCategoryListRequest(TextView tv){
        this.tv = tv;
    }

    @Override
    public void onResponse(JSONObject response){
        Log.i(getClass().getName(), "Success!!!");
        Log.i(getClass().getName(), response.toString());
        tv.setText(response.toString());
    }
}
