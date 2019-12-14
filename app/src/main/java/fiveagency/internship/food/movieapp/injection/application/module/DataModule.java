package fiveagency.internship.food.movieapp.injection.application.module;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.data.database.MovieDatabase;
import fiveagency.internship.food.data.database.crudder.MovieCrudder;
import fiveagency.internship.food.data.database.dao.FavoritesDao;
import fiveagency.internship.food.data.database.dao.MovieDao;
import fiveagency.internship.food.data.database.mappers.MovieModelMapper;
import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.data.network.client.WeatherClient;
import fiveagency.internship.food.data.network.configuration.Urls;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.data.network.service.OmdbService;
import fiveagency.internship.food.data.network.service.WeatherService;
import fiveagency.internship.food.data.repository.MovieRepositoryImpl;
import fiveagency.internship.food.data.repository.WeatherRepositoryImpl;
import fiveagency.internship.food.domain.repository.MovieRepository;
import fiveagency.internship.food.domain.repository.WeatherRepository;
import fiveagency.internship.food.movieapp.injection.application.ForApplication;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static fiveagency.internship.food.data.database.MovieDatabase.DB_NAME;

@Module
public final class DataModule {

    private static final String OMDB_RETROFIT = "omdbRetrofit";
    private static final String WEATHER_RETROFIT = "weatherRetrofit";

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
    @Named(OMDB_RETROFIT)
    Retrofit provideOmdbRetrofit(final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Urls.OMDB_RETROFIT_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named(WEATHER_RETROFIT)
    Retrofit provideWeatherRetrofit(final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Urls.WEATHER_RETROFIT_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OmdbService provideOmdbService(@Named(OMDB_RETROFIT) final Retrofit retrofit) {
        return retrofit.create(OmdbService.class);
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
    WeatherService provideWeatherService(@Named(WEATHER_RETROFIT) final Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    @Provides
    @Singleton
    MovieClient provideMovieClient(final MovieService movieService, final MovieMapper mapper, final OmdbService omdbService) {
        return new MovieClient(movieService, mapper, omdbService);
    }

    @Provides
    @Singleton
    WeatherClient provideWeatherClient(final WeatherService weatherService) {
        return new WeatherClient(weatherService);
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
        return movieDatabase.movieDao();
    }

    @Provides
    @Singleton
    FavoritesDao provideFavoritesDao(final MovieDatabase movieDatabase) {
        return movieDatabase.favoritesDao();
    }

    @Provides
    @Singleton
    MovieDatabase provideMovieDatabase(@ForApplication final Context context) {
        return Room.databaseBuilder(context, MovieDatabase.class, DB_NAME)
                   .fallbackToDestructiveMigration()
                   .build();
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(final MovieClient movieClient, final MovieCrudder movieCrudder) {
        return new MovieRepositoryImpl(movieClient, movieCrudder);
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(final WeatherClient weatherClient) {
        return new WeatherRepositoryImpl(weatherClient);
    }
}
