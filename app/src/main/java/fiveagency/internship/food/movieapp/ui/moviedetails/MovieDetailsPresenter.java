package fiveagency.internship.food.movieapp.ui.moviedetails;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetMovieDetailsUseCase;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;

public final class MovieDetailsPresenter extends BasePresenter<MovieDetailsContract.View> implements MovieDetailsContract.Presenter {

    @Inject
    GetMovieDetailsUseCase getMovieDetailsUseCase;

    @Inject
    MovieDetailsViewModelMapper movieDetailsViewModelMapper;

    @Override
    public void start(final int id) {
        compositeDisposable.add(getMovieDetailsUseCase.execute(id)
                                                      .map(movieDetailsViewModelMapper::mapMovieDetailsViewModel)
                                                      .subscribeOn(backgroundScheduler)
                                                      .observeOn(mainThreadScheduler)
                                                      .subscribe(movieDetailsViewModel -> view.render(movieDetailsViewModel),
                                                                 Throwable::printStackTrace));
    }

    @Override
    public void setView(final MovieDetailsContract.View view) {
        this.view = view;
    }
}
