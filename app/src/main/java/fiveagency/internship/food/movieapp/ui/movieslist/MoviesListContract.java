package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.List;

public interface MoviesListContract {

    interface Presenter {

        void start();

        void showMovieDetails(int movieId);

        void getFlowableMoviesUseCase();

        void setView(MoviesListContract.View view);

        void insertFavorite(int movieId);

        void removeFavorite(int movieId);

        void saveMovies(List<MovieViewModel> movieViewModelList);

        void getAdditionalMovies(int page);
    }

    interface View {

        void render(final MoviesListViewModel moviesListViewModel);

        void appendMovies(final MoviesListViewModel moviesListViewModel);
    }
}
