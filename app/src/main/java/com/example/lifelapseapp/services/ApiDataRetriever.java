package com.example.lifelapseapp.services;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lifelapseapp.adapters.MainRecyclerViewAdapter;
import com.example.lifelapseapp.model.ApiCharacter;
import com.example.lifelapseapp.model.Location;
import com.example.lifelapseapp.model.PageInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.lifelapseapp.constants.Constants.COUNT_TAG;
import static com.example.lifelapseapp.constants.Constants.EPISODE_TAG;
import static com.example.lifelapseapp.constants.Constants.ERROR_MESSAGE;
import static com.example.lifelapseapp.constants.Constants.GENDER_TAG;
import static com.example.lifelapseapp.constants.Constants.ID_TAG;
import static com.example.lifelapseapp.constants.Constants.IMAGE_TAG;
import static com.example.lifelapseapp.constants.Constants.INFO_TAG;
import static com.example.lifelapseapp.constants.Constants.LOCATION_TAG;
import static com.example.lifelapseapp.constants.Constants.NAME_TAG;
import static com.example.lifelapseapp.constants.Constants.NEXT_TAG;
import static com.example.lifelapseapp.constants.Constants.ORIGIN_TAG;
import static com.example.lifelapseapp.constants.Constants.PAGES_TAG;
import static com.example.lifelapseapp.constants.Constants.PREV_TAG;
import static com.example.lifelapseapp.constants.Constants.RESULTS_TAG;
import static com.example.lifelapseapp.constants.Constants.SPECIES_TAG;
import static com.example.lifelapseapp.constants.Constants.STATUS_TAG;
import static com.example.lifelapseapp.constants.Constants.TYPE_TAG;
import static com.example.lifelapseapp.constants.Constants.URL_TAG;


/**
 * ApiDataReceiver with singleton pattern and thread saftey
 */
public class ApiDataRetriever {

    public static ApiDataRetriever apiDataRetriever = null;

    private ApiDataRetriever() {
    }

    public static synchronized ApiDataRetriever getInstance() {
        if (apiDataRetriever == null)
            apiDataRetriever = new ApiDataRetriever();
        return apiDataRetriever;
    }

    public void fetchData(String url, final Context context, final List<ApiCharacter> list, final MainRecyclerViewAdapter adapter, final PageInfo pageInfo) {

        //dialog while waiting to load data
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Fetching Data")
                .setMessage("Please wait while we are fetching data")
                .setCancelable(false).create();
        alertDialog.show();

        RequestQueue queue = Volley.newRequestQueue(context);

        // Volley to fetch the result from API
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertDialog.dismiss();
                        if (response != null && !response.isEmpty())
                            populateData(response, list, adapter, pageInfo);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        alertDialog.dismiss();
                        Toast.makeText(context, ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(stringRequest);
    }

    /**
     * Populates the data in the arrayList
     *
     * @param responseData   : Response message from server
     * @param characterList: list of the character that is update in the main recyclerview
     * @param adapter:       adapter to update the view
     * @param pageInfo:      the next and prev value holder
     */
    public void populateData(String responseData, List<ApiCharacter> characterList, MainRecyclerViewAdapter adapter, PageInfo pageInfo) {
        JsonParser parser = JsonParser.getInstance();
        // populate the info elements
        if (pageInfo != null)
            getPageInfoObject(parser, pageInfo, responseData);
        // Populate the results elements
        JSONArray elements = parser.parseArrayElement(RESULTS_TAG, responseData);
        characterList.clear();
        if (elements != null) {
            for (int i = 0; i < elements.length(); i++) {
                try {
                    characterList.add(getCharacterObject(JsonParser.getInstance(), elements.get(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            characterList.add(getCharacterObject(JsonParser.getInstance(), responseData));
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * Updates the PageInfo of the current view
     *
     * @param parser:       parser service to parse data
     * @param pageInfo:     holds the pagination variables prev and next
     * @param responseData: the result from the server
     * @return: updated page info object
     */
    public PageInfo getPageInfoObject(JsonParser parser, PageInfo pageInfo, String responseData) {
        JSONObject jsonObject = parser.parseObjectElement(INFO_TAG, responseData);
        pageInfo.setNext(parser.parseStringElement(NEXT_TAG, jsonObject));
        pageInfo.setPrev(parser.parseStringElement(PREV_TAG, jsonObject));
        pageInfo.setPages(parser.parseStringElement(PAGES_TAG, jsonObject));
        pageInfo.setCount(parser.parseStringElement(COUNT_TAG, jsonObject));
        return pageInfo;
    }

    /**
     * Updates the character object
     *
     * @param parser:  parser to parse json data
     * @param element: JSON object to be parsed
     * @return: returns the updated character object
     */
    public ApiCharacter getCharacterObject(JsonParser parser, Object element) {
        ArrayList<String> episodesList = new ArrayList<>();
        JSONObject locationObject = parser.parseObjectElement(LOCATION_TAG, element.toString());
        JSONObject originObject = parser.parseObjectElement(ORIGIN_TAG, element.toString());
        JSONArray episodes = parser.parseArrayElement(EPISODE_TAG, element.toString());
        if (episodes != null) {
            for (int i = 0; i < episodes.length(); i++) {
                try {
                    episodesList.add(episodes.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        String elementString = element.toString();
        return new ApiCharacter(
                parser.parseStringElement(ID_TAG, elementString),
                parser.parseStringElement(NAME_TAG, elementString),
                parser.parseStringElement(STATUS_TAG, elementString),
                parser.parseStringElement(SPECIES_TAG, elementString),
                parser.parseStringElement(TYPE_TAG, elementString),
                parser.parseStringElement(GENDER_TAG, elementString),
                parser.parseStringElement(IMAGE_TAG, elementString),
                parser.parseStringElement(URL_TAG, elementString),
                episodesList,
                new Location(parser.parseStringElement(NAME_TAG, originObject.toString()),
                        parser.parseStringElement(URL_TAG, originObject.toString())),
                new Location(parser.parseStringElement(NAME_TAG, locationObject.toString()),
                        parser.parseStringElement(URL_TAG, locationObject.toString()))
        );
    }
}
