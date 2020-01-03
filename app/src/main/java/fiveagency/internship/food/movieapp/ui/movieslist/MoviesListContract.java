package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.List;

import fiveagency.internship.food.domain.model.WeatherModel;

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

        void onStop();

        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

        void showSearchScreen();
    }

    interface View {

        void render(final MoviesListViewModel moviesListViewModel);

        void renderWeather(WeatherModel fiveDayForecast);
    }
}
