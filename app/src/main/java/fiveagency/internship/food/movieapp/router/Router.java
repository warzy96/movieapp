package fiveagency.internship.food.movieapp.router;

import android.app.Activity;

import javax.inject.Inject;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesFragment;
import fiveagency.internship.food.movieapp.ui.login.LogInFragment;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListFragment;
import fiveagency.internship.food.movieapp.ui.pager.ActivityFragment;
import fiveagency.internship.food.movieapp.ui.profile.ProfileFragment;

public final class Router {

    @IdRes
    private static final int CONTAINER_ID = R.id.fragment_container;
    @IdRes
    private static final int FRAGMENT_CONTAINER_ID = R.id.container;
    private final Activity activity;
    private final FragmentManager fragmentManager;

    @Inject
    public Router(final Activity activity, final FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    public void showMoviesListScreen() {
        Fragment fragment = fragmentManager.findFragmentByTag(MoviesListFragment.TAG);
        if (fragment == null) {
            fragment = MoviesListFragment.newInstance();
        }

        fragmentManager.beginTransaction()
                       .replace(FRAGMENT_CONTAINER_ID, fragment, MoviesListFragment.TAG)
                       .commit();
    }

    public void showLogInScreen() {
        Fragment fragment = fragmentManager.findFragmentByTag(LogInFragment.TAG);
        if (fragment == null) {
            fragment = LogInFragment.newInstance();
        }

        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, fragment, LogInFragment.TAG)
                       .commit();
    }

    public void showMovieDetailsScreen(final int movieId) {
        fragmentManager.beginTransaction()
                       .add(CONTAINER_ID, MovieDetailsFragment.newInstance(movieId), MovieDetailsFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }

    public void showFavoriteMoviesScreen() {
        Fragment fragment = fragmentManager.findFragmentByTag(MovieFavoritesFragment.TAG);
        if (fragment == null) {
            fragment = MovieFavoritesFragment.newInstance();
        }

        fragmentManager.beginTransaction()
                       .replace(FRAGMENT_CONTAINER_ID, fragment, MovieFavoritesFragment.TAG)
                       .commit();
    }

    public void showActivityFragmentScreen() {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, ActivityFragment.newInstance(), ActivityFragment.TAG)
                       .commit();
    }

    public void showUserProfileScreen() {
        Fragment fragment = fragmentManager.findFragmentByTag(ProfileFragment.TAG);
        if (fragment == null) {
            fragment = ProfileFragment.newInstance();
        }

        fragmentManager.beginTransaction()
                       .replace(FRAGMENT_CONTAINER_ID, fragment, ProfileFragment.TAG)
                       .commit();
    }

    public void exitApp() {
        activity.finish();
    }

    public void goBack() {
        fragmentManager.popBackStack();
    }
}
