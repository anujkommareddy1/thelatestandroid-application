package com.thelatest.thelatestmobile.fragments.profile.mylocal;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.adapters.ShortVersionNewsListAdapter;
import com.thelatest.thelatestmobile.example.MyListExample;
import com.thelatest.thelatestmobile.objects.News;

import java.util.ArrayList;

/**
 * Created by Jesse on 10/20/15.
 */
public class MyListFragment extends Fragment {

    private View rootView;
    private ListView listView;
    private LinearLayout horizontalScrollViewUsers;

    public MyListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_profile_mylocal_mylist, container, false);
        listView = (ListView)rootView.findViewById(R.id.listview);
        horizontalScrollViewUsers = (LinearLayout)rootView.findViewById(R.id.horizontalscrollview_layout);

        drawHorizontalCityList();

        return rootView;
    }

    private void drawHorizontalCityList(){
        ArrayList<String> myCities = getMyCitiesFromServer();

        for(String city : myCities) {
            TextView textView = new TextView(getActivity());
            textView.setText(city);
            textView.setBackgroundResource(R.drawable.bg_oval_mylocal);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setOnClickListener(new CityClick(city));

            horizontalScrollViewUsers.addView(textView);
        }
    }

    private class CityClick implements View.OnClickListener{
        String city;

        public CityClick(String city){
            this.city = city;
        }

        @Override
        public void onClick(View v){
            ArrayList<News> newsArr = getNewsForCityChoice(city);
            ShortVersionNewsListAdapter adapter = new ShortVersionNewsListAdapter(getActivity().getBaseContext(), R.layout.listviewitem_shortversionnews, newsArr);
            listView.setAdapter(adapter);
        }
    }

    private ArrayList<String> getMyCitiesFromServer(){
        return MyListExample.getMyCities();
    }

    private ArrayList<News> getNewsForCityChoice(String city){
        return MyListExample.getNewsForCity(city, rootView.getContext());
    }
}
