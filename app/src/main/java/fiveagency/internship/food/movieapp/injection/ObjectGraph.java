package fiveagency.internship.food.movieapp.injection;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import java.util.List;

import fiveagency.internship.food.data.network.client.MovieClient;
import fiveagency.internship.food.data.network.configuration.Urls;
import fiveagency.internship.food.data.network.mappers.MovieMapper;
import fiveagency.internship.food.data.network.service.MovieService;
import fiveagency.internship.food.data.repository.MovieRepositoryImpl;
import fiveagency.internship.food.domain.interactor.GetMovieDetailsUseCase;
import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import fiveagency.internship.food.movieapp.router.Router;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsContract;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsPresenter;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsViewModelMapper;
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
    private final MovieDetailsViewModelMapper movieDetailsViewModelMapper;

    public ObjectGraph() {
        final HttpLoggingInterceptor httpLoggingInterceptor = provideHttpLoggingInterceptor();
        final OkHttpClient okHttpClient = provideOkHttpClient(httpLoggingInterceptor);
        final Retrofit retrofit = provideRetrofit(okHttpClient);

        final MovieService movieService = retrofit.create(MovieService.class);
        final MovieMapper movieMapper = new MovieMapper();
        final MovieClient movieClient = new MovieClient(movieService, movieMapper);
        final MovieRepository movieRepository = new MovieRepositoryImpl(movieClient);

        movieViewModelMapper = new MovieViewModelMapper();
        movieDetailsViewModelMapper = new MovieDetailsViewModelMapper();
        getMoviesUseCase = new GetMoviesUseCase(movieRepository);
        getMovieDetailsUseCase = new GetMovieDetailsUseCase(movieRepository);
    }

    public MovieDetailsContract.Presenter provideMovieDetailsPresenter(final MovieDetailsContract.View view, final Router router) {
        return new MovieDetailsPresenter(view, getMovieDetailsUseCase, movieDetailsViewModelMapper, router);
    }

    private Retrofit provideRetrofit(final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Urls.retrofitBaseUrl)
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

    public MoviesListContract.Presenter provideMoviesListPresenter(final MoviesListContract.View view, Router router) {
        return new MoviesListPresenter(view, getMoviesUseCase, movieViewModelMapper, router);
    }

    public QueryUseCase<Integer, List<Movie>> provideGetMoviesUseCase() {
        return getMoviesUseCase;
    }

    public QueryUseCase<Integer, Movie> provideGetMovieDetailsUseCase() {
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
