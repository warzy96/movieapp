package fiveagency.internship.food.domain.interactor;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.FlowableQueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Flowable;

public final class GetMovieDetailsUseCase implements FlowableQueryUseCase<Integer, Movie> {

    private final MovieRepository movieRepository;

    @Inject
    public GetMovieDetailsUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Flowable<Movie> execute(final Integer id) {
        return movieRepository.fetchMovieDetails(id);
    }
}
