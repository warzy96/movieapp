package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.List;

import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;

public final class MoviesListPresenter extends BasePresenter<MoviesListContract.View> implements MoviesListContract.Presenter {

    private static final int defaultPage = 1;
    private final GetMoviesUseCase getMoviesUseCase;
    private final MovieViewModelMapper movieViewModelMapper;

    public MoviesListPresenter(final MoviesListContract.View view, final GetMoviesUseCase getMoviesUseCase,
                               final MovieViewModelMapper movieViewModelMapper) {
        super(view);
        this.getMoviesUseCase = getMoviesUseCase;
        this.movieViewModelMapper = movieViewModelMapper;
    }

    @Override
    public void start() {
        getMoviesUseCase.execute(defaultPage, new QueryUseCase.Callback<List<Movie>>() {

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
}
