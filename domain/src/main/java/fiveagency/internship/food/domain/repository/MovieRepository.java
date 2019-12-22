package fiveagency.internship.food.domain.repository;

import java.util.List;

import fiveagency.internship.food.domain.model.ActorDetails;
import fiveagency.internship.food.domain.model.Cast;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.model.PersonMovieCredits;
import fiveagency.internship.food.domain.model.Rating;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MovieRepository {

    Single<Movie> fetchMovieDetails(int id);

    Single<List<Movie>> fetchMovies(int page);

    Flowable<List<Movie>> queryMovies(String title);

    Completable insertMovies(List<Movie> movies);

    Completable setFavorite(final int movieId);

    Completable removeFavorite(Integer movieId);

    Flowable<List<Movie>> fetchFavorites();

    Flowable<List<Movie>> fetchFlowableMovies(int page);

    Completable insertFavorite(Movie movie);

    Completable setPersonalNote(Movie movie);

    Single<List<Cast>> fetchMovieCast(final int movieId);

    Single<List<Rating>> fetchRatings(final String imdbId);

    Single<PersonMovieCredits> fetchPersonMovieCredits(int personId);

    Single<ActorDetails> fetchActorDetails(int actorId);
}
