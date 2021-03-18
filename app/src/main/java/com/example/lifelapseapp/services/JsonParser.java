package com.example.lifelapseapp.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public static JsonParser jsonParser = null;
    private JsonParser() {}

    public static synchronized JsonParser getInstance() {
        if(jsonParser == null)
            jsonParser = new JsonParser();
        return jsonParser;
    }

    public String parseStringElement(String tag, JSONObject object) {
        try {
            return object.getString(tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String parseStringElement(String tag, String jsonString) {
        try {
            return new JSONObject(jsonString).getString(tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject parseObjectElement(String tag, String jsonString) {
        try {
            return new JSONObject(jsonString).getJSONObject(tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray parseArrayElement(String tag, String jsonString) {
        try {
            return new JSONObject(jsonString).getJSONArray(tag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
