package fiveagency.internship.food.movieapp.injection.fragment.module;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentScope;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsContract;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsPresenter;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListContract;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListPresenter;
import io.reactivex.disposables.CompositeDisposable;

@Module
public final class FragmentPresenterModule {

    private final DaggerFragment daggerFragment;

    public FragmentPresenterModule(final DaggerFragment daggerFragment) {
        this.daggerFragment = daggerFragment;
    }

    private FragmentComponent getFragmentComponent() {
        return daggerFragment.getFragmentComponent();
    }

    @Provides
    @FragmentScope
    public MoviesListContract.Presenter provideMoviesListPresenter() {
        MoviesListPresenter moviesListPresenter = new MoviesListPresenter();
        getFragmentComponent().inject(moviesListPresenter);
        return moviesListPresenter;
    }

    @Provides
    @FragmentScope
    public MovieDetailsContract.Presenter provideMovieDetailsPresenter() {
        MovieDetailsPresenter movieDetailsPresenter = new MovieDetailsPresenter();
        getFragmentComponent().inject(movieDetailsPresenter);
        return movieDetailsPresenter;
    }

    @Provides
    @FragmentScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
