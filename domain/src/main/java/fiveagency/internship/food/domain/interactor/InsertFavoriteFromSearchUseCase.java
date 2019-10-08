package fiveagency.internship.food.domain.interactor;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.CompletableUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Completable;

public final class InsertFavoriteFromSearchUseCase implements CompletableUseCase<Movie> {

    private final MovieRepository movieRepository;

    @Inject
    public InsertFavoriteFromSearchUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Completable execute(final Movie movie) {
        return movieRepository.insertFavorite(movie);
    }
}
