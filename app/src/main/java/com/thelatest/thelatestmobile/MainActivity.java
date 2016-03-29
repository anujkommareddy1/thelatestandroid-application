package com.thelatest.thelatestmobile;

import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.fragments.BigNewsFragment;
import com.thelatest.thelatestmobile.fragments.MainFragment;
import com.thelatest.thelatestmobile.fragments.SmallNewsFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FrameLayout mainView;
    private ScrollView slideMenuView;

    private Toolbar mainToolbar;
    private ImageView hamburgerImageView;
    private ImageView logoImageView;

    private final int MAIN_PAGE = 0;
    private final int BIG_NEWS_PAGE = 1;
    private final int SMALL_NEWS_PAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        mainView = (FrameLayout)findViewById(R.id.main_view);
        slideMenuView = (ScrollView)findViewById(R.id.slide_menu_scrollview);
        setUpSlideMenuView();

        mainToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        hamburgerImageView = (ImageView)findViewById(R.id.hamburger_imageview);
        logoImageView = (ImageView)findViewById(R.id.thelatest_logo_imageview);

        mainToolbar.setContentInsetsAbsolute(0, 0);

        final String bigCat = NewsCategoryConstants.getBigCategories()[0];
        final String smallCat = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCat)[0];


        hamburgerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(slideMenuView);
            }
        });

        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(SMALL_NEWS_PAGE, bigCat, smallCat);
            }
        });

        if(savedInstanceState == null){
            displayView(SMALL_NEWS_PAGE, bigCat, smallCat);
        }
    }

    private void setUpSlideMenuView(){
        LinearLayout newsCategoryLayout = (LinearLayout)findViewById(R.id.slide_menu_view);

        for(String bigCategory : NewsCategoryConstants.getBigCategories()){
            View bigCategoryView = View.inflate(this, R.layout.template_slide_menu_big_category, null);

            LinearLayout smallCategoryView = (LinearLayout)bigCategoryView.findViewById(R.id.small_category);
            smallCategoryView.setVisibility(View.GONE);

            TextView bigCategoryTextView = (TextView)bigCategoryView.findViewById(R.id.big_category_textview);
            bigCategoryTextView.setText(bigCategory);

            RelativeLayout bigCategoryExpandRelativeLayout = (RelativeLayout)bigCategoryView.findViewById(R.id.big_category_expand);
            bigCategoryExpandRelativeLayout.setOnClickListener(new CustomBigCategoryArrowClick(smallCategoryView));

            String[] smallCategories = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);

            for (int i = 0; i < smallCategories.length; i++) {
                View smallCategoryTemplate = View.inflate(this, R.layout.template_slide_menu_small_category, null);

                TextView smallCategoryName1TextView = (TextView) smallCategoryTemplate.findViewById(R.id.small_category_1);
                smallCategoryName1TextView.setText(smallCategories[i]);
                smallCategoryName1TextView.setOnClickListener(new CustomSmallCategoryClick(bigCategory, smallCategories[i]));


                smallCategoryView.addView(smallCategoryTemplate);
            }

            newsCategoryLayout.addView(bigCategoryView);
        }
    }

    private void displayView(int pageType, String bigCategory, String smallCategory){
        Fragment fragment = null;
        Bundle arguments = null;
        android.app.FragmentManager fragmentManager = getFragmentManager();

        switch(pageType){
            case MAIN_PAGE:
                fragment = new MainFragment();
                break;

            case BIG_NEWS_PAGE:
                fragment = new BigNewsFragment();
                arguments = new Bundle();
                arguments.putString(KeyConstants.BIG_CATEGORY, bigCategory);
                fragment.setArguments(arguments);
                break;

            case SMALL_NEWS_PAGE:
                fragment = new SmallNewsFragment();
                arguments = new Bundle();
                arguments.putString(KeyConstants.BIG_CATEGORY, bigCategory);
                arguments.putString(KeyConstants.SMALL_CATEGORY, smallCategory);
                fragment.setArguments(arguments);
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_view, fragment)
                .commit();
    }

    private class CustomBigCategoryTextClick implements View.OnClickListener{
        private String bigCategoryString;

        public CustomBigCategoryTextClick(String bigCategoryString){
            this.bigCategoryString = bigCategoryString;
        }

        @Override
        public void onClick(View v){
            displayView(BIG_NEWS_PAGE, bigCategoryString, null);
            drawerLayout.closeDrawer(slideMenuView);
        }
    }

    private class CustomBigCategoryArrowClick implements View.OnClickListener{
        private View smallCategoryView;
        private boolean isSmallCategoriesOpen;

        public CustomBigCategoryArrowClick(View smallCategoryArea){
            this.smallCategoryView = smallCategoryArea;
            isSmallCategoriesOpen = false;
        }

        @Override
        public void onClick(View v){
            smallCategoryView.setVisibility( isSmallCategoriesOpen ? View.GONE : View.VISIBLE );
            isSmallCategoriesOpen = !isSmallCategoriesOpen;
            ImageView big_category_expand_button = (ImageView) v.findViewById(R.id.big_category_expand_button);
            if(isSmallCategoriesOpen) {
                big_category_expand_button.setImageResource(R.drawable.ic_upwards);

            }else
            {
                big_category_expand_button.setImageResource(R.drawable.ic_dropdown);

            }
        }
    }

    private class CustomSmallCategoryClick implements View.OnClickListener{
        private String bigCategoryString;
        private String smallCategoryString;

        public CustomSmallCategoryClick(String bigCategoryString, String smallCategoryString){
            this.bigCategoryString = bigCategoryString;
            this.smallCategoryString = smallCategoryString;
        }

        @Override
        public void onClick(View v){
            displayView(SMALL_NEWS_PAGE, bigCategoryString, smallCategoryString);
            drawerLayout.closeDrawer(slideMenuView);
        }
    }
}
