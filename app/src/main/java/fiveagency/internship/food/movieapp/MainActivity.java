package fiveagency.internship.food.movieapp;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import fiveagency.internship.food.movieapp.injection.activity.ActivityComponent;
import fiveagency.internship.food.movieapp.injection.activity.DaggerActivity;
import fiveagency.internship.food.movieapp.router.Router;
import fiveagency.internship.food.movieapp.ui.login.LogInFragment;

import static fiveagency.internship.food.movieapp.ui.login.LogInPresenterKt.RC_SIGN_IN;

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
        if (requestCode == RC_SIGN_IN) {
            final LogInFragment logInFragment = (LogInFragment) getSupportFragmentManager().findFragmentByTag(LogInFragment.TAG);
            if (logInFragment != null) {
                logInFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
