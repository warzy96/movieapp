package fiveagency.internship.food.data.network.client;

import java.util.List;

import fiveagency.internship.food.data.network.ApiConstants;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.domain.model.Movie;
import io.reactivex.Flowable;
import io.reactivex.Single;

public final class MovieClient {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieClient(final MovieService movieService, final MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    public Single<Movie> getMovieDetails(final int movieId) {
        return movieService.movieDetailsEntity(movieId, ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US)
                           .map(movieMapper::mapMovie);
    }

    public Single<List<Movie>> getMovies(final int page) {
        return movieService.listMovieEntities(ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US, page)
                           .map(movieMapper::mapMovies);
    }

    public Flowable<List<Movie>> getFlowableMovies(final int page) {
        return movieService.listFlowableMovieEntities(ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US, page)
                           .map(movieMapper::mapMovies).toFlowable();
    }

    public Single<List<Movie>> getMovies(final String title) {
        return movieService.searchMovieEntities(ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US, title)
                           .map(movieMapper::mapMovies);
    }
}
