package fiveagency.internship.food.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.FlowableQueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Flowable;

public final class GetFlowableMoviesUseCase implements FlowableQueryUseCase<Integer, List<Movie>> {

    private final MovieRepository movieRepository;

    public GetFlowableMoviesUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Inject

    @Override
    public Flowable<List<Movie>> execute(final Integer page) {
        return movieRepository.fetchFlowableMovies(page);
    }
}
