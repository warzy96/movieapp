package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetFlowableMoviesUseCase;
import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.RemoveFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.SaveMoviesUseCase;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;

public final class MoviesListPresenter extends BasePresenter<MoviesListContract.View> implements MoviesListContract.Presenter {

    private static final int DEFAULT_PAGE = 1;
    @Inject
    GetMoviesUseCase getMoviesUseCase;

    @Inject
    InsertFavoriteUseCase insertFavoriteUseCase;

    @Inject
    MovieViewModelMapper movieViewModelMapper;

    @Inject
    RemoveFavoriteUseCase removeFavoriteUseCase;

    @Inject
    GetFlowableMoviesUseCase getFlowableMoviesUseCase;

    @Inject
    SaveMoviesUseCase saveMoviesUseCase;

    @Override
    public void start() {
        getFlowableMoviesUseCase();
    }

    @Override
    public void getFlowableMoviesUseCase() {
        compositeDisposable.add(getFlowableMoviesUseCase.execute(DEFAULT_PAGE)
                                                        .map(movieViewModelMapper::mapMoviesListViewModel)
                                                        .subscribeOn(backgroundScheduler)
                                                        .observeOn(mainThreadScheduler)
                                                        .subscribe(moviesListViewModel -> view.render(moviesListViewModel),
                                                                   throwable -> loggerImpl.log(throwable)));
    }

    public void getAdditionalMovies(final int page) {
        compositeDisposable.add(getFlowableMoviesUseCase.execute(page)
                                                        .map(movieViewModelMapper::mapMoviesListViewModel)
                                                        .subscribeOn(backgroundScheduler)
                                                        .observeOn(mainThreadScheduler)
                                                        .subscribe(moviesListViewModel -> view.appendMovies(moviesListViewModel),
                                                                   throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void showMovieDetails(final int movieId) {
        router.showMovieDetailsScreen(movieId);
    }

    @Override
    public void setView(final MoviesListContract.View view) {
        this.view = view;
    }

    @Override
    public void insertFavorite(final int movieId) {
        compositeDisposable.add(insertFavoriteUseCase.execute(movieId)
                                                     .subscribeOn(backgroundScheduler)
                                                     .subscribe(() -> {},
                                                                throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void removeFavorite(final int movieId) {
        compositeDisposable.add(removeFavoriteUseCase.execute(movieId)
                                                     .subscribeOn(backgroundScheduler)
                                                     .subscribe(() -> {},
                                                                throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void saveMovies(final List<MovieViewModel> movieViewModelList) {
        compositeDisposable.add(saveMoviesUseCase.execute(movieViewModelMapper.mapMovies(movieViewModelList))
                                                 .subscribeOn(backgroundScheduler)
                                                 .subscribe(() -> {},
                                                            throwable -> loggerImpl.log(throwable)));
    }
}
