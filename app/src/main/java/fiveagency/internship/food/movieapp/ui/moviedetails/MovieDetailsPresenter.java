package fiveagency.internship.food.movieapp.ui.moviedetails;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetMovieCastUseCase;
import fiveagency.internship.food.domain.interactor.GetMovieDetailsUseCase;
import fiveagency.internship.food.domain.interactor.GetMovieRatingsUseCase;
import fiveagency.internship.food.domain.interactor.InsertFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.RemoveFavoriteUseCase;
import fiveagency.internship.food.domain.interactor.SavePersonalNoteUseCase;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;
import io.reactivex.Single;

public final class MovieDetailsPresenter extends BasePresenter<MovieDetailsContract.View> implements MovieDetailsContract.Presenter {

    @Inject
    GetMovieDetailsUseCase getMovieDetailsUseCase;

    @Inject
    GetMovieCastUseCase getMovieCastUseCase;

    @Inject
    MovieDetailsViewModelMapper movieDetailsViewModelMapper;

    @Inject
    SavePersonalNoteUseCase savePersonalNoteUseCase;

    @Inject
    InsertFavoriteUseCase insertFavoriteUseCase;

    @Inject
    RemoveFavoriteUseCase removeFavoriteUseCase;

    @Inject
    GetMovieRatingsUseCase getMovieRatingsUseCase;

    @Override
    public void start(final int id) {
        compositeDisposable.add(
                Single.zip(
                        getMovieDetailsUseCase.execute(id),
                        getMovieCastUseCase.execute(id),
                        (movie, cast) -> movieDetailsViewModelMapper.mapMovieDetailsViewModel(movie, cast)
                )
                      .subscribeOn(backgroundScheduler)
                      .observeOn(mainThreadScheduler)
                      .subscribe(movieDetailsViewModel -> {
                          view.render(movieDetailsViewModel);
                          fetchRatings(movieDetailsViewModel.imdbId);
                      }, throwable -> loggerImpl.log(throwable)));
    }

    @Override
    public void setView(final MovieDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void savePersonalNote(final MovieDetailsViewModel movieDetailsViewModel) {
        compositeDisposable.add(
                savePersonalNoteUseCase.execute(movieDetailsViewModelMapper.mapMovieDetailsViewModelToMovie(movieDetailsViewModel))
                                       .subscribeOn(backgroundScheduler)
                                       .observeOn(mainThreadScheduler)
                                       .subscribe(() -> {},
                                                  throwable -> loggerImpl.log(throwable)));
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
    public void goBack() {
        router.goBack();
    }

    private void fetchRatings(final String imdbId) {
        compositeDisposable.add(
                getMovieRatingsUseCase.execute(imdbId)
                                      .subscribeOn(backgroundScheduler)
                                      .observeOn(mainThreadScheduler)
                                      .subscribe(ratings -> view.renderRatings(ratings), throwable -> loggerImpl.log(throwable))
        );
    }
}
