package fiveagency.internship.food.movieapp.injection.fragment.module;

import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentScope;
import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesAdapter;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListAdapter;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader;

@Module
public final class FragmentModule {

    private final DaggerFragment fragment;

    public FragmentModule(final DaggerFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public MoviesListAdapter provideMoviesListAdapter(final ImageLoader imageLoader, final LayoutInflater layoutInflater) {
        return new MoviesListAdapter(layoutInflater, imageLoader);
    }

    @Provides
    @FragmentScope
    public MovieDetailsFragment provideMovieDetailsFragment(final int movieId) {
        return MovieDetailsFragment.newInstance(movieId);
    }

    @Provides
    @FragmentScope
    public MovieFavoritesAdapter provideMovieFavoritesAdapter(final LayoutInflater layoutInflater) {
        return new MovieFavoritesAdapter(layoutInflater);
    }
}
