package fiveagency.internship.food.domain.repository;

import java.util.List;

import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.listeners.RepositoryListener;

public interface MovieRepository {

    void fetchMovieDetails(int id, RepositoryListener<Movie> repositoryListener);

    void fetchMovies(int page, RepositoryListener<List<Movie>> repositoryListener);

    void fetchMovies(String title, RepositoryListener<List<Movie>> repositoryListener);
}
