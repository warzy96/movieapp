package fiveagency.internship.food.movieapp.injection.fragment;

import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesFragment;
import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesPresenter;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsFragment;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsPresenter;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListPresenter;

public interface FragmentComponentInjects {

    void inject(MovieDetailsFragment view);

    void inject(MoviesListFragment view);

    void inject(MoviesListPresenter presenter);

    void inject(MovieDetailsPresenter presenter);

    void inject(MovieFavoritesFragment movieFavoritesFragment);

    void inject(MovieFavoritesPresenter movieFavoritesPresenter);
}
