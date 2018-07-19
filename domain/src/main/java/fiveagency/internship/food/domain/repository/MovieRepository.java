package fiveagency.internship.food.domain.repository;

import java.util.List;

import fiveagency.internship.food.domain.model.Movie;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface MovieRepository {

    Single<Movie> fetchMovieDetails(int id);

    Single<List<Movie>> fetchMovies(int page);

    Single<List<Movie>> fetchMovies(String title);

    Completable insertMovies(List<Movie> movies);

    Completable setFavorite(final int movieId);

    Completable removeFavorite(Integer movieId);
}
