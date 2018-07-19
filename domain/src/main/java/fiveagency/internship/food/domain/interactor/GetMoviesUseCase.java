package fiveagency.internship.food.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.SingleQueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Single;

public final class GetMoviesUseCase implements SingleQueryUseCase<Integer, List<Movie>> {

    private final MovieRepository movieRepository;

    @Inject
    public GetMoviesUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Single<List<Movie>> execute(final Integer page) {
        return movieRepository.fetchMovies(page);
    }
}
