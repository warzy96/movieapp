package fiveagency.internship.food.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.CompletableUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;

public final class InsertMoviesUseCase implements CompletableUseCase<List<Movie>> {

    private final MovieRepository movieRepository;

    @Inject
    public InsertMoviesUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void execute(final List<Movie> movies) {
        movieRepository.insertMovies(movies);
    }
}
