package fiveagency.internship.food.movieapp.ui.favoriteslist;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetFavoritesUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.RemoveFavoriteUseCase;
import fiveagency.internship.food.domain.model.FavoriteMovie;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;
import fiveagency.internship.food.movieapp.ui.movieslist.FavoriteMovieModel;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModelMapper;

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
        getFavoritesUseCase();
    }

    public void getFavoritesUseCase() {
        compositeDisposable.add(getFavoritesUseCase.execute()
                                                   .map(movieViewModelMapper::mapMoviesListViewModel)
                                                   .subscribeOn(backgroundScheduler)
                                                   .observeOn(mainThreadScheduler)
                                                   .subscribe(moviesListViewModel -> view.render(moviesListViewModel),
                                                              throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void insertFavorite(final FavoriteMovieModel favoriteMovieModel) {
        compositeDisposable.add(insertFavoriteUseCase.execute(new FavoriteMovie(favoriteMovieModel.getMovieId(), favoriteMovieModel.getGenres()))
                                                     .subscribeOn(backgroundScheduler)
                                                     .subscribe(() -> {},
                                                                throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void removeFavorite(final FavoriteMovieModel favoriteMovieModel) {
        compositeDisposable.add(removeFavoriteUseCase.execute(new FavoriteMovie(favoriteMovieModel.getMovieId(), favoriteMovieModel.getGenres()))
                                                     .subscribeOn(backgroundScheduler)
                                                     .subscribe(() -> {},
                                                                throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void showMovieDetails(final int movieId) {
        router.showMovieDetailsScreen(movieId);
    }

    @Override
    public void setView(final MovieFavoritesContract.View view) {
        this.view = view;
    }
}
