package fiveagency.internship.food.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.FlowableQueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Flowable;

public final class GetSearchMoviesUseCase implements FlowableQueryUseCase<String, List<Movie>> {

    private final MovieRepository movieRepository;

    @Inject
    public GetSearchMoviesUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Flowable<List<Movie>> execute(final String title) {
        return movieRepository.queryMovies(title);
    }
}
