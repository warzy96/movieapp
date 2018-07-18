package fiveagency.internship.food.data.repository;

import java.util.List;

import fiveagency.internship.food.data.database.crudder.MovieCrudder;
import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Completable;
import io.reactivex.Single;

public final class MovieRepositoryImpl implements MovieRepository {

    private final MovieClient movieClient;
    private final MovieCrudder movieCrudder;

    public MovieRepositoryImpl(final MovieClient movieClient, final MovieCrudder movieCrudder) {
        this.movieClient = movieClient;
        this.movieCrudder = movieCrudder;
    }

    @Override
    public Single<Movie> fetchMovieDetails(final int id) {
        return movieClient.getMovieDetails(id);
    }

    @Override
    public Single<List<Movie>> fetchMovies(final int page) {
        return movieClient.getMovies(page)
                          .map(movies -> {
                              movieCrudder.insertMovies(movies);
                              return movies;
                          })
                          .map(movies -> {
                              for (Movie movie : movies) {
                                  movie.isFavorite = movieCrudder.isMovieFavorite(movie.id);
                              }
                              return movies;
                          });
    }

    @Override
    public Single<List<Movie>> fetchMovies(final String title) {
        return movieClient.getMovies(title);
    }

    @Override
    public void insertMovies(final List<Movie> movies) {
        movieCrudder.insertMovies(movies);
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
    public Single<List<Movie>> fetchFavorites() {
        return movieCrudder.getAllFavoriteMovies();
    }
}
