package fiveagency.internship.food.movieapp.injection;

import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.data.repository.MovieRepositoryImpl;
import fiveagency.internship.food.domain.interactor.GetMovieDetailsUseCase;
import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.repository.MovieRepository;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListPresenter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ObjectGraph {

    private final MoviesListPresenter moviesListPresenter;

    public ObjectGraph() {
        final HttpLoggingInterceptor httpLoggingInterceptor = provideHttpLoggingInterceptor();
        final OkHttpClient okHttpClient = provideOkHttpClient(httpLoggingInterceptor);
        final Retrofit retrofit = provideRetrofit(okHttpClient);

        final MovieService movieService = retrofit.create(MovieService.class);
        final MovieMapper movieMapper = new MovieMapper();
        final MovieClient movieClient = new MovieClient(movieService, movieMapper);
        final MovieRepository movieRepository = new MovieRepositoryImpl(movieClient);
        final GetMoviesUseCase getMoviesUseCase = new GetMoviesUseCase(movieRepository);
        final MoviesListFragment moviesListFragment = new MoviesListFragment();
        moviesListPresenter = new MoviesListPresenter(moviesListFragment);
        final GetMovieDetailsUseCase getMovieDetailsUseCase = new GetMovieDetailsUseCase(movieRepository);
    }

    private Retrofit provideRetrofit(final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private OkHttpClient provideOkHttpClient(final HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    private HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
