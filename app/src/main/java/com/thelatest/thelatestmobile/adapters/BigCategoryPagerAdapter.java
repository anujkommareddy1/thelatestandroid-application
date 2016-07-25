package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.SyncStateContract;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;
import com.thelatest.thelatestmobile.volley.Volley;
import com.thelatest.thelatestmobile.volley.VolleyConstants;
import com.thelatest.thelatestmobile.volley.request.CustomErrorListener;
import com.thelatest.thelatestmobile.volley.request.FeedsRequest;
import com.thelatest.thelatestmobile.volley.request.NewsFeedRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 7/12/2016.
 */
public class BigCategoryPagerAdapter extends PagerAdapter {

    private Context context;
    private String[] listBigCat;
    private String[] listBigCatEdited = new String[]{"TOP STORIES","ENTERTAINMENT","SPORTS", "PRODUCTS","EXTRA"};
    private ImageLoader imageLoader;

    public BigCategoryPagerAdapter(Context context){
        this.context = context;
        listBigCat = NewsCategoryConstants.getBigCategories();

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(context, R.layout.bigcategory_view_pager, null);
        final String smallCatFromBig[] = NewsCategoryConstants.getSmallCategoriesForBigCategory(NewsCategoryConstants.getBigCategories()[position]);
        TextView bigCatTV = (TextView) view.findViewById(R.id.big_cat_title_textview);
        bigCatTV.setText(listBigCatEdited[position]);
        SwipyRefreshLayout mSwipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipy_refresh_layout);

        final ArrayList<News> lNews = new ArrayList<News>();
        final BigCategoryListViewAdapter big;


        big = new BigCategoryListViewAdapter(view.getContext(),R.layout.bigcat_listview,lNews,smallCatFromBig,listBigCat[position]);
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyCustomObject(big,smallCatFromBig,mSwipyRefreshLayout,lNews));

        JSONObject params = new JSONObject();
            Volley volley = Volley.getVolley(context);

            for(int i=0;i<smallCatFromBig.length;i++) {


                try {
                    params.put("slug", smallCatFromBig[i]);
                    params.put("startIndex", 0);
                    params.put("endIndex", 0);
                } catch (Exception e) {
                    Log.e(getClass().toString(), "Error setting params for communication with server");
                    e.printStackTrace();
                }
                volley.runRequest(Request.Method.POST, VolleyConstants.PROD_URL + VolleyConstants.NEWS_FETCH_ROUTE,
                        params, new FeedsRequest(big,lNews,mSwipyRefreshLayout), new CustomErrorListener("ERROR"));

        }




        ListView mListView = (ListView) view.findViewById(R.id.view_pager_bigcat_listview);
        mListView.setAdapter(big);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return listBigCat.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    public class SwipyCustomObject implements SwipyRefreshLayout.OnRefreshListener{

        private String[] swipySmallCatFromBig;
        private BigCategoryListViewAdapter swipyBigCatePager;
        private SwipyRefreshLayout swipyRefreshLayout;
        private ArrayList<News> swipyNews;

        public SwipyCustomObject(BigCategoryListViewAdapter b, String[] s, SwipyRefreshLayout swipy, ArrayList<News> n){
            this.swipyBigCatePager = b;
            this.swipySmallCatFromBig = s;
            this.swipyRefreshLayout = swipy;
            this.swipyNews = n;
        }
        @Override
        public void onRefresh(SwipyRefreshLayoutDirection direction) {

         if(direction == SwipyRefreshLayoutDirection.TOP){
             swipyBigCatePager.clear();


             JSONObject params = new JSONObject();
             Volley volley = Volley.getVolley(context);

             for(int i=0;i<swipySmallCatFromBig.length;i++) {


                 try {
                     params.put("slug", swipySmallCatFromBig[i]);
                     params.put("startIndex", 0);
                     params.put("endIndex", 0);
                 } catch (Exception e) {
                     Log.e(getClass().toString(), "Error setting params for communication with server");
                     e.printStackTrace();
                 }
                 volley.runRequest(Request.Method.POST, VolleyConstants.PROD_URL + VolleyConstants.NEWS_FETCH_ROUTE,
                         params, new FeedsRequest(swipyBigCatePager,swipyNews,swipyRefreshLayout), new CustomErrorListener("ERROR"));
             }

         }else {
             swipyRefreshLayout.setRefreshing(false);

         }

         }


    }
}
