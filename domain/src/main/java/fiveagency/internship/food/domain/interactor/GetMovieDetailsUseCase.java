package fiveagency.internship.food.domain.interactor;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.SingleQueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Single;

public final class GetMovieDetailsUseCase implements SingleQueryUseCase<Integer, Movie> {

    private final MovieRepository movieRepository;

    @Inject
    public GetMovieDetailsUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Single<Movie> execute(final Integer id) {
        return movieRepository.fetchMovieDetails(id);
    }
}
