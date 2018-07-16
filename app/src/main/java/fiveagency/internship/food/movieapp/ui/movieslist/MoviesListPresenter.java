package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.List;

import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.router.Router;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;

public final class MoviesListPresenter extends BasePresenter<MoviesListContract.View> implements MoviesListContract.Presenter {

    private static final int DEFAULT_PAGE = 1;
    private final GetMoviesUseCase getMoviesUseCase;
    private final MovieViewModelMapper movieViewModelMapper;
    private final Router router;

    public MoviesListPresenter(final MoviesListContract.View view, final GetMoviesUseCase getMoviesUseCase,
                               final MovieViewModelMapper movieViewModelMapper, final Router router) {
        super(view);
        this.getMoviesUseCase = getMoviesUseCase;
        this.movieViewModelMapper = movieViewModelMapper;
        this.router = router;
    }

    @Override
    public void start() {
        getMoviesUseCase();
    }

    public void getMoviesUseCase() {
        getMoviesUseCase.execute(DEFAULT_PAGE, new QueryUseCase.Callback<List<Movie>>() {

            @Override
            public void onSuccess(final List<Movie> movieList) {
                final MoviesListViewModel moviesListViewModel = movieViewModelMapper.mapMoviesListViewModel(movieList);
                view.render(moviesListViewModel);
            }

            //TODO: missing implementation
            @Override
            public void onFailure(final Throwable throwable) {

            }
        });
    }

    @Override
    public void showMovieDetails(final int movieId) {
        router.showMovieDetailsScreen(movieId);
    }
}
