package fiveagency.internship.food.movieapp.injection.application.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.domain.interactor.FetchFiveDayForecastUseCase;
import fiveagency.internship.food.domain.interactor.GetFavoritesUseCase;
import fiveagency.internship.food.domain.interactor.GetFlowableMoviesUseCase;
import fiveagency.internship.food.domain.interactor.GetMovieCastUseCase;
import fiveagency.internship.food.domain.interactor.GetMovieRatingsUseCase;
import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.GetRandomBeerUseCase;
import fiveagency.internship.food.domain.interactor.GetSearchMoviesUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteFromSearchUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.RemoveFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.SaveMoviesUseCase;
import fiveagency.internship.food.domain.interactor.SavePersonalNoteUseCase;
import fiveagency.internship.food.domain.interactor.type.GetActorDetailsUseCase;
import fiveagency.internship.food.domain.repository.BeerRepository;
import fiveagency.internship.food.domain.repository.MovieRepository;
import fiveagency.internship.food.domain.repository.WeatherRepository;

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

    @Provides
    @Singleton
    GetFlowableMoviesUseCase provideGetFlowableMoviesUseCase(final MovieRepository movieRepository) {
        return new GetFlowableMoviesUseCase(movieRepository);
    }

    @Provides
    @Singleton
    GetSearchMoviesUseCase provideGetSearchMoviesUseCase(final MovieRepository movieRepository) {
        return new GetSearchMoviesUseCase(movieRepository);
    }

    @Provides
    @Singleton
    InsertFavoriteFromSearchUseCase provideInsertFavoriteFromSearchUseCase(final MovieRepository movieRepository) {
        return new InsertFavoriteFromSearchUseCase(movieRepository);
    }

    @Provides
    @Singleton
    SavePersonalNoteUseCase provideSavePersonalNoteUseCase(final MovieRepository movieRepository) {
        return new SavePersonalNoteUseCase(movieRepository);
    }

    @Provides
    @Singleton
    SaveMoviesUseCase provideSaveMoviesUseCase(final MovieRepository movieRepository) {
        return new SaveMoviesUseCase(movieRepository);
    }

    @Provides
    @Singleton
    GetMovieCastUseCase provideGetMovieCastUseCase(final MovieRepository movieRepository) {
        return new GetMovieCastUseCase(movieRepository);
    }

    @Provides
    @Singleton
    GetMovieRatingsUseCase provideGetMovieRatingsUseCase(final MovieRepository movieRepository) {
        return new GetMovieRatingsUseCase(movieRepository);
    }

    @Provides
    @Singleton
    FetchFiveDayForecastUseCase provideFetchFiveDayForecastUseCase(final WeatherRepository weatherRepository) {
        return new FetchFiveDayForecastUseCase(weatherRepository);
    }

    @Provides
    @Singleton
    GetActorDetailsUseCase provideGetActorDetailsUseCase(final MovieRepository movieRepository) {
        return new GetActorDetailsUseCase(movieRepository);
    }

    @Provides
    @Singleton
    GetRandomBeerUseCase provideGetRandomBeerUseCase(final BeerRepository beerRepository) {
        return new GetRandomBeerUseCase(beerRepository);
    }
}
