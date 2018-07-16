package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetMoviesUseCase;
import fiveagency.internship.food.domain.interactor.InsertMoviesUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class MoviesListPresenter extends BasePresenter<MoviesListContract.View> implements MoviesListContract.Presenter {

    private static final int DEFAULT_PAGE = 1;

    @Inject
    GetMoviesUseCase getMoviesUseCase;

    @Inject
    MovieViewModelMapper movieViewModelMapper;

    @Inject
    InsertMoviesUseCase insertMoviesUseCase;

    @Override
    public void start() {
        final Single<List<Movie>> result = getMoviesUseCase.execute(DEFAULT_PAGE);
        compositeDisposable.add(result.map(movieViewModelMapper::mapMoviesListViewModel)
                                      .subscribeOn(Schedulers.io())
                                      .observeOn(AndroidSchedulers.mainThread())
                                      .subscribe(movieViewModelMapper -> view.render(movieViewModelMapper), Throwable::printStackTrace));
        compositeDisposable.add(result.subscribeOn(Schedulers.io())
                                      .observeOn(Schedulers.io())
                                      .subscribe(movies -> insertMoviesUseCase.execute(movies), Throwable::printStackTrace));
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
