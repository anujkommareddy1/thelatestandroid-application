package com.thelatest.thelatestmobile.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.R;



public class BrowserFragment extends android.app.Fragment {
    private WebView mWebview;
    private String url;

    public BrowserFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_browser, container, false);



        url=getActivity().getIntent().getStringExtra(KeyConstants.NEWS_URL);


        WebView webview = (WebView) layout.findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new webClient());
        webview.loadUrl(url);
        return layout;
    }
    private class webClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;

        }

    }





}
