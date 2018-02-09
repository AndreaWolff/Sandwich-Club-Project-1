package com.udacity.sandwichclub.features.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.features.details.ui.DetailsActivity;
import com.udacity.sandwichclub.features.main.MainActivityContract;
import com.udacity.sandwichclub.features.main.logic.MainActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

import static com.udacity.sandwichclub.features.common.ActivityConstants.EXTRA_POSITION;

public class MainActivity extends AppCompatActivity implements MainActivityContract.Navigation {

    private MainActivityContract.Presenter presenter;

    @BindView(R.id.sandwiches_listview) ListView listView;

    @OnItemClick(R.id.sandwiches_listview)
    public void onItemClick(int position) {
        presenter.onSandwichSelected(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new MainActivityPresenter(this);

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sandwiches);

        listView.setAdapter(adapter);
    }

    @Override
    public void navigateToSandwich(final int position) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(EXTRA_POSITION, position);
                startActivity(intent);
            }
        });
    }
}
