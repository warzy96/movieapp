package fiveagency.internship.food.movieapp.injection.application.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.domain.interactor.GetFavoritesUseCase;
import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.RemoveFavoriteUseCase;
import fiveagency.internship.food.domain.repository.MovieRepository;

@Module
public final class UseCaseModule {

    @Provides
    @Singleton
    GetMoviesUseCase provideGetMoviesUseCase(final MovieRepository movieRepository) {
        return new GetMoviesUseCase(movieRepository);
    }

    @Provides
    @Singleton
    InsertFavoriteUseCase provideInsertFavoriteUseCase(final MovieRepository movieRepository) {
        return new InsertFavoriteUseCase(movieRepository);
    }

    @Provides
    @Singleton
    RemoveFavoriteUseCase provideRemoveFavoriteUseCase(final MovieRepository movieRepository) {
        return new RemoveFavoriteUseCase(movieRepository);
    }

    @Provides
    @Singleton
    GetFavoritesUseCase provideGetFavoritesUseCase(final MovieRepository movieRepository) {
        return new GetFavoritesUseCase(movieRepository);
    }
}
