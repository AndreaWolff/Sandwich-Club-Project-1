package com.udacity.sandwichclub.features.details;

public interface DetailsActivityContract {

    interface View {
        void finishActivity();

        void renderAlsoKnownAs(String alsoKnownAsNames);

        void renderIngredients(String ingredients);

        void renderDescription(String description);

        void renderPlaceOfOrigin(String placeOfOrigin);

        void renderTitle(String mainName);

        void renderSandwichImage(String image);

        void displayNoOtherNames(int noOtherNames);

        void displayNoIngredients(int noIngredients);

        void displayNoPlaceOfOrigin(int noPlaceOfOrigin);

        void displayNoDescription(int noDescription);
    }

    interface Presenter {
        void closeOnError();

        void getSandwichFromJson(String json);

        void populateScreen();

        void disconnectView();
    }
}
