package fiveagency.internship.food.movieapp.ui.movieslist;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteUseCase;
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

    @Override
    public void start() {
        getMoviesUseCase();
    }

    public void getMoviesUseCase() {
        compositeDisposable.add(getMoviesUseCase.execute(DEFAULT_PAGE)
                                                .map(movieViewModelMapper::mapMoviesListViewModel)
                                                .subscribeOn(backgroundScheduler)
                                                .observeOn(mainThreadScheduler)
                                                .subscribe(movieViewModelMapper -> view.render(movieViewModelMapper),
                                                           Throwable::printStackTrace));
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
                                                                Throwable::printStackTrace));
    }

    @Override
    public void removeFavorite(final int movieId) {
        compositeDisposable.add(removeFavoriteUseCase.execute(movieId)
                                                     .subscribeOn(backgroundScheduler)
                                                     .subscribe(() -> {},
                                                                Throwable::printStackTrace));
    }
}
