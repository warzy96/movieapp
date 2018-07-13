package fiveagency.internship.food.movieapp.ui.movieslist;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class MoviesListPresenter extends BasePresenter<MoviesListContract.View> implements MoviesListContract.Presenter {

    private static final int DEFAULT_PAGE = 1;
    @Inject
    GetMoviesUseCase getMoviesUseCase;
    @Inject
    MovieViewModelMapper movieViewModelMapper;

    @Override
    public void start() {
        compositeDisposable.add(getMoviesUseCase.execute(DEFAULT_PAGE)
                                                .map(movieViewModelMapper::mapMoviesListViewModel)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(movieViewModelMapper -> view.render(movieViewModelMapper), Throwable::printStackTrace));
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
