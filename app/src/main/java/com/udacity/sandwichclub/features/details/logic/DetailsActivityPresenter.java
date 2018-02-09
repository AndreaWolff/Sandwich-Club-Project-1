package com.udacity.sandwichclub.features.details.logic;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.features.details.DetailsActivityContract;
import com.udacity.sandwichclub.features.details.DetailsActivityContract.View;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.lang.ref.WeakReference;
import java.util.List;

public class DetailsActivityPresenter implements DetailsActivityContract.Presenter {

    private WeakReference<View> viewWeakReference;

    private Sandwich sandwich;

    public DetailsActivityPresenter(View view) {
        viewWeakReference = new WeakReference<>(view);
    }

    @Override
    public void closeOnError() {
        View view = viewWeakReference.get();

        if (view != null) {
            view.finishActivity();
        }
    }

    @Override
    public void getSandwichFromJson(String json) {
        sandwich = JsonUtils.parseSandwichJson(json);
    }

    @Override
    public void populateScreen() {
        View view = viewWeakReference.get();

        if (view != null) {
            view.renderTitle(sandwich.getMainName());
            configureSandwichImage(view);
            configureAlsoKnownAsNames(view);
            configureIngredients(view);
            configurePlaceOfOrigin(view);
            configureDescription(view);
        }
    }

    private void configureSandwichImage(View view) {
        String image = sandwich.getImage();
        if (image != null && !image.equals("")) {
            view.renderSandwichImage(image);
        }
    }

    private void configureAlsoKnownAsNames(View view) {
        List<String> alsoKnownAsNames = sandwich.getAlsoKnownAs();
        if (alsoKnownAsNames != null && !alsoKnownAsNames.isEmpty()) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < alsoKnownAsNames.size(); i++) {
                if (i != alsoKnownAsNames.size()-1) {
                    sb.append(alsoKnownAsNames.get(i)).append(", ");
                } else {
                    sb.append(alsoKnownAsNames.get(i));
                }
            }

            view.renderAlsoKnownAs(sb.toString());
        } else {
            view.displayNoOtherNames(R.string.detail_no_other_known_as_names_message);
        }
    }

    private void configureIngredients(View view) {
        List<String> ingredients = sandwich.getIngredients();
        if (ingredients != null && !ingredients.isEmpty()) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < ingredients.size(); i++) {
                if (i != ingredients.size()-1) {
                    sb.append(ingredients.get(i)).append("\n");
                } else {
                    sb.append(ingredients.get(i));
                }
            }

            view.renderIngredients(sb.toString());
        } else {
            view.displayNoIngredients(R.string.detail_no_ingredients_message);
        }
    }

    private void configurePlaceOfOrigin(View view) {
        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        if (placeOfOrigin != null && !placeOfOrigin.equals("")) {
            view.renderPlaceOfOrigin(sandwich.getPlaceOfOrigin());
        } else {
            view.displayNoPlaceOfOrigin(R.string.detail_no_place_of_origin_message);
        }
    }

    private void configureDescription(View view) {
        String description = sandwich.getDescription();
        if (description != null && !description.equals("")) {
            view.renderDescription(description);
        } else {
            view.displayNoDescription(R.string.detail_no_description_message);
        }
    }
}

