package fiveagency.internship.food.movieapp.ui.favoriteslist;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetFavoritesUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.RemoveFavoriteUseCase;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModelMapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class MovieFavoritesPresenter extends BasePresenter<MovieFavoritesContract.View> implements MovieFavoritesContract.Presenter {

    @Inject
    GetFavoritesUseCase getFavoritesUseCase;

    @Inject
    MovieViewModelMapper movieViewModelMapper;

    @Inject
    InsertFavoriteUseCase insertFavoriteUseCase;

    @Inject
    RemoveFavoriteUseCase removeFavoriteUseCase;

    @Override
    public void start() {
        compositeDisposable.add(getFavoritesUseCase.execute()
                                                   .map(movieViewModelMapper::mapMoviesListViewModel)
                                                   .subscribeOn(Schedulers.io())
                                                   .observeOn(AndroidSchedulers.mainThread())
                                                   .subscribe(movieViewModel -> view.render(movieViewModel),
                                                              Throwable::printStackTrace));
    }

    @Override
    public void insertFavorite(final int movieId) {
        compositeDisposable.add(insertFavoriteUseCase.execute(movieId)
                                                     .subscribeOn(Schedulers.io())
                                                     .observeOn(Schedulers.io())
                                                     .subscribe(() -> {},
                                                                Throwable::printStackTrace));
    }

    @Override
    public void removeFavorite(final int movieId) {
        compositeDisposable.add(removeFavoriteUseCase.execute(movieId)
                                                     .subscribeOn(Schedulers.io())
                                                     .observeOn(Schedulers.io())
                                                     .subscribe(() -> {},
                                                                Throwable::printStackTrace));
    }

    @Override
    public void setView(final MovieFavoritesContract.View view) {
        this.view = view;
    }
}
