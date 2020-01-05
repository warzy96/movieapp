package fiveagency.internship.food.movieapp.ui.favoriteslist;

import fiveagency.internship.food.movieapp.ui.movieslist.FavoriteMovieModel;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListViewModel;

public interface MovieFavoritesContract {

    interface Presenter {

        void start();

        void setView(MovieFavoritesContract.View view);

        void insertFavorite(FavoriteMovieModel favoriteMovieModel);

        void removeFavorite(FavoriteMovieModel favoriteMovieModel);

        void showMovieDetails(int movieId);

        void getFavoritesUseCase();

        void onStop();
    }

    interface View {

        void render(final MoviesListViewModel moviesListViewModel);
    }
}
