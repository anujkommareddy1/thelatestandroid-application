package com.thelatest.thelatestmobile;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.fragments.MainFragment;
import com.thelatest.thelatestmobile.fragments.SignInFragment;
import com.thelatest.thelatestmobile.fragments.news.NewsPanelFragment;
import com.thelatest.thelatestmobile.fragments.profile.ProfileFragment;
import com.thelatest.thelatestmobile.volley.Volley;

public class MainActivity extends AppCompatActivity {

    // android.support.v4.widget.DrawerLayout in activity_main.xml
    private DrawerLayout drawerLayout;

    // FrameLayout in activity_main.xml
    private FrameLayout mainView;

    // ScrollView in activity_main.xml
    private ScrollView slideMenuView;

    // Main Toolbar
    private Toolbar mainToolbar;
    private ImageView hamburgerImageView;
    private ImageView logoImageView;
    private ImageView searchImageView;

    // Search Toolbar
    private Toolbar searchToolbar;
    private ImageView backArrowImageView;
    private EditText searchEditText;
    private PopupWindow popupWindow;

    private final int MAIN_PAGE = 0;
    private final int SIGNIN_PAGE = 1;
    private final int PROFILE_PAGE = 2;
    private final int NEWS_PANEL_PAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerListener(new CustomDrawerListener());
        mainView = (FrameLayout)findViewById(R.id.main_view);
        slideMenuView = (ScrollView)findViewById(R.id.slide_menu_view);
        setUpSlideMenuView();

        // MAIN TOOLBAR ----------------------------------------------------------------------------
        mainToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        hamburgerImageView = (ImageView)findViewById(R.id.ic_hamburger);
        logoImageView = (ImageView)findViewById(R.id.ic_logo);
        searchImageView = (ImageView)findViewById(R.id.ic_exclamation);

        mainToolbar.setContentInsetsAbsolute(0, 0);
        hamburgerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(slideMenuView);
            }
        });
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(MAIN_PAGE, null, null);
            }
        });
        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchToolbar.setVisibility(View.VISIBLE);
                mainToolbar.setVisibility(View.INVISIBLE);
                popupWindow.showAtLocation(findViewById(R.id.main_view), Gravity.CENTER_HORIZONTAL, 10, 10);
            }
        });

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.dialog_search_history, null, false);

        popupWindow = new PopupWindow(popupView, 200, 200, true);


        // SEARCH TOOLBAR --------------------------------------------------------------------------
        searchToolbar = (Toolbar)findViewById(R.id.search_toolbar);
        backArrowImageView = (ImageView)findViewById(R.id.back_arrow);
        searchEditText = (EditText)findViewById(R.id.search_bar);

        searchToolbar.setContentInsetsAbsolute(0, 0);
        backArrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainToolbar.setVisibility(View.VISIBLE);
                searchToolbar.setVisibility(View.INVISIBLE);
            }
        });

        // -----------------------------------------------------------------------------------------
        if(savedInstanceState == null){
            displayView(MAIN_PAGE, null, null);
        }
    }

    private void setUpSlideMenuView(){
        LinearLayout newsCategoryLayout = (LinearLayout)findViewById(R.id.widget_slide_menu);

        ImageView signInImageView = (ImageView)findViewById(R.id.sign_in_imageview);
        signInImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(SIGNIN_PAGE, null, null);
                drawerLayout.closeDrawer(slideMenuView);
            }
        });

        for(String bigCategory : NewsCategoryConstants.getBigCategories()){
            View bigCategoryView = View.inflate(this, R.layout.template_slide_menu_big_category, null);

            LinearLayout smallCategoryView = (LinearLayout)bigCategoryView.findViewById(R.id.small_category);
            smallCategoryView.setVisibility(View.GONE);

            ((TextView)bigCategoryView.findViewById(R.id.big_category_name)).setText(bigCategory);
            (bigCategoryView.findViewById(R.id.big_category_expand)).setOnClickListener(new CustomBigCategoryClick(smallCategoryView));

            String[] smallCategories = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);

            for (int i = 0; i < smallCategories.length; i += 2) {
                View smallCategoryTemplate = View.inflate(this, R.layout.template_slide_menu_small_category, null);

                TextView smallCategoryName1TextView = (TextView)smallCategoryTemplate.findViewById(R.id.small_category_1);
                smallCategoryName1TextView.setText(smallCategories[i]);
                smallCategoryName1TextView.setOnClickListener(new CustomSmallCategoryClick(bigCategory, smallCategories[i]));

                TextView smallCategoryName2TextView = (TextView)smallCategoryTemplate.findViewById(R.id.small_category_2);
                try {
                    smallCategoryName2TextView.setText(smallCategories[i+1]);
                    smallCategoryName2TextView.setOnClickListener(new CustomSmallCategoryClick(bigCategory, smallCategories[i+1]));
                } catch (ArrayIndexOutOfBoundsException e) {    // If no more item to display to the right, it is empty
                    smallCategoryName2TextView.setText("");
                }

                smallCategoryView.addView(smallCategoryTemplate);
            }

            newsCategoryLayout.addView(bigCategoryView);
        }
    }

    private void displayView(int pageType, String bigCategory, String smallCategory){
        Fragment fragment = null;

        switch(pageType){
            case MAIN_PAGE:
                fragment = new MainFragment();
                break;

            case SIGNIN_PAGE:
                fragment = new SignInFragment();
                break;

            case PROFILE_PAGE:
                fragment = new ProfileFragment();
                break;

            case NEWS_PANEL_PAGE:
                fragment = new NewsPanelFragment();
                Bundle arguments = new Bundle();
                arguments.putString(KeyConstants.BIG_CATEGORY, bigCategory);
                arguments.putString(KeyConstants.SMALL_CATEGORY, smallCategory);
                fragment.setArguments(arguments);
                break;
        }

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_view, fragment).commit();
    }

    private class CustomBigCategoryClick implements View.OnClickListener{
        private boolean isSmallCategoriesOpen;
        private View smallCategoryArea;

        public CustomBigCategoryClick(View smallCategoryArea){
            this.smallCategoryArea = smallCategoryArea;
            isSmallCategoriesOpen = false;
        }

        @Override
        public void onClick(View v){
            smallCategoryArea.setVisibility( isSmallCategoriesOpen ? View.GONE : View.VISIBLE );
            isSmallCategoriesOpen = !isSmallCategoriesOpen;
        }
    }

    private class CustomSmallCategoryClick implements View.OnClickListener{
        private String bigCategory;
        private String smallCategory;

        public CustomSmallCategoryClick(String bigCategory, String smallCategory){
            this.bigCategory = bigCategory;
            this.smallCategory = smallCategory;
        }

        @Override
        public void onClick(View v){
            displayView(NEWS_PANEL_PAGE, bigCategory, smallCategory);
            drawerLayout.closeDrawer(slideMenuView);
        }
    }

    private class CustomDrawerListener implements DrawerLayout.DrawerListener{
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset){
            mainView.setTranslationX(slideOffset * drawerView.getWidth());
        }

        public void onDrawerOpened(View drawerView){}
        public void onDrawerClosed(View drawerView){}
        public void onDrawerStateChanged(int newState){}
    }
}
