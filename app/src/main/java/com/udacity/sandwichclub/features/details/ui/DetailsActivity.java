package com.udacity.sandwichclub.features.details.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.features.details.DetailsActivityContract;
import com.udacity.sandwichclub.features.details.logic.DetailsActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_SHORT;
import static com.udacity.sandwichclub.features.common.ActivityConstants.EXTRA_POSITION;

public class DetailsActivity extends AppCompatActivity implements DetailsActivityContract.View {

    private static final int DEFAULT_POSITION = -1;

    private DetailsActivityContract.Presenter presenter;

    @BindView(R.id.textview_also_known_as) TextView alsoKnownAsTextView;
    @BindView(R.id.textview_ingredients) TextView ingredientsTextView;
    @BindView(R.id.textview_description) TextView descriptionTextView;
    @BindView(R.id.textview_place_of_origin) TextView placeOfOriginTextView;
    @BindView(R.id.imageview_sandwich) ImageView sandwichImageView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        presenter = new DetailsActivityPresenter(this);

        Intent intent = getIntent();
        if (intent == null) {
            presenter.closeOnError();
        }

        int position = intent != null ?
                intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION) : DEFAULT_POSITION;

        if (position == DEFAULT_POSITION) {
            presenter.closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        presenter.getSandwichFromJson(json);
        presenter.populateScreen();
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.disconnectView();
    }

    @Override public void finishActivity() {
        Toast.makeText(this, R.string.detail_error_message, LENGTH_SHORT).show();
        finish();
    }

    @Override public void renderAlsoKnownAs(final String alsoKnownAsNames) {
        alsoKnownAsTextView.append(alsoKnownAsNames);
    }

    @Override public void renderIngredients(final String ingredients) {
        ingredientsTextView.append(ingredients);
    }

    @Override public void renderDescription(final String description) {
        descriptionTextView.setText(description);
    }

    @Override public void renderPlaceOfOrigin(final String placeOfOrigin) {
        placeOfOriginTextView.setText(placeOfOrigin);
    }

    @Override public void renderTitle(final String mainName) {
        setTitle(mainName);
    }

    @Override public void renderSandwichImage(final String image) {
        Picasso.with(this)
               .load(image)
               .placeholder(R.color.image_loading_background)
               .into(sandwichImageView);
    }

    @Override public void displayNoOtherNames(final int noOtherNames) {
        alsoKnownAsTextView.setText(getString(noOtherNames));
    }

    @Override public void displayNoIngredients(final int noIngredients) {
        ingredientsTextView.setText(getString(noIngredients));
    }

    @Override public void displayNoPlaceOfOrigin(final int noPlaceOfOrigin) {
        placeOfOriginTextView.setText(getString(noPlaceOfOrigin));
    }

    @Override public void displayNoDescription(final int noDescription) {
        descriptionTextView.setText(getString(noDescription));
    }
}
