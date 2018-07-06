package fiveagency.internship.food.domain.repository;

import java.util.List;

import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.listeners.RepositoryListener;

public interface MovieRepository {

    void getMovieDetails(int id, RepositoryListener<Movie> repositoryListener);

    void getMovies(int page, RepositoryListener<List<Movie>> repositoryListener);

    void getMovies(String title, RepositoryListener<List<Movie>> repositoryListener);
}
