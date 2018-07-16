package fiveagency.internship.food.movieapp.router;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListFragment;

public final class Router {

    @IdRes
    private static final int CONTAINER_ID = R.id.fragment_container;
    private final Activity activity;
    private final FragmentManager fragmentManager;

    @Inject
    public Router(final Activity activity, final FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    public void showMoviesListScreen() {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, MoviesListFragment.newInstance(), MoviesListFragment.TAG)
                       .commit();
    }

    public void showMovieDetailsScreen(final int movieId) {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, MovieDetailsFragment.newInstance(movieId), MovieDetailsFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }
}
