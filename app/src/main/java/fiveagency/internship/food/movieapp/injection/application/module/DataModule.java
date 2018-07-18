package fiveagency.internship.food.movieapp.injection.application.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.data.network.configuration.Urls;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.data.repository.MovieRepositoryImpl;
import fiveagency.internship.food.domain.repository.MovieRepository;
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
    MovieRepository provideMovieRepository(final MovieClient movieClient) {
        return new MovieRepositoryImpl(movieClient);
    }
}
