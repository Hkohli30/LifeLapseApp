package com.example.lifelapseapp;

import com.example.lifelapseapp.model.ApiCharacter;
import com.example.lifelapseapp.model.PageInfo;
import com.example.lifelapseapp.services.ApiDataRetriever;
import com.example.lifelapseapp.services.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static com.example.lifelapseapp.constants.Constants.COUNT_TAG;
import static com.example.lifelapseapp.constants.Constants.EPISODE_TAG;
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
import static com.example.lifelapseapp.constants.Constants.SPECIES_TAG;
import static com.example.lifelapseapp.constants.Constants.STATUS_TAG;
import static com.example.lifelapseapp.constants.Constants.TYPE_TAG;
import static com.example.lifelapseapp.constants.Constants.URL_TAG;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void parseStringElementWithJSONObjectTest() {
        JSONObject object = new JSONObject();
        try {
            object.put("temp", "temp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonParser parser = JsonParser.getInstance();
        assertEquals("temp", parser.parseStringElement("temp", object));

    }

    @Test
    public void parseStringElementWithString() {
        String jsonString = "{\"name\":\"hkohli\",\"id\":\"1\",\"age\":\"37\"}";
        JsonParser parser = JsonParser.getInstance();
        assertEquals("hkohli", parser.parseStringElement("name", jsonString));
        assertEquals("37", parser.parseStringElement("age", jsonString));
        assertEquals("1", parser.parseStringElement("id", jsonString));
    }

    @Test
    public void parseJsonObjectTest() {
        try {
            JSONObject sampleObj = new JSONObject();
            JSONObject actualObject = new JSONObject();
            actualObject.put("TempString", "TempString");
            sampleObj.put("JSON", actualObject);
            JsonParser parser = JsonParser.getInstance();
            JSONObject testObject = parser.parseObjectElement("JSON", sampleObj.toString());
            assertEquals(testObject.toString(), actualObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void parseJsonArrayTest() {
        String jsonString = "{\"status\":400,\"errors\":[\"Author is not allowed.\"]}";
        JsonParser parser = JsonParser.getInstance();
        JSONArray actualObj = parser.parseArrayElement("errors", jsonString);
        JSONArray expected = new JSONArray();
        expected.put("Author is not allowed.");
        assertEquals(actualObj.toString(), expected.toString());
    }

    @Test
    public void getCharacterObjectTest() {
        JSONObject jsonObject = new JSONObject();
        ApiDataRetriever apiDataRetriever = ApiDataRetriever.getInstance();
        JsonParser jsonParser = JsonParser.getInstance();
        try {

            jsonObject.put(LOCATION_TAG, new JSONObject().put(NAME_TAG, NAME_TAG).put(URL_TAG, URL_TAG));
            jsonObject.put(ORIGIN_TAG, new JSONObject().put(NAME_TAG, ORIGIN_TAG).put(URL_TAG, URL_TAG));
            jsonObject.put(EPISODE_TAG, (new JSONArray()).put(EPISODE_TAG).put(EPISODE_TAG));
            jsonObject.put(ID_TAG, ID_TAG);
            jsonObject.put(NAME_TAG, NAME_TAG);
            jsonObject.put(STATUS_TAG, STATUS_TAG);
            jsonObject.put(SPECIES_TAG, SPECIES_TAG);
            jsonObject.put(TYPE_TAG, TYPE_TAG);
            jsonObject.put(GENDER_TAG, GENDER_TAG);
            jsonObject.put(IMAGE_TAG, IMAGE_TAG);
            jsonObject.put(URL_TAG, URL_TAG);

            ApiCharacter character = apiDataRetriever.getCharacterObject(jsonParser, jsonObject);
            assertEquals(character.getId(), ID_TAG);
            assertEquals(character.getName(), NAME_TAG);
            assertEquals(character.getStatus(), STATUS_TAG);
            assertEquals(character.getSpecies(), SPECIES_TAG);
            assertEquals(character.getType(), TYPE_TAG);
            assertEquals(character.getGender(), GENDER_TAG);
            assertEquals(character.getImage(), IMAGE_TAG);
            assertEquals(character.getUrl(), URL_TAG);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPageInfoObjectTest() {
        JSONObject jsonObject = new JSONObject();
        JSONObject infoObject = new JSONObject();
        ApiDataRetriever apiDataRetriever = ApiDataRetriever.getInstance();
        JsonParser jsonParser = JsonParser.getInstance();
        try {
            jsonObject.put(COUNT_TAG, COUNT_TAG)
                    .put(PAGES_TAG, PAGES_TAG)
                    .put(NEXT_TAG, NEXT_TAG)
                    .put(PREV_TAG, PREV_TAG);
            infoObject.put(INFO_TAG, jsonObject);

            PageInfo pageInfoElement = new PageInfo("", "");
            PageInfo pageInfo = apiDataRetriever.getPageInfoObject(jsonParser, pageInfoElement, infoObject.toString());
            assertEquals(pageInfo.getCount(), COUNT_TAG);
            assertEquals(pageInfo.getPages(), PAGES_TAG);
            assertEquals(pageInfo.getNext(), NEXT_TAG);
            assertEquals(pageInfo.getPrev(), PREV_TAG);
            assertEquals(pageInfoElement, pageInfo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}