package fiveagency.internship.food.movieapp.injection.application.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.data.database.MovieDatabase;
import fiveagency.internship.food.data.database.crudder.MovieCrudder;
import fiveagency.internship.food.data.database.dao.FavoritesDao;
import fiveagency.internship.food.data.database.dao.FavoritesDao_Impl;
import fiveagency.internship.food.data.database.dao.MovieDao;
import fiveagency.internship.food.data.database.dao.MovieDao_Impl;
import fiveagency.internship.food.data.database.mappers.MovieModelMapper;
import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.data.network.configuration.Urls;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.data.repository.MovieRepositoryImpl;
import fiveagency.internship.food.domain.repository.MovieRepository;
import fiveagency.internship.food.movieapp.injection.application.ForApplication;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class DataModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Urls.RETROFIT_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    MovieMapper provideMovieMapper() {
        return new MovieMapper();
    }

    @Provides
    @Singleton
    MovieService provideMovieService(final Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    @Singleton
    MovieClient provideMovieClient(final MovieService movieService, final MovieMapper mapper) {
        return new MovieClient(movieService, mapper);
    }

    @Provides
    @Singleton
    MovieCrudder provideMovieCrudder(final MovieModelMapper movieModelMapper, final MovieDao movieDao, final FavoritesDao favoritesDao) {
        return new MovieCrudder(movieDao, favoritesDao, movieModelMapper);
    }

    @Provides
    @Singleton
    MovieModelMapper provideMovieModelMapper() {
        return new MovieModelMapper();
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(final MovieDatabase movieDatabase) {
        return new MovieDao_Impl(movieDatabase);
    }

    @Provides
    @Singleton
    FavoritesDao provideFavoritesDao(final MovieDatabase movieDatabase) {
        return new FavoritesDao_Impl(movieDatabase);
    }

    @Provides
    @Singleton
    MovieDatabase provideMovieDatabase(@ForApplication final Context context) {
        return Room.databaseBuilder(context, MovieDatabase.class, "movie-database")
                   .fallbackToDestructiveMigration()
                   .build();
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(final MovieClient movieClient, final MovieCrudder movieCrudder) {
        return new MovieRepositoryImpl(movieClient, movieCrudder);
    }
}
