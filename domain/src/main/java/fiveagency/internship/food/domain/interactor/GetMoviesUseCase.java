package fiveagency.internship.food.domain.interactor;

import java.util.List;

import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Single;

public final class GetMoviesUseCase implements QueryUseCase<Integer, Single<List<Movie>>> {

    private final MovieRepository movieRepository;

    public GetMoviesUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Single<List<Movie>> execute(final Integer page) {
        return movieRepository.fetchMovies(page);
    }
}
