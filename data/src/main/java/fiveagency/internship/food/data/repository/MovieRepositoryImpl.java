package fiveagency.internship.food.data.repository;

import java.util.List;

import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.data.network.client.ResponseListener;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import fiveagency.internship.food.domain.repository.listeners.RepositoryListener;

public final class MovieRepositoryImpl implements MovieRepository {

    private final MovieClient movieClient;

    public MovieRepositoryImpl(final MovieClient movieClient) {
        this.movieClient = movieClient;
    }

    @Override
    public void getMovieDetails(final int id, RepositoryListener<Movie> repositoryListener) {
        movieClient.getMovieDetails(id, new ResponseListener<Movie>() {

            @Override
            public void onResult(final Movie movie) {
                repositoryListener.onResult(movie);
            }

            @Override
            public void onFailure(final Throwable t) {
                repositoryListener.onFailure(t);
            }
        });
    }

    @Override
    public void getMovies(final int page, RepositoryListener<List<Movie>> repositoryListener) {
        movieClient.getMovies(new ResponseListener<List<Movie>>() {

            @Override
            public void onResult(final List<Movie> movies) {
                repositoryListener.onResult(movies);
            }

            @Override
            public void onFailure(final Throwable t) {
                repositoryListener.onFailure(t);
            }
        }, page);
    }

    @Override
    public void getMovies(final String title, final RepositoryListener<List<Movie>> repositoryListener) {
        movieClient.getMovies(new ResponseListener<List<Movie>>() {

            @Override
            public void onResult(final List<Movie> movies) {
                repositoryListener.onResult(movies);
            }

            @Override
            public void onFailure(final Throwable t) {
                repositoryListener.onFailure(t);
            }
        }, title);
    }
}
