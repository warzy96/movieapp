package fiveagency.internship.food.movieapp.injection.fragment.module;

import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentScope;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListAdapter;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoaderImpl;

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
}
