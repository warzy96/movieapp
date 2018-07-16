package fiveagency.internship.food.movieapp.ui.moviedetails;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.GetMovieDetailsUseCase;
import fiveagency.internship.food.domain.interactor.type.QueryUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.ui.base.BasePresenter;

public final class MovieDetailsPresenter extends BasePresenter<MovieDetailsContract.View> implements MovieDetailsContract.Presenter {

    @Inject
    GetMovieDetailsUseCase getMovieDetailsUseCase;

    @Inject
    MovieDetailsViewModelMapper movieDetailsViewModelMapper;

    @Override
    public void start(final int id) {
        getMovieDetailsUseCase.execute(id, new QueryUseCase.Callback<Movie>() {

            @Override
            public void onSuccess(final Movie movie) {
                final MovieDetailsViewModel movieDetailsViewModel = movieDetailsViewModelMapper.mapMovieDetailsViewModel(movie);
                view.render(movieDetailsViewModel);
            }

            //TODO: Missing implementation
            @Override
            public void onFailure(final Throwable throwable) {

            }
        });
    }

    @Override
    public void setView(final MovieDetailsContract.View view) {
        this.view = view;
    }
}
