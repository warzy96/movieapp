package fiveagency.internship.food.movieapp.injection.application.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.InsertMoviesUseCase;
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
    InsertMoviesUseCase provideInsertMoviesUseCase(final MovieRepository movieRepository) {
        return new InsertMoviesUseCase(movieRepository);
    }
}
