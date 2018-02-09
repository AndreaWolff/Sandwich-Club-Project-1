package com.udacity.sandwichclub.features.main;

public interface MainActivityContract {

    interface Navigation {
        void navigateToSandwich(int position);
    }

    interface Presenter {
        void onSandwichSelected(int position);
    }
}
