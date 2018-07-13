package fiveagency.internship.food.data.repository;

import java.util.List;

import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Single;

public final class MovieRepositoryImpl implements MovieRepository {

    private final MovieClient movieClient;

    public MovieRepositoryImpl(final MovieClient movieClient) {
        this.movieClient = movieClient;
    }

    @Override
    public Single<Movie> fetchMovieDetails(final int id) {
        return movieClient.getMovieDetails(id);
    }

    @Override
    public Single<List<Movie>> fetchMovies(final int page) {
        return movieClient.getMovies(page);
    }

    @Override
    public Single<List<Movie>> fetchMovies(final String title) {
        return movieClient.getMovies(title);
    }
}
