package fiveagency.internship.food.movieapp.ui.searchlist;

import android.widget.EditText;

import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModel;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListViewModel;

public interface MoviesSearchContract {

    interface Presenter {

        void start(EditText editText);

        void showMovieDetails(int movieId);

        void setView(MoviesSearchContract.View view);

        void insertFavorite(MovieViewModel movie);

        void removeFavorite(int movieId);
    }

    interface View {

        void render(MoviesListViewModel moviesListViewModel);
    }
}
