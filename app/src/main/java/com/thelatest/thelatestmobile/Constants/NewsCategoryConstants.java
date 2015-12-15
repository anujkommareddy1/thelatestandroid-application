package com.thelatest.thelatestmobile.Constants;

/**
 * Created by Jesse on 10/20/15.
 */
public class NewsCategoryConstants {

    // BIG CATEGORIES
    public static final String TOP_STORIES = "topstories";                      // ID : 56,  slug : topstories
    public static final String ENTERTAINMENT = "entertainment";                 // ID : 58,  slug : entertainment
    public static final String SPORTS = "sports";                               // ID : 59,  slug : sports
    public static final String PRODUCTS = "products";                           // ID : 57,  slug : products
    public static final String EXTRA = "extra";                                 // ID : 55,  slug : extra

    // TOP STORIES CATEGORY
    public static final String US = "US";                                       // ID : 99,  slug : us
    public static final String WORLD = "World";                                 // ID : 101, slug : world
    public static final String POLITICS = "Politics";                           // ID : 103, slug : politics
    public static final String BUSINESS = "Business";                           // ID : 106, slug : business
    public static final String TECH = "Tech";                                   // ID : 107, slug : tech
    public static final String HEALTH = "Health";                               // ID : 108, slug : health
    public static final String SCIENCE_ENVIRONMENT = "Science & Environment";   // ID : 60,  slug : science-environment
    public static final String MEDIA = "Media";                                 // ID : 64,  slug : media
    public static final String POLLS = "Polls";                                 // ID : 65,  slug : polls

    // ENTERTAINMENT CATEGORY
    public static final String CELEBRITY = "Celebrity";                         // ID : 74,  slug : celebrity
    public static final String MOVIES = "Movies";                               // ID : 76,  slug : movies
    public static final String TV = "TV";                                       // ID : 78,  slug : tv
    public static final String MUSIC = "Music";                                 // ID : 79,  slug : music
    public static final String ARTS = "Arts";                                   // ID : 81,  slug : arts
    public static final String HUMOR = "Humor";                                 // ID : 83,  slug : humor
    public static final String VIRAL = "Viral";                                 // ID : 86,  slug : viral
    public static final String TRAILERS = "Trailers";                           // ID : 90,  slug : trailer
    public static final String COOL_ADS = "Cool Ads";                           // ID : 88,  slug : cool-ads
    public static final String RUMOR_SCANDAL = "Rumor/Scandal";                 // ID : 102, slug : rumor-scandal

    // SPORTS CATEGORY
    public static final String FOOTBALL = "Football";                           // ID : 80,  slug : football
    public static final String BASEBALL = "Baseball";                           // ID : 82,  slug : baseball
    public static final String BASKETBALL = "Basketball";                       // ID : 85,  slug : baseball
    public static final String SOCCER = "Soccer";                               // ID : 94,  slug : soccer
    public static final String HOCKEY = "Hockey";                               // ID : 87,  slug : hockey
    public static final String TENNIS = "Tennis";                               // ID : 89,  slug : tennis
    public static final String GOLF = "Golf";                                   // ID : 91,  slug : golf
    public static final String WINTER_SPORTS = "Winter Sports";                 // ID : 92,  slug : winter-sports
    public static final String EXTREME_SPORTS = "Extreme Sports";               // ID : 113, slug : extreme-sports
    public static final String MORE_SPORTS = "More Sports";                     // ID : 109, slug : more-sports

    // PRODUCTS CATEGORY
    public static final String ELECTRONICS = "Electronics";                     // ID : 66,  slug : electronics
    public static final String FASHION = "Fashion";                             // ID : 67,  slug : fashion
    public static final String FITNESS = "Fitness";                             // ID : 68,  slug : fitness
    public static final String FOOD_DRINK = "Food & Drink";                     // ID : 69,  slug : food-drink
    public static final String HOME_GARDEN = "Home & Garden";                   // ID : 70,  slug : home-garden
    public static final String AUTO = "Auto";                                   // ID : 71,  slug : auto
    public static final String TRAVEL = "Travel";                               // ID : 72,  slug : travel
    public static final String GAMES = "Games";                                 // ID : 73,  slug : games
    public static final String BOOKS = "Books";                                 // ID : 75,  slug : books
    public static final String PERSONAL_FINANCE = "Personal Finance";           // ID : 77,  slug : personal-finance

    // EXTRA CATEGORY
    public static final String TODAY = "Today";                                 // ID : 93,  slug : today
    public static final String FEEL_GOOD = "Feel Good";                         // ID : 98,  slug : feel-good
    public static final String SPIRITUALITY = "Spirituality";                   // ID : 100, slug : spirituality
    public static final String HOBBIES = "Hobbies";                             // ID : 104, slug : hobbies
    public static final String PETS = "Pets";                                   // ID : 167, slug : pets
    public static final String WEIRD = "Weird";                                 // ID : 105, slug : weird
    public static final String DISCOVERY = "Discovery";                         // ID : 61,  slug : discovery


    public static int getCategoryID(String category){

        switch(category){
            // BIG CATEGORIES
            case TOP_STORIES: return 56;
            case ENTERTAINMENT: return 58;
            case SPORTS: return 59;
            case PRODUCTS: return 57;
            case EXTRA: return 55;

            // TOP STORIES
            case US: return 99;
            case WORLD: return 101;
            case POLITICS: return 103;
            case BUSINESS: return 106;
            case TECH: return 107;
            case HEALTH: return 108;
            case SCIENCE_ENVIRONMENT: return 60;
            case MEDIA: return 64;
            case POLLS: return 65;

            // ENTERTAINMENT
            case CELEBRITY: return 74;
            case MOVIES: return 76;
            case TV: return 78;
            case MUSIC: return 79;
            case ARTS: return 81;
            case HUMOR: return 83;
            case VIRAL: return 86;
            case TRAILERS: return 90;
            case COOL_ADS: return 88;
            case RUMOR_SCANDAL: return 102;

            // SPORTS
            case FOOTBALL: return 80;
            case BASEBALL: return 82;
            case BASKETBALL: return 85;
            case SOCCER: return 94;
            case HOCKEY: return 87;
            case TENNIS: return 89;
            case GOLF: return 91;
            case WINTER_SPORTS: return 92;
            case EXTREME_SPORTS: return 113;
            case MORE_SPORTS: return 109;

            // PRODUCTS
            case ELECTRONICS: return 66;
            case FASHION: return 67;
            case FITNESS: return 68;
            case FOOD_DRINK: return 69;
            case HOME_GARDEN: return 70;
            case AUTO: return 71;
            case TRAVEL: return 72;
            case GAMES: return 73;
            case BOOKS: return 75;
            case PERSONAL_FINANCE: return 77;

            // EXTRA
            case TODAY: return 93;
            case FEEL_GOOD: return 98;
            case SPIRITUALITY: return 100;
            case HOBBIES: return 104;
            case PETS: return 167;
            case WEIRD: return 105;
            case DISCOVERY: return 61;
        }

        return -1;
    }

    public static String getCategorySlug(String category){

        switch(category){
            // BIG CATEGORIES
            case TOP_STORIES: return "topstories";
            case ENTERTAINMENT: return "entertainment";
            case SPORTS: return "sports";
            case PRODUCTS: return "products";
            case EXTRA: return "extra";

            // TOP STORIES
            case US: return "us";
            case WORLD: return "world";
            case POLITICS: return "politics";
            case BUSINESS: return "business";
            case TECH: return "tech";
            case HEALTH: return "health";
            case SCIENCE_ENVIRONMENT: return "science-environment";
            case MEDIA: return "media";
            case POLLS: return "polls";

            // ENTERTAINMENT
            case CELEBRITY: return "celebrity";
            case MOVIES: return "movies";
            case TV: return "tv";
            case MUSIC: return "music";
            case ARTS: return "arts";
            case HUMOR: return "humor";
            case VIRAL: return "viral";
            case TRAILERS: return "trailers";
            case COOL_ADS: return "cool-ads";
            case RUMOR_SCANDAL: return "rumor-scandal";

            // SPORTS
            case FOOTBALL: return "football";
            case BASEBALL: return "baseball";
            case BASKETBALL: return "basketball";
            case SOCCER: return "soccer";
            case HOCKEY: return "hockey";
            case TENNIS: return "tennis";
            case GOLF: return "golf";
            case WINTER_SPORTS: return "winter-sports";
            case EXTREME_SPORTS: return "extreme-sports";
            case MORE_SPORTS: return "more-sports";

            // PRODUCTS
            case ELECTRONICS: return "electronics";
            case FASHION: return "fashion";
            case FITNESS: return "fitness";
            case FOOD_DRINK: return "food-drink";
            case HOME_GARDEN: return "home-garden";
            case AUTO: return "auto";
            case TRAVEL: return "travel";
            case GAMES: return "games";
            case BOOKS: return "books";
            case PERSONAL_FINANCE: return "personal-finance";

            // EXTRA
            case TODAY: return "today";
            case FEEL_GOOD: return "feel-good";
            case SPIRITUALITY: return "spirituality";
            case HOBBIES: return "hobbies";
            case PETS: return "pets";
            case WEIRD: return "weird";
            case DISCOVERY: return "discovery";
        }

        return null;
    }

    public static String[] getBigCategories(){
        return new String[]{TOP_STORIES, ENTERTAINMENT, SPORTS, PRODUCTS, EXTRA};
    }

    public static String[] getSmallCategoriesForBigCategory(String bigCategory){
        switch(bigCategory){
            case TOP_STORIES:
                return new String[]{US, WORLD, POLITICS, BUSINESS, TECH, HEALTH, SCIENCE_ENVIRONMENT, MEDIA, POLLS};
            case ENTERTAINMENT:
                return new String[]{CELEBRITY, MOVIES, TV, MUSIC, ARTS, HUMOR, VIRAL, TRAILERS, COOL_ADS, RUMOR_SCANDAL};
            case SPORTS:
                return new String[]{FOOTBALL, BASEBALL, BASKETBALL, SOCCER, HOCKEY, TENNIS, GOLF, WINTER_SPORTS, EXTREME_SPORTS, MORE_SPORTS};
            case PRODUCTS:
                return new String[]{ELECTRONICS, FASHION, FITNESS, FOOD_DRINK, HOME_GARDEN, AUTO, TRAVEL, GAMES, BOOKS, PERSONAL_FINANCE};
            case EXTRA:
                return new String[]{TODAY, FEEL_GOOD, SPIRITUALITY, HOBBIES, PETS, WEIRD, DISCOVERY};
        }
        return null;
    }
}
