package fiveagency.internship.food.data.network.client;

import java.util.List;

import fiveagency.internship.food.data.network.ApiConstants;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.data.network.service.OmdbService;
import fiveagency.internship.food.domain.model.Cast;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.model.PersonDetails;
import fiveagency.internship.food.domain.model.PersonMovieCredits;
import fiveagency.internship.food.domain.model.Rating;
import fiveagency.internship.food.domain.model.Video;
import io.reactivex.Flowable;
import io.reactivex.Single;

public final class MovieClient {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final OmdbService omdbService;

    public MovieClient(final MovieService movieService, final MovieMapper movieMapper, final OmdbService omdbService) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.omdbService = omdbService;
    }

    public Single<Movie> getMovieDetails(final int movieId) {
        return movieService.movieDetailsEntity(movieId, ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US)
                           .map(movieMapper::mapMovie);
    }

    public Single<List<Cast>> getMovieCast(final int movieId) {
        return movieService.movieCreditsEntity(movieId, ApiConstants.API_KEY)
                           .map(credits -> movieMapper.mapCast(credits.getCast()));
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

    public Single<List<Rating>> getMovieRating(final String imdbId) {
        return omdbService.movieRatingEntity(ApiConstants.OMDB_API_KEY, imdbId)
                          .map(movieMapper::mapRatings);
    }

    public Single<List<Video>> getMovieVideos(final int movieId) {
        return movieService.movieVideosEntity(movieId, ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US)
                           .map(movieMapper::mapVideos);
    }

    public Single<PersonMovieCredits> getPersonMovieCredits(final int personId) {
        return movieService.personMovieCreditsEntity(personId, ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US)
                           .map(movieMapper::mapPersonMovieCredits);
    }

    public Single<PersonDetails> getPersonDetails(final int actorId) {
        return movieService.personDetails(actorId, ApiConstants.API_KEY, ApiConstants.LANGUAGE_EN_US)
                           .map(movieMapper::mapPersonDetails);
    }
}
