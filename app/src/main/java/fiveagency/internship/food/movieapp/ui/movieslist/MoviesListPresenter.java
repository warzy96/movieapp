package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;

public final class MoviesListPresenter extends BasePresenter<MoviesListContract.View> implements MoviesListContract.Presenter {

    private static final int DEFAULT_PAGE = 1;
    @Inject
    GetMoviesUseCase getMoviesUseCase;

    @Inject
    MovieViewModelMapper movieViewModelMapper;

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

    @Override
    public void setView(final MoviesListContract.View view) {
        this.view = view;
    }
}
