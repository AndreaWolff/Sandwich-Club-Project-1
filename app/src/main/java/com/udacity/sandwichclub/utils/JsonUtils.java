package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String JSON_NAME_KEY = "name";
    private final static String JSON_MAIN_NAME_KEY = "mainName";
    private final static String JSON_ALSO_KNOWN_AS_KEY = "alsoKnownAs";
    private final static String JSON_PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private final static String JSON_DESCRIPTION_KEY = "description";
    private final static String JSON_IMAGE_KEY = "image";
    private final static String JSON_INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        String sandwichMainName = "";
        List<String> sandwichAlsoKnownAs = new ArrayList<>();
        String sandwichPlaceOfOrigin = "";
        String sandwichDescription = "";
        String sandwichImage = "";
        List<String> sandwichIngredients = new ArrayList<>();

        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject sandwichName = sandwichObject.getJSONObject(JSON_NAME_KEY);
            sandwichMainName = sandwichName.getString(JSON_MAIN_NAME_KEY);

            JSONArray sandwichAlsoKnownAsArray = sandwichName.getJSONArray(JSON_ALSO_KNOWN_AS_KEY);
            sandwichAlsoKnownAs = new ArrayList<>();
            for (int i = 0; i < sandwichAlsoKnownAsArray.length(); i++) {
                String val = sandwichAlsoKnownAsArray.getString(i);
                sandwichAlsoKnownAs.add(val);
            }

            sandwichPlaceOfOrigin = sandwichObject.optString(JSON_PLACE_OF_ORIGIN_KEY);
            sandwichDescription = sandwichObject.optString(JSON_DESCRIPTION_KEY);
            sandwichImage = sandwichObject.optString(JSON_IMAGE_KEY);

            JSONArray sandwichIngredientsArray = sandwichObject.getJSONArray(JSON_INGREDIENTS_KEY);
            sandwichIngredients = new ArrayList<>();
            for (int i = 0; i < sandwichIngredientsArray.length(); i++) {
                String val = sandwichIngredientsArray.getString(i);
                sandwichIngredients.add(val);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(sandwichMainName, sandwichAlsoKnownAs, sandwichPlaceOfOrigin, sandwichDescription, sandwichImage, sandwichIngredients);
    }
}
