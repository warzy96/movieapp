package fiveagency.internship.food.data.repository;

import java.util.ArrayList;
import java.util.List;

import fiveagency.internship.food.data.database.crudder.MovieCrudder;
import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public final class MovieRepositoryImpl implements MovieRepository {

    private final MovieClient movieClient;
    private final MovieCrudder movieCrudder;

    public MovieRepositoryImpl(final MovieClient movieClient, final MovieCrudder movieCrudder) {
        this.movieClient = movieClient;
        this.movieCrudder = movieCrudder;
    }

    @Override
    public Flowable<Movie> fetchMovieDetails(final int id) {
        return Flowable.combineLatest(movieClient.getMovieDetails(id).toFlowable(), movieCrudder.movieExists(id).toFlowable(),
                                      (movieDetails, dbMovie) -> {
                                          if (dbMovie.isEmpty()) {
                                              return movieDetails;
                                          } else {
                                              return movieDetails.withPersonalNote(dbMovie.get(0).personalNote);
                                          }
                                      });
    }

    @Override
    public Single<List<Movie>> fetchMovies(final int page) {
        final List<Movie> moviesWithFavorites = new ArrayList<>();
        return Single.zip(movieClient.getMovies(page), movieCrudder.getAllFavoriteMoviesIds(),
                          (movies, favorites) -> {
                              for (final Movie movie : movies) {
                                  if (favorites.contains(movie.id)) {
                                      moviesWithFavorites.add(movie.withIsFavorite(true));
                                  } else {
                                      moviesWithFavorites.add(movie);
                                  }
                              }
                              return moviesWithFavorites;
                          });
    }

    @Override
    public Flowable<List<Movie>> queryMovies(final String title) {
        return Flowable.combineLatest(movieClient.getMovies(title).toFlowable(), movieCrudder.getAllFlowableFavoriteMoviesIds(),
                                      (movies, favorites) -> {
                                          final List<Movie> moviesWithFavorites = new ArrayList<>();
                                          for (final Movie movie : movies) {
                                              if (favorites.contains(movie.id)) {
                                                  moviesWithFavorites.add(movie.withIsFavorite(true));
                                              } else {
                                                  moviesWithFavorites.add(movie);
                                              }
                                          }
                                          return moviesWithFavorites;
                                      });
    }

    @Override
    public Completable insertMovies(final List<Movie> movies) {
        return movieCrudder.insertMovies(movies);
    }

    @Override
    public Completable setFavorite(final int movieId) {
        return movieCrudder.setFavorite(movieId);
    }

    @Override
    public Completable removeFavorite(final Integer movieId) {
        return movieCrudder.removeFavorite(movieId);
    }

    @Override
    public Single<List<Integer>> fetchFavoritesIds() {
        return movieCrudder.getAllFavoriteMoviesIds();
    }

    @Override
    public Flowable<List<Movie>> fetchFavorites() {
        return movieCrudder.getAllFavoriteMovies();
    }

    @Override
    public Flowable<List<Movie>> fetchFlowableMovies(final int page) {
        return Flowable.combineLatest(movieClient.getFlowableMovies(page), movieCrudder.getAllFlowableFavoriteMoviesIds(),
                                      (movies, favorites) -> {
                                          final List<Movie> moviesWithFavorites = new ArrayList<>();
                                          for (final Movie movie : movies) {
                                              if (favorites.contains(movie.id)) {
                                                  moviesWithFavorites.add(movie.withIsFavorite(true));
                                              } else {
                                                  moviesWithFavorites.add(movie);
                                              }
                                          }
                                          insertMovies(moviesWithFavorites);
                                          return moviesWithFavorites;
                                      });
    }

    @Override
    public Completable insertFavorite(final Movie movie) {
        return movieCrudder.insertMovie(movie);
    }

    @Override
    public Completable setPersonalNote(final Movie movie) {
        return movieCrudder.setPersonalNote(movie);
    }
}
