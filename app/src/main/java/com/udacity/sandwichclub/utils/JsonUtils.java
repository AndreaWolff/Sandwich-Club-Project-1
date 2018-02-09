package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwichObject = null;
        JSONObject sandwichName = null;
        String sandwichMainName = "";
        List<String> sandwichAlsoKnownAs = new ArrayList<>();
        String sandwichPlaceOfOrigin = "";
        String sandwichDescription = "";
        String sandwichImage = "";
        List<String> sandwichIngredients = new ArrayList<>();

        try {
            sandwichObject = new JSONObject(json);
            sandwichName = sandwichObject.getJSONObject("name");
            sandwichMainName = sandwichName.getString("mainName");

            sandwichPlaceOfOrigin = sandwichObject.optString("placeOfOrigin");
            sandwichDescription = sandwichObject.optString("description");
            sandwichImage = sandwichObject.optString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            if (sandwichName != null) {
                JSONArray sandwichAlsoKnownAsArray = sandwichName.getJSONArray("alsoKnownAs");
                sandwichAlsoKnownAs = new ArrayList<>();
                for (int i = 0; i < sandwichAlsoKnownAsArray.length(); i++) {
                    String val = sandwichAlsoKnownAsArray.getString(i);
                    sandwichAlsoKnownAs.add(val);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            if (sandwichObject != null) {
                JSONArray sandwichIngredientsArray = sandwichObject.getJSONArray("ingredients");
                sandwichIngredients = new ArrayList<>();
                for (int i = 0; i < sandwichIngredientsArray.length(); i++) {
                    String val = sandwichIngredientsArray.getString(i);
                    sandwichIngredients.add(val);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(sandwichMainName, sandwichAlsoKnownAs, sandwichPlaceOfOrigin, sandwichDescription, sandwichImage, sandwichIngredients);
    }
}
