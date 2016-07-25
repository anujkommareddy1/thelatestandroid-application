package com.thelatest.thelatestmobile.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.R;
import com.thelatest.thelatestmobile.objects.News;

/**
 * Created by Pedro on 7/12/2016.
 */
public class BigCategoryPagerAdapterBackUp extends PagerAdapter {

    private Context context;
    private String[] listBigCat;
    private String[] listBigCatEdited = new String[]{"TOP STORIES","ENTERTAINMENT","SPORTS", "PRODUCTS","EXTRA"};
    private RecyclerView mRecyclerView;

    public BigCategoryPagerAdapterBackUp(Context context){
        this.context = context;
        listBigCat = NewsCategoryConstants.getBigCategories();
    }



    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = View.inflate(context, R.layout.bigcategory_view_pager,null);
        final News[] news = new News[listBigCat.length];

        String smallCatFromBig[] = NewsCategoryConstants.getSmallCategoriesForBigCategory(NewsCategoryConstants.getBigCategories()[position]);
        TextView bigCatTV = (TextView) view.findViewById(R.id.big_cat_title_textview);
        bigCatTV.setText(listBigCatEdited[position]);


        SwipyRefreshLayout mSwipyRefreshLayout = (SwipyRefreshLayout)view.findViewById(R.id.swipy_refresh_layout);


       /* mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager glm = new GridLayoutManager(view.getContext(),1);
        glm.setAutoMeasureEnabled(true);
        BigCategoryRecyclerAdapter mAdapter = new BigCategoryRecyclerAdapter(smallCatFromBig,view.getContext());
        //  ArrayAdapter<String> Teste = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,smallCatFromBig);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(glm);
        mRecyclerView.setAdapter(mAdapter);*/

        ListView mListView = (ListView) view.findViewById(R.id.view_pager_bigcat_listview);
        mListView.setAdapter(new BigCategoryListViewAdapterBackUp(view.getContext(),smallCatFromBig));
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

        @Override
        public void onRefresh(SwipyRefreshLayoutDirection direction) {
            onRefresh(SwipyRefreshLayoutDirection.BOTTOM);
        }
    }
}
