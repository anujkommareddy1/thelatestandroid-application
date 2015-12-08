package com.thelatest.thelatestmobile.Constants;

/**
 * Created by Jesse on 10/20/15.
 */
public class NewsCategoryConstants {

    // BIG CATEGORIES
    public static final String TOP_STORIES = "Top Stories";
    public static final String ENTERTAINMENT = "Entertainment";
    public static final String SPORTS = "Sports";
    public static final String PRODUCTS = "Products";
    public static final String EXTRA = "Extra";

    // TOP STORIES CATEGORY
    public static final String US = "US";
    public static final String WORLD = "World";
    public static final String POLITICS = "Politics";
    public static final String BUSINESS = "Business";
    public static final String TECH = "Tech";
    public static final String HEALTH = "Health";
    public static final String SCIENCE_ENVIRONMENT = "Sci & Env";
    public static final String MEDIA = "Media";
    public static final String POLLS = "Polls";

    // ENTERTAINMENT CATEGORY
    public static final String CELEBRITY = "Celebrity";
    public static final String MOVIES = "Movies";
    public static final String TV = "TV";
    public static final String MUSIC = "Music";
    public static final String ARTS = "Arts";
    public static final String HUMOR = "Humor";
    public static final String VIRAL = "Viral";
    public static final String TRAILERS = "Trailers";
    public static final String COOL_ADS = "Cool Ads";

    // SPORTS CATEGORY
    public static final String FOOTBALL = "Football";
    public static final String BASEBALL = "Baseball";
    public static final String BASKETBALL = "Basketball";
    public static final String SOCCER = "Soccer";
    public static final String HOCKEY = "Hockey";
    public static final String TENNIS = "Tennis";
    public static final String GOLF = "Golf";
    public static final String WINTER_SPORTS = "Winter Sports";
    public static final String EXTREME_SPORTS = "Extreme Sports";
    public static final String MORE_SPORTS = "More Sports";

    // PRODUCTS CATEGORY
    public static final String ELECTRONICS = "Electronics";
    public static final String FASHION = "Fashion";
    public static final String FITNESS = "Fitness";
    public static final String FOOD_DRINK = "Food & Drink";
    public static final String HOME_GARDEN = "Home";
    public static final String AUTO = "Auto";
    public static final String TRAVEL = "Travel";
    public static final String GAMES = "Games";
    public static final String BOOKS = "Books";
    public static final String PERSONAL_FINANCE = "Personal Finance";

    // EXTRA CATEGORY
    public static final String TODAY = "Today";
    public static final String FEEL_GOOD = "Feel Good";
    public static final String SPIRITUALITY = "Spirituality";
    public static final String RUMOR_SCANDAL = "Rumor";
    public static final String HOBBIES = "Hobbies";
    public static final String WEIRD = "Weird";
    public static final String DISCOVERY = "Discovery";
    public static final String STAFF_PICKS = "Staff Picks";
    public static final String PETS = "Pets";

    public static int getCategoryIndex(String category){
        switch(category){
            // BIG CATEGORIES
            case TOP_STORIES: return 0;
            case ENTERTAINMENT: return 1;
            case SPORTS: return 2;
            case PRODUCTS: return 3;
            case EXTRA: return 4;

            // TOP STORIES
            case US: return 0;
            case WORLD: return 1;
            case POLITICS: return 2;
            case BUSINESS: return 3;
            case TECH: return 4;
            case HEALTH: return 5;
            case SCIENCE_ENVIRONMENT: return 6;
            case MEDIA: return 7;
            case POLLS: return 8;

            // ENTERTAINMENT
            case CELEBRITY: return 0;
            case MOVIES: return 1;
            case TV: return 2;
            case MUSIC: return 3;
            case ARTS: return 4;
            case HUMOR: return 5;
            case VIRAL: return 6;
            case TRAILERS: return 7;
            case COOL_ADS: return 8;

            // SPORTS
            case FOOTBALL: return 0;
            case BASEBALL: return 1;
            case BASKETBALL: return 2;
            case SOCCER: return 3;
            case HOCKEY: return 4;
            case TENNIS: return 5;
            case GOLF: return 6;
            case WINTER_SPORTS: return 7;
            case EXTREME_SPORTS: return 8;
            case MORE_SPORTS: return 9;

            // PRODUCTS
            case ELECTRONICS: return 0;
            case FASHION: return 1;
            case FITNESS: return 2;
            case FOOD_DRINK: return 3;
            case HOME_GARDEN: return 4;
            case AUTO: return 5;
            case TRAVEL: return 6;
            case GAMES: return 7;
            case BOOKS: return 8;
            case PERSONAL_FINANCE: return 9;

            // EXTRA
            case TODAY: return 0;
            case FEEL_GOOD: return 1;
            case SPIRITUALITY: return 2;
            case RUMOR_SCANDAL: return 3;
            case HOBBIES: return 4;
            case WEIRD: return 5;
            case DISCOVERY: return 6;
            case STAFF_PICKS: return 7;
            case PETS: return 8;
        }

        return -1;
    }

    public static String[] getSmallCategoriesForBigCategory(String bigCategory){
        switch(bigCategory){
            case TOP_STORIES:
                return new String[]{US, WORLD, POLITICS, BUSINESS, TECH, HEALTH, SCIENCE_ENVIRONMENT, MEDIA, POLLS};
            case ENTERTAINMENT:
                return new String[]{CELEBRITY, MOVIES, TV, MUSIC, ARTS, HUMOR, VIRAL, TRAILERS, COOL_ADS};
            case SPORTS:
                return new String[]{FOOTBALL, BASEBALL, BASKETBALL, SOCCER, HOCKEY, TENNIS, GOLF, WINTER_SPORTS, EXTREME_SPORTS, MORE_SPORTS};
            case PRODUCTS:
                return new String[]{ELECTRONICS, FASHION, FITNESS, FOOD_DRINK, HOME_GARDEN, AUTO, TRAVEL, GAMES, BOOKS, PERSONAL_FINANCE};
            case EXTRA:
                return new String[]{TODAY, FEEL_GOOD, SPIRITUALITY, RUMOR_SCANDAL, HOBBIES, WEIRD, DISCOVERY, STAFF_PICKS, PETS};
        }
        return null;
    }

    public static String[] getBigCategories(){
        return new String[]{TOP_STORIES, ENTERTAINMENT, SPORTS, PRODUCTS, EXTRA};
    }
}
