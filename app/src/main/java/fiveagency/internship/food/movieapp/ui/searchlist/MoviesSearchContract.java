package fiveagency.internship.food.movieapp.ui.searchlist;

import android.widget.EditText;

import fiveagency.internship.food.movieapp.ui.movieslist.FavoriteMovieModel;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModel;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListViewModel;

public interface MoviesSearchContract {

    interface Presenter {

        void start(EditText editText);

        void showMovieDetails(int movieId);

        void setView(MoviesSearchContract.View view);

        void insertFavorite(MovieViewModel movie);

        void removeFavorite(final FavoriteMovieModel favoriteMovieModel);

        void refreshSearch(final String title);
    }

    interface View {

        void render(MoviesListViewModel moviesListViewModel);
    }
}
