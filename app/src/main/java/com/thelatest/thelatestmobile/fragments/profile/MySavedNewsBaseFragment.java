package com.thelatest.thelatestmobile.fragments.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.adapters.ShortVersionNewsListAdapter;
import com.thelatest.thelatestmobile.example.MySavedNewsExample;
import com.thelatest.thelatestmobile.objects.News;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/13/15.
 */
public class MySavedNewsBaseFragment extends Fragment {

    private View rootView;
    private ListView listView;
    private ShortVersionNewsListAdapter adapter;

    public MySavedNewsBaseFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_profile_mysavednews, container, false);
        listView = (ListView)rootView.findViewById(R.id.my_saved_news_list);

        ArrayList<News> newsArr = getMySavedNewsFromServer();
        adapter = new ShortVersionNewsListAdapter(getActivity().getBaseContext(), R.layout.listviewitem_shortversionnews, newsArr);
        listView.setAdapter(adapter);

        return rootView;
    }

    private ArrayList<News> getMySavedNewsFromServer(){
        return MySavedNewsExample.getMySavedNews(rootView.getContext());
    }
}
