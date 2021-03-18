package com.example.lifelapseapp.constants;

public class Constants {

    public static final String CHARACTER_TAG = "Character";
    public static final String baseURL = "https://rickandmortyapi.com/api/character/";
    public static final String pageAppender = "?page=";

    // API tags
    public static final String INFO_TAG = "info";
    public static final String COUNT_TAG = "count";
    public static final String PAGES_TAG = "pages";
    public static final String NEXT_TAG = "next";
    public static final String PREV_TAG = "prev";
    public static final String RESULTS_TAG = "results";
    public static final String LOCATION_TAG = "location";
    public static final String ORIGIN_TAG = "origin";
    public static final String EPISODE_TAG = "episode";
    public static final String ID_TAG = "id";
    public static final String NAME_TAG = "name";
    public static final String STATUS_TAG = "status";
    public static final String SPECIES_TAG = "species";
    public static final String TYPE_TAG = "type";
    public static final String GENDER_TAG = "gender";
    public static final String IMAGE_TAG = "image";
    public static final String URL_TAG = "url";


    // Message
    public static final String ERROR_MESSAGE = "Unable to load data from API / No results returned";
    public static final String SEARCH_SUGGESTION = "USE ? before search \nsearch params based on name(morty), status(alive, dead, unknown), " +
            "species(human), type and gender(female, male, genderless or unknown) \n " +
            "for multiple queries use &. such as name=rick&status=alive" +
            "\n Search a valid id with just the number(without ?) e.g 22";
    public static final String SEARCH_TITLE_MESSAGE = "Search your Characters";

}
