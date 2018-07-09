package fiveagency.internship.food.movieapp.injection;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.data.repository.MovieRepositoryImpl;
import fiveagency.internship.food.domain.interactor.GetMovieDetailsUseCase;
import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.repository.MovieRepository;
import fiveagency.internship.food.movieapp.router.Router;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModelMapper;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListAdapter;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListContract;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListPresenter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ObjectGraph {

    private final GetMoviesUseCase getMoviesUseCase;
    private final GetMovieDetailsUseCase getMovieDetailsUseCase;
    private final MovieViewModelMapper movieViewModelMapper;

    public ObjectGraph() {
        final HttpLoggingInterceptor httpLoggingInterceptor = provideHttpLoggingInterceptor();
        final OkHttpClient okHttpClient = provideOkHttpClient(httpLoggingInterceptor);
        final Retrofit retrofit = provideRetrofit(okHttpClient);

        final MovieService movieService = retrofit.create(MovieService.class);
        final MovieMapper movieMapper = new MovieMapper();
        final MovieClient movieClient = new MovieClient(movieService, movieMapper);
        final MovieRepository movieRepository = new MovieRepositoryImpl(movieClient);

        movieViewModelMapper = new MovieViewModelMapper();
        getMoviesUseCase = new GetMoviesUseCase(movieRepository);
        getMovieDetailsUseCase = new GetMovieDetailsUseCase(movieRepository);
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

    public MoviesListPresenter provideMoviesListPresenter(final MoviesListContract.View view) {
        return new MoviesListPresenter(view, getMoviesUseCase, movieViewModelMapper);
    }

    public GetMoviesUseCase provideGetMoviesUseCase() {
        return getMoviesUseCase;
    }

    public GetMovieDetailsUseCase provideGetMovieDetailsUseCase() {
        return getMovieDetailsUseCase;
    }

    public Router provideRouter(final AppCompatActivity activity) {
        return new Router(activity, activity.getSupportFragmentManager());
    }

    public MoviesListAdapter provideMoviesListAdapter(final Context context) {
        return new MoviesListAdapter(provideLayoutInflater(context));
    }

    private LayoutInflater provideLayoutInflater(final Context context) {
        return LayoutInflater.from(context);
    }
}
