package fiveagency.internship.food.movieapp.injection.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.ComponentFactory;

public abstract class DaggerActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getActivityComponent());
    }

    public ActivityComponent getActivityComponent() {
        if(activityComponent == null) {
            activityComponent = ComponentFactory.createActivityComponent(this, getMovieApplication().getApplicationComponent());
        }
        return activityComponent;
    }

    private MovieApplication getMovieApplication() {
        return MovieApplication.from(this);
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
