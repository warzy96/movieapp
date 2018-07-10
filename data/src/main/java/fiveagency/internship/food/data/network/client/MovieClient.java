package fiveagency.internship.food.data.network.client;

import java.util.List;

import fiveagency.internship.food.data.network.ApiConstants;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.data.network.model.ApiMoviesList;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.domain.model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class MovieClient {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieClient(final MovieService movieService, final MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    public void getMovieDetails(final int movieId, final ResponseListener<Movie> responseListener) {
        movieService.movieDetailsEntity(movieId, ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US)
                    .enqueue(new Callback<ApiMovie>() {

                        @Override
                        public void onResponse(final Call<ApiMovie> call, final Response<ApiMovie> response) {
                            if (response.isSuccessful()) {
                                final ApiMovie apiMovie = response.body();
                                responseListener.onResult(movieMapper.mapMovie(apiMovie));
                            }
                        }

                        @Override
                        public void onFailure(final Call<ApiMovie> call, final Throwable t) {
                            responseListener.onFailure(t);
                        }
                    });
    }

    public void getMovies(final ResponseListener<List<Movie>> responseListener, final int page) {
        movieService.listMovieEntities(ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US, page)
                    .enqueue(new Callback<ApiMoviesList>() {

                        @Override
                        public void onResponse(final Call<ApiMoviesList> call, final Response<ApiMoviesList> response) {
                            final ApiMoviesList apiMoviesList = response.body();
                            if (response.isSuccessful()) {
                                responseListener.onResult(movieMapper.mapMovies(apiMoviesList));
                            }
                        }

                        @Override
                        public void onFailure(final Call<ApiMoviesList> call, final Throwable t) {
                            responseListener.onFailure(t);
                        }
                    });
    }

    public void getMovies(final ResponseListener<List<Movie>> responseListener, final String title) {
        movieService.searchMovieEntities(ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US, title)
                    .enqueue(new Callback<ApiMoviesList>() {

                        @Override
                        public void onResponse(final Call<ApiMoviesList> call, final Response<ApiMoviesList> response) {
                            if (response.isSuccessful()) {
                                final ApiMoviesList apiMoviesList = response.body();
                                responseListener.onResult(movieMapper.mapMovies(apiMoviesList));
                            }
                        }

                        @Override
                        public void onFailure(final Call<ApiMoviesList> call, final Throwable t) {
                            responseListener.onFailure(t);
                        }
                    });
    }
}
