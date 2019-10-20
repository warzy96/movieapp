package fiveagency.internship.food.movieapp.router;

import android.app.Activity;

import javax.inject.Inject;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesFragment;
import fiveagency.internship.food.movieapp.ui.login.LogInFragment;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListFragment;
import fiveagency.internship.food.movieapp.ui.pager.ActivityFragment;

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
        fragmentManager.beginTransaction()
                       .replace(FRAGMENT_CONTAINER_ID, MoviesListFragment.newInstance(), MoviesListFragment.TAG)
                       .commit();
    }

    public void showLogInScreen() {
        fragmentManager.beginTransaction()
                       .add(CONTAINER_ID, LogInFragment.newInstance(), LogInFragment.TAG)
                       .commit();
    }

    public void showMovieDetailsScreen(final int movieId) {
        fragmentManager.beginTransaction()
                       .replace(FRAGMENT_CONTAINER_ID, MovieDetailsFragment.newInstance(movieId), MovieDetailsFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }

    public void showFavoriteMoviesScreen() {
        fragmentManager.beginTransaction()
                       .replace(FRAGMENT_CONTAINER_ID, MovieFavoritesFragment.newInstance(), MovieFavoritesFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }

    public void showActivityFragmentScreen() {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, ActivityFragment.newInstance(), ActivityFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }
}
