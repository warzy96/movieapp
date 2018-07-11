package fiveagency.internship.food.movieapp.injection.application.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.application.ForApplication;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsViewModelMapper;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModelMapper;

@Module
public final class ApplicationModule {

    private final MovieApplication movieApplication;

    public ApplicationModule(final MovieApplication movieApplication) {
        this.movieApplication = movieApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideContext() {
        return movieApplication;
    }

    @Provides
    @Singleton
    MovieApplication provideMovieApplication() {
        return movieApplication;
    }

    @Provides
    @Singleton
    MovieDetailsViewModelMapper provideMovieDetailsViewModelMapper() {
        return new MovieDetailsViewModelMapper();
    }

    @Provides
    @Singleton
    MovieViewModelMapper provideMovieViewModelMapper() {
        return new MovieViewModelMapper();
    }
}
