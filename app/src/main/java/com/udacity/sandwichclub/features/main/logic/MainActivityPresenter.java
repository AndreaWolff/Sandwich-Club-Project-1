package com.udacity.sandwichclub.features.main.logic;

import com.udacity.sandwichclub.features.main.MainActivityContract;
import com.udacity.sandwichclub.features.main.MainActivityContract.Navigation;

import java.lang.ref.WeakReference;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private WeakReference<Navigation> navigationWeakReference;

    public MainActivityPresenter(Navigation navigation) {
        navigationWeakReference = new WeakReference<>(navigation);
    }

    @Override
    public void onSandwichSelected(int position) {
        Navigation navigation = navigationWeakReference.get();

        if (navigation != null) {
            navigation.navigateToSandwich(position);
        }
    }
}
