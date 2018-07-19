package fiveagency.internship.food.domain.interactor;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.CompletableUseCase;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Completable;

public final class RemoveFavoriteUseCase implements CompletableUseCase<Integer> {

    private final MovieRepository movieRepository;

    @Inject
    public RemoveFavoriteUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Completable execute(final Integer movieId) {
        return movieRepository.removeFavorite(movieId);
    }
}
