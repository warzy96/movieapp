package fiveagency.internship.food.domain.interactor;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.CompletableUseCase;
import fiveagency.internship.food.domain.model.FavoriteMovie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Completable;

public final class InsertFavoriteUseCase implements CompletableUseCase<FavoriteMovie> {

    private final MovieRepository movieRepository;

    @Inject
    public InsertFavoriteUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Completable execute(final FavoriteMovie favoriteMovie) {
        return movieRepository.setFavorite(favoriteMovie);
    }
}
