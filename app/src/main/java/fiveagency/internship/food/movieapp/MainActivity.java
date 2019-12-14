package fiveagency.internship.food.movieapp;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fiveagency.internship.food.movieapp.injection.activity.ActivityComponent;
import fiveagency.internship.food.movieapp.injection.activity.DaggerActivity;
import fiveagency.internship.food.movieapp.router.Router;
import fiveagency.internship.food.movieapp.ui.login.LogInFragment;
import fiveagency.internship.food.movieapp.ui.login.LogInPresenterKt;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListPresenter;

public final class MainActivity extends DaggerActivity {

    @Inject
    Router router;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        router.showLogInScreen();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LogInPresenterKt.RC_SIGN_IN) {
            final LogInFragment logInFragment = (LogInFragment) getSupportFragmentManager().findFragmentByTag(LogInFragment.TAG);
            if (logInFragment != null) {
                logInFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (requestCode == MoviesListPresenter.RC_LOCATION) {
            final MoviesListFragment moviesListFragment = (MoviesListFragment) getSupportFragmentManager().findFragmentByTag(MoviesListFragment.TAG);
            if (moviesListFragment != null) {
                moviesListFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
