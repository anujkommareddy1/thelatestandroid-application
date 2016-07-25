package com.thelatest.thelatestmobile.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.Request;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.MainActivity;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.adapters.BigCategoryRecyclerAdapter;
import com.thelatest.thelatestmobile.adapters.SmallNewsListViewAdapter;
import com.thelatest.thelatestmobile.adapters.TileAdapter;
import com.thelatest.thelatestmobile.adapters.TileAdapterRecycle;
import com.thelatest.thelatestmobile.objects.News;
import com.thelatest.thelatestmobile.volley.Volley;
import com.thelatest.thelatestmobile.volley.VolleyConstants;
import com.thelatest.thelatestmobile.volley.request.CustomErrorListener;
import com.thelatest.thelatestmobile.volley.request.NewsFeedRequest;

import org.json.JSONObject;

import java.util.ArrayList;


public class TileFragment extends android.support.v4.app.Fragment {

    private RecyclerView mRecyclerView;

    public TileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        final View rootView = inflater.inflate(R.layout.tile_grid_layout,container, false);
        //View rootView = inflater.inflate(R.layout.fragment_tile,container, false);
       final String s[] = NewsCategoryConstants.getBigCategories();
      // String s[] = {"entertainment", "sports", "products", "extra"};
        String s2[] = {"topstories"};



        //ArrayAdapter<String> adap = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_list_item_1,s);
        //ListView listView = (ListView)rootView.findViewById(R.id.listview);
        //listView.setAdapter(smallNewsListViewAdapter);
        //TileAdapter tl = new TileAdapter(rootView.getContext(),R.layout.tile_grid_layout,new ArrayList<News>());

        //ArrayAdapter<String> adap = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_list_item_1,s);
        SmallNewsListViewAdapter smallNewsListViewAdapter = new SmallNewsListViewAdapter(rootView.getContext(),R.layout.listviewitem_smallnews, new ArrayList<News>());
      //  SwipyRefreshLayout mSwipyRefreshLayout = (SwipyRefreshLayout)rootView.findViewById(R.id.swipy_refresh_layout);


    //   String lol[] = NewsCategoryConstants.getBigCategories();


       // GridView gv = (GridView) rootView.findViewById(R.id.gv);
       //ListView lv = (ListView) rootView.findViewById(R.id.listview2);

        //lv.setAdapter(new TileAdapter(rootView.getContext(),R.layout.listviewitem_smallnews,s2));
        //gv.setAdapter(new TileAdapter(rootView.getContext(),R.layout.listviewitem_smallnews,s));
       // gv.setAdapter(smallNewsListViewAdapter);
        //gv.setAdapter(snlv);
        //gv.setAdapter(new TileAdapterRecycle(s,rootView.getContext(),new GridLayoutManager()));

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        GridLayoutManager glm = new GridLayoutManager(rootView.getContext(),1);
       // glm.setAutoMeasureEnabled(true);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        TileAdapterRecycle mAdapter = new TileAdapterRecycle(s,rootView.getContext());
        //BigCategoryRecyclerAdapter mAdapter = new BigCategoryRecyclerAdapter(s,rootView.getContext());
        mAdapter.setOnItemClickListener(new TileAdapterRecycle.ClickListener(){
            Bundle arguments = new Bundle();
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(rootView.getContext(), MainActivity.class);
                intent.putExtra("Position", s[position]);

                rootView.getContext().startActivity(intent);

        }});

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(glm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);


        return rootView;

    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
