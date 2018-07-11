package fiveagency.internship.food.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import fiveagency.internship.food.domain.repository.listeners.RepositoryListener;

public final class GetMoviesUseCase implements QueryUseCase<Integer, List<Movie>> {

    private final MovieRepository movieRepository;

    public GetMoviesUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void execute(final Integer page, final Callback<List<Movie>> callback) {
        movieRepository.fetchMovies(page, new RepositoryListener<List<Movie>>() {

            @Override
            public void onResult(final List<Movie> movies) {
                callback.onSuccess(movies);
            }

            @Override
            public void onFailure(final Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
