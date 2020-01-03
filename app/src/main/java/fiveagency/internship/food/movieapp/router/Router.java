package fiveagency.internship.food.movieapp.router;

import android.app.Activity;

import javax.inject.Inject;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.ui.actordetails.ActorDetailsFragment;
import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesFragment;
import fiveagency.internship.food.movieapp.ui.login.LogInFragment;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListFragment;
import fiveagency.internship.food.movieapp.ui.pager.ActivityFragment;
import fiveagency.internship.food.movieapp.ui.profile.ProfileFragment;
import fiveagency.internship.food.movieapp.ui.searchlist.MoviesSearchFragment;

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
        if (isFragmentVisible(MoviesListFragment.TAG)) {
            return;
        }
        fragmentManager.beginTransaction()
                       .replace(FRAGMENT_CONTAINER_ID, MoviesListFragment.newInstance(), MoviesListFragment.TAG)
                       .commit();
    }

    public void showActivityFragmentScreen() {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, ActivityFragment.newInstance(), ActivityFragment.TAG)
                       .commit();
    }

    public void showUserProfileScreen() {
        if (isFragmentVisible(ProfileFragment.TAG)) {
            return;
        }
        fragmentManager.beginTransaction()
                       .replace(FRAGMENT_CONTAINER_ID, ProfileFragment.newInstance(), ProfileFragment.TAG)
                       .commit();
    }

    public void showFavoriteMoviesScreen() {
        if (isFragmentVisible(MovieFavoritesFragment.TAG)) {
            return;
        }
        fragmentManager.beginTransaction()
                       .replace(FRAGMENT_CONTAINER_ID, MovieFavoritesFragment.newInstance(), MovieFavoritesFragment.TAG)
                       .commit();
    }

    public void showLogInScreen() {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, LogInFragment.newInstance(), LogInFragment.TAG)
                       .commit();
    }

    public void showMovieDetailsScreen(final int movieId) {
        fragmentManager.beginTransaction()
                       .add(CONTAINER_ID, MovieDetailsFragment.newInstance(movieId), MovieDetailsFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }

    public void exitApp() {
        activity.finish();
    }

    public void goBack() {
        fragmentManager.popBackStack();
    }

    public void showActorDetailsScreen(final int castId) {
        fragmentManager.beginTransaction()
                       .add(CONTAINER_ID, ActorDetailsFragment.Companion.newInstance(castId), ActorDetailsFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }

    public void showMoviesSearchScreen() {
        fragmentManager.beginTransaction()
                       .add(CONTAINER_ID, MoviesSearchFragment.newInstance(), MoviesSearchFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }

    private boolean isFragmentVisible(final String tag) {
        final Fragment fragment = fragmentManager.findFragmentByTag(tag);
        return fragment != null && fragment.isVisible();
    }
}
