package com.thelatest.thelatestmobile;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.thelatest.thelatestmobile.Constants.KeyConstants;
import com.thelatest.thelatestmobile.Constants.NewsCategoryConstants;
import com.thelatest.thelatestmobile.fragments.BigNewsFragment;
import com.thelatest.thelatestmobile.fragments.CategoriesFragment;
import com.thelatest.thelatestmobile.fragments.MainFragment;
import com.thelatest.thelatestmobile.fragments.SmallNewsFragment;
import com.thelatest.thelatestmobile.fragments.SortFragment;
import com.thelatest.thelatestmobile.fragments.WriteFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FrameLayout mainView;
    private ScrollView slideMenuView;

    private Toolbar mainToolbar;
    private ImageView hamburgerImageView;
    private ImageView logoImageView;
    private FloatingActionButton floatingActionButton;

    // private Toolbar bottomToolbar;

    //private LinearLayout searchSquare;
    //private LinearLayout categoriesSquare;
    //private LinearLayout writeSquare;
    //private LinearLayout sortSquare;

    //private ImageView categoriesButton;
    //private ImageView writeButton;
    //private ImageView sortButton;

    private final int MAIN_PAGE = 0;
    private final int BIG_NEWS_PAGE = 1;
    private final int SMALL_NEWS_PAGE = 2;
    private final int CATEGORIES_PAGE = 3;
    private final int WRITE_PAGE = 4;
    private final int SORT_PAGE = 5;

    private final String bigCat = NewsCategoryConstants.getBigCategories()[0];
    private final String smallCat = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCat)[0];

    private Map<RelativeLayout, Boolean> mapMenus = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        this.mainView = (FrameLayout)findViewById(R.id.main_view);
        this.slideMenuView = (ScrollView)findViewById(R.id.slide_menu_scrollview);
        this.setUpSlideMenuView();

        this.mainToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        this.hamburgerImageView = (ImageView)findViewById(R.id.hamburger_imageview);
        this.logoImageView = (ImageView)findViewById(R.id.thelatest_logo_imageview);
        this.floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingButton);
        //this.bottomToolbar = (Toolbar)findViewById(R.id.toolbarBotom);
        //if (this.bottomToolbar != null) {
        //    this.bottomToolbar.setTag(bottomToolbar.getId(), false);
        //}

        //this.searchSquare = (LinearLayout)findViewById(R.id.searchSquare);
        //this.categoriesSquare = (LinearLayout)findViewById(R.id.categoriesSquare);
        //this.writeSquare = (LinearLayout)findViewById(R.id.writeSquare);
        //this.sortSquare = (LinearLayout)findViewById(R.id.sortSquare);

        //this.categoriesButton = (ImageView)findViewById(R.id.buttonCategories);
        //this.writeButton = (ImageView)findViewById(R.id.buttonWrite);
        //this.sortButton = (ImageView)findViewById(R.id.buttonSort);

        this.mainToolbar.setContentInsetsAbsolute(0, 0);

        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openSearchActivity(v);
                /*Animation bottomUp = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bottom_up);
                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
                floatingActionButton.setAnimation(animFadeOut);
                floatingActionButton.setVisibility(View.GONE);
                bottomToolbar.startAnimation(bottomUp);
                bottomToolbar.setVisibility(View.VISIBLE);
                bottomToolbar.setTag(bottomToolbar.getId(), true);*/
            }
        });

        /*this.searchSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchActivity(view);
            }
        });*/

        /*this.categoriesSquare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                displayFragmentCategories();
            }
        });*/

        /*this.writeSquare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                displayFragmentWrite();
            }
        });*/

        /*this.sortSquare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                displayFragmentSort();
            }
        });*/

        /*this.categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFragmentCategories();
            }
        });*/

        /*this.writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFragmentWrite();
            }
        });*/

        /*this.sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFragmentSort();
            }
        });*/

        this.hamburgerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(slideMenuView);
            }
        });

        this.logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(SMALL_NEWS_PAGE, bigCat, smallCat);
            }
        });

        if(savedInstanceState == null){
            this.displayView(SMALL_NEWS_PAGE, bigCat, smallCat);
        }
    }

    public void openSearchActivity(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        //this.bottomToolbar.setTag(bottomToolbar.getId(), false);
    }

    public void displayFragmentCategories() {
        displayFragment(CATEGORIES_PAGE, bigCat, smallCat);
    }

    public void displayFragmentWrite() {
        displayFragment(WRITE_PAGE, bigCat, smallCat);
    }

    public void displayFragmentSort() {
        displayFragment(SORT_PAGE, bigCat, smallCat);
    }

    private void displayFragment(int page, String bigCat, String smallCat) {
        displayView(page, bigCat, smallCat);
        downBottomBar();
    }

    public void downBottomBar() {
        /*if ((boolean)this.bottomToolbar.getTag(bottomToolbar.getId())) {
            Animation bottomDown = AnimationUtils.loadAnimation(getBaseContext(), R.anim.bottom_down);
            Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
            floatingActionButton.setAnimation(animFadeIn);
            floatingActionButton.setVisibility(View.VISIBLE);
            this.bottomToolbar.startAnimation(bottomDown);
            this.bottomToolbar.setVisibility(View.GONE);
            this.bottomToolbar.setTag(bottomToolbar.getId(), false);
        }*/
    }

    private void setUpSlideMenuView(){
        LinearLayout newsCategoryLayout = (LinearLayout)findViewById(R.id.slide_menu_view);

        for(String bigCategory : NewsCategoryConstants.getBigCategories()){
            View bigCategoryView = View.inflate(this, R.layout.template_slide_menu_big_category, null);

            LinearLayout bigCategoryLine = (LinearLayout)bigCategoryView.findViewById(R.id.big_category);
            LinearLayout smallCategoryView = (LinearLayout)bigCategoryView.findViewById(R.id.small_category);
            smallCategoryView.setVisibility(View.GONE);

            TextView bigCategoryTextView = (TextView)bigCategoryView.findViewById(R.id.big_category_textview);
            bigCategoryTextView.setText(bigCategory);

            RelativeLayout bigCategoryExpandRelativeLayout = (RelativeLayout)bigCategoryView.findViewById(R.id.big_category_expand);
            this.mapMenus.put(bigCategoryExpandRelativeLayout, false);
            bigCategoryExpandRelativeLayout.setOnClickListener(new CustomBigCategoryArrowClick(smallCategoryView, bigCategoryExpandRelativeLayout, this.mapMenus));

            String[] smallCategories = NewsCategoryConstants.getSmallCategoriesForBigCategory(bigCategory);

            if (smallCategories != null) {
                for (String smallCategory : smallCategories) {
                    View smallCategoryTemplate = View.inflate(this, R.layout.template_slide_menu_small_category, null);

                    TextView smallCategoryName1TextView = (TextView) smallCategoryTemplate.findViewById(R.id.small_category_1);
                    smallCategoryName1TextView.setText(smallCategory);
                    smallCategoryName1TextView.setOnClickListener(new CustomSmallCategoryClick(bigCategory, smallCategory));


                    smallCategoryView.addView(smallCategoryTemplate);
                }
            }

            bigCategoryLine.setZ(2);
            smallCategoryView.setZ(1);

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
            case CATEGORIES_PAGE:
                fragment = new CategoriesFragment();
                break;
            case WRITE_PAGE:
                fragment = new WriteFragment();
                break;
            case SORT_PAGE:
                fragment = new SortFragment();
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
        private RelativeLayout clickableArea;
        private Map<RelativeLayout, Boolean> mapMenus;

        public CustomBigCategoryArrowClick(View smallCategoryArea, RelativeLayout clickableArea, Map<RelativeLayout, Boolean> mapMenus){
            this.smallCategoryView = smallCategoryArea;
            this.isSmallCategoriesOpen = false;
            this.clickableArea = clickableArea;
            this.mapMenus = mapMenus;
        }

        @Override
        public void onClick(View v){

            for (Map.Entry<RelativeLayout, Boolean> entry : mapMenus.entrySet()) {
                if (entry.getValue() && entry.getKey() != v) {
                    mapMenus.put(entry.getKey(), false);
                    entry.getKey().callOnClick();
                }
            }

            Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_menu);
            Animation fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

            if (isSmallCategoriesOpen) {
                clickableArea.setClickable(false);
                smallCategoryView.startAnimation(fadeout);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        smallCategoryView.setVisibility(View.GONE);
                        clickableArea.setClickable(true);
                    }
                }, 500);
                mapMenus.put((RelativeLayout)v, false);
            } else {
                clickableArea.setClickable(false);
                smallCategoryView.startAnimation(slideDown);
                smallCategoryView.setVisibility(View.VISIBLE);
                clickableArea.setClickable(true);
                mapMenus.put((RelativeLayout)v, true);
            }

            isSmallCategoriesOpen = !isSmallCategoriesOpen;
            ImageView big_category_expand_button = (ImageView) v.findViewById(R.id.big_category_expand_button);
            if(isSmallCategoriesOpen) {
                big_category_expand_button.setImageResource(R.drawable.ic_dropup);

            } else {
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
