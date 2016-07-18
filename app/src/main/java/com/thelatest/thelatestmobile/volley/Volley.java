package com.thelatest.thelatestmobile.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Jesse on 11/10/15.
 */
public class Volley {

    private static Volley volley = null;
    private RequestQueue requestQueue;
    private Context applicationContext;

    private Volley(Context context) {
        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
        applicationContext = context;
    }

    public static Volley getVolley(Context context) {
        if (volley == null) {
            volley = new Volley(context);
        }

        return volley;
    }

    public void runRequest(int requestMethod, String url, JSONObject params, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(requestMethod, url, params, responseListener, errorListener);
        addToRequestQueue(request);
    }

    public void addToRequestQueue(Request<JSONObject> newRequest) {
        requestQueue.add(newRequest);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}