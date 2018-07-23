package fiveagency.internship.food.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.CompletableUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Completable;

public final class SaveMoviesUseCase implements CompletableUseCase<List<Movie>> {

    private final MovieRepository movieRepository;

    @Inject
    public SaveMoviesUseCase(final MovieRepository movieRepository) {this.movieRepository = movieRepository;}

    @Override
    public Completable execute(final List<Movie> movies) {
        return movieRepository.insertMovies(movies);
    }
}
