package fiveagency.internship.food.movieapp.ui.searchlist;

import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetSearchMoviesUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteFromSearchUseCase;
import fiveagency.internship.food.domain.interactor.RemoveFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.SaveMoviesUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModel;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModelMapper;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public final class MoviesSearchPresenter extends BasePresenter<MoviesSearchContract.View> implements MoviesSearchContract.Presenter {

    @Inject
    public MovieViewModelMapper movieViewModelMapper;

    @Inject
    InsertFavoriteFromSearchUseCase insertFavoriteFromSearchUseCase;

    @Inject
    RemoveFavoriteUseCase removeFavoriteUseCase;

    @Inject
    GetSearchMoviesUseCase getSearchMoviesUseCase;

    @Inject
    SaveMoviesUseCase saveMoviesUseCase;

    @Override
    public void start(final EditText searchEditText) {
        initEditTextSearch(searchEditText);
    }

    private void initEditTextSearch(final EditText searchEditText) {
        compositeDisposable.add(RxTextView.textChanges(searchEditText)
                                          .map(CharSequence::toString)
                                          .debounce(300, TimeUnit.MILLISECONDS)
                                          .filter(text -> !text.isEmpty())
                                          .distinctUntilChanged()
                                          .switchMap((Function<String, ObservableSource<List<Movie>>>) title -> getSearchMoviesUseCase.execute(title).toObservable())
                                          .subscribeOn(backgroundScheduler)
                                          .observeOn(mainThreadScheduler)
                                          .map(movies -> {
                                              final List<Movie> moviesToSave = new ArrayList<>();
                                              moviesToSave.addAll(movies);
                                              compositeDisposable.add(saveMoviesUseCase.execute(moviesToSave).subscribeOn(backgroundScheduler)
                                                                                       .subscribe(() -> {}, throwable -> loggerImpl.log(throwable)));
                                              return movieViewModelMapper.mapMoviesListViewModel(movies);
                                          })
                                          .subscribe(movieViewModel -> view.render(movieViewModel),
                                                     throwable -> loggerImpl.log(throwable)));
    }

    public void refreshSearch(final String title) {
        compositeDisposable.add(getSearchMoviesUseCase.execute(title)
                                                      .subscribeOn(backgroundScheduler)
                                                      .observeOn(mainThreadScheduler)
                                                      .map(movies -> movieViewModelMapper.mapMoviesListViewModel(movies))
                                                      .subscribe(moviesListViewModel -> view.render(moviesListViewModel),
                                                                 throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void showMovieDetails(final int movieId) {
        router.showMovieDetailsScreen(movieId);
    }

    @Override
    public void setView(final MoviesSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void insertFavorite(final MovieViewModel movie) {
        compositeDisposable.add(insertFavoriteFromSearchUseCase.execute(movieViewModelMapper.mapMovie(movie))
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
}
