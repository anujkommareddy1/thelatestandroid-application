package com.thelatest.thelatestmobile.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.volley.Volley;
import com.thelatest.thelatestmobile.volley.request.CustomErrorListener;
import com.thelatest.thelatestmobile.volley.request.SignInRequest;

import org.json.JSONObject;

/**
 * Created by Jesse on 10/12/15.
 */
public class MainFragment extends Fragment {

    private View rootView;
    private TextView textView;

    private Volley volley;
    private String URL = "http://finance.yahoo.com/webservice/v1/symbols/allcurrencies/quote?format=json";

    public MainFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (TextView)rootView.findViewById(R.id.txtLabel);

        JSONObject params = new JSONObject();
        try {
            params.put("id", "1");
            params.put("name", "myname");
        }catch(Exception e){
            e.printStackTrace();
        }

        volley = Volley.getVolley(getActivity());
        volley.runRequest(Request.Method.GET, URL, null, new SignInRequest(textView), new CustomErrorListener("ERROR"));
        return rootView;
    }
}
