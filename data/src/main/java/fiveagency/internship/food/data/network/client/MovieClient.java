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
    private final ApiConstants apiConstants = new ApiConstants();

    public MovieClient(final MovieService movieService, final MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    public void getMovieDetails(int movieId, final ResponseListener<Movie> responseListener) {
        movieService.movieDetailsEntity(movieId, apiConstants.api_key, apiConstants.language_enUS)
                    .enqueue(new Callback<ApiMovie>() {

                        @Override
                        public void onResponse(final Call<ApiMovie> call, final Response<ApiMovie> response) {
                            ApiMovie apiMovie = response.body();
                            responseListener.onResult(movieMapper.mapMovie(apiMovie == null ? new ApiMovie() : apiMovie));
                        }

                        @Override
                        public void onFailure(final Call<ApiMovie> call, final Throwable t) {
                            responseListener.onFailure(t);
                        }
                    });
    }

    public void getMovies(final ResponseListener<List<Movie>> responseListener, int page) {
        movieService.listMovieEntities(apiConstants.api_key, apiConstants.language_enUS, page)
                    .enqueue(new Callback<ApiMoviesList>() {

                        @Override
                        public void onResponse(final Call<ApiMoviesList> call, final Response<ApiMoviesList> response) {
                            ApiMoviesList apiMoviesList = response.body();
                            if (response.isSuccessful()) {
                                responseListener.onResult(movieMapper.mapMovies(apiMoviesList == null ? new ApiMoviesList() : apiMoviesList));
                            }
                        }

                        @Override
                        public void onFailure(final Call<ApiMoviesList> call, final Throwable t) {
                            responseListener.onFailure(t);
                        }
                    });
    }

    public void getMovies(final ResponseListener<List<Movie>> responseListener, String title) {
        movieService.searchMovieEntities(apiConstants.api_key, apiConstants.language_enUS, title)
                    .enqueue(new Callback<ApiMoviesList>() {

                        @Override
                        public void onResponse(final Call<ApiMoviesList> call, final Response<ApiMoviesList> response) {
                            ApiMoviesList apiMoviesList = response.body();
                            responseListener.onResult(movieMapper.mapMovies(apiMoviesList == null ? new ApiMoviesList() : apiMoviesList));
                        }

                        @Override
                        public void onFailure(final Call<ApiMoviesList> call, final Throwable t) {
                            responseListener.onFailure(t);
                        }
                    });
    }
}
