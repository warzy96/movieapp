package fiveagency.internship.food.movieapp;

import android.os.Bundle;

import javax.inject.Inject;

import fiveagency.internship.food.movieapp.injection.activity.ActivityComponent;
import fiveagency.internship.food.movieapp.injection.activity.DaggerActivity;
import fiveagency.internship.food.movieapp.router.Router;

public final class MainActivity extends DaggerActivity {

    @Inject
    Router router;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        router.showMoviesListScreen();
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
