package fiveagency.internship.food.movieapp.ui.moviedetails;

import javax.inject.Inject;
import javax.inject.Named;

import fiveagency.internship.food.domain.interactor.GetMovieDetailsUseCase;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;
import io.reactivex.Scheduler;

public final class MovieDetailsPresenter extends BasePresenter<MovieDetailsContract.View> implements MovieDetailsContract.Presenter {

    @Inject
    GetMovieDetailsUseCase getMovieDetailsUseCase;

    @Inject
    MovieDetailsViewModelMapper movieDetailsViewModelMapper;

    @Inject
    @Named("IOThread")
    Scheduler ioScheduler;

    @Inject
    @Named("MainThread")
    Scheduler mainThreadScheduler;

    @Override
    public void start(final int id) {
        compositeDisposable.add(getMovieDetailsUseCase.execute(id)
                                                      .map(movieDetailsViewModelMapper::mapMovieDetailsViewModel)
                                                      .subscribeOn(ioScheduler)
                                                      .observeOn(mainThreadScheduler)
                                                      .subscribe(movieDetailsViewModel -> view.render(movieDetailsViewModel),
                                                                 Throwable::printStackTrace));
    }

    @Override
    public void setView(final MovieDetailsContract.View view) {
        this.view = view;
    }
}
