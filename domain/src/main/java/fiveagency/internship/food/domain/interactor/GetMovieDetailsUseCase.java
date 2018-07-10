package fiveagency.internship.food.domain.interactor;

import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import fiveagency.internship.food.domain.repository.listeners.RepositoryListener;

public final class GetMovieDetailsUseCase implements QueryUseCase<Integer, Movie> {

    private final MovieRepository movieRepository;

    public GetMovieDetailsUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void execute(final Integer id, final Callback<Movie> callback) {
        movieRepository.fetchMovieDetails(id, new RepositoryListener<Movie>() {

            @Override
            public void onResult(final Movie movie) {
                callback.onSuccess(movie);
            }

            @Override
            public void onFailure(final Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
