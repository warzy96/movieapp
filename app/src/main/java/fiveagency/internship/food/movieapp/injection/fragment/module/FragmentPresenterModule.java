package fiveagency.internship.food.movieapp.injection.fragment.module;

import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentScope;
import fiveagency.internship.food.movieapp.ui.actordetails.ActorDetailsContract;
import fiveagency.internship.food.movieapp.ui.actordetails.ActorDetailsPresenter;
import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesContract;
import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesPresenter;
import fiveagency.internship.food.movieapp.ui.login.LogInContract;
import fiveagency.internship.food.movieapp.ui.login.LogInPresenter;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsContract;
import fiveagency.internship.food.movieapp.ui.moviedetails.MovieDetailsPresenter;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListContract;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListPresenter;
import fiveagency.internship.food.movieapp.ui.pager.ActivityContract;
import fiveagency.internship.food.movieapp.ui.pager.ActivityPresenter;
import fiveagency.internship.food.movieapp.ui.profile.ProfileContract;
import fiveagency.internship.food.movieapp.ui.profile.ProfilePresenter;
import fiveagency.internship.food.movieapp.ui.searchlist.MoviesSearchContract;
import fiveagency.internship.food.movieapp.ui.searchlist.MoviesSearchPresenter;
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
        final MoviesListPresenter moviesListPresenter = new MoviesListPresenter();
        getFragmentComponent().inject(moviesListPresenter);
        return moviesListPresenter;
    }

    @Provides
    @FragmentScope
    public LogInContract.Presenter provideLogInPresenter(final Intent firebaseAuthUIIntent, final FirebaseAuth firebaseAuth) {
        final LogInPresenter logInPresenter = new LogInPresenter(firebaseAuthUIIntent, firebaseAuth);
        getFragmentComponent().inject(logInPresenter);
        return logInPresenter;
    }

    @Provides
    @FragmentScope
    public MovieDetailsContract.Presenter provideMovieDetailsPresenter() {
        final MovieDetailsPresenter movieDetailsPresenter = new MovieDetailsPresenter();
        getFragmentComponent().inject(movieDetailsPresenter);
        return movieDetailsPresenter;
    }

    @Provides
    @FragmentScope
    public MovieFavoritesContract.Presenter provideMovieFavoritesContract() {
        final MovieFavoritesPresenter movieFavoritesPresenter = new MovieFavoritesPresenter();
        getFragmentComponent().inject(movieFavoritesPresenter);
        return movieFavoritesPresenter;
    }

    @Provides
    @FragmentScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @FragmentScope
    public MoviesSearchContract.Presenter provideMoviesSearchPresenter() {
        final MoviesSearchPresenter moviesSearchPresenter = new MoviesSearchPresenter();
        getFragmentComponent().inject(moviesSearchPresenter);
        return moviesSearchPresenter;
    }

    @Provides
    @FragmentScope
    public ActivityContract.Presenter provideActivityPresenter() {
        final ActivityPresenter activityPresenter = new ActivityPresenter();
        getFragmentComponent().inject(activityPresenter);
        return activityPresenter;
    }

    @Provides
    @FragmentScope
    public ProfileContract.Presenter provideProfilePresenter(final FirebaseAuth firebaseAuth) {
        final ProfilePresenter profilePresenter = new ProfilePresenter(firebaseAuth);
        getFragmentComponent().inject(profilePresenter);
        return profilePresenter;
    }

    @Provides
    @FragmentScope
    public ActorDetailsContract.Presenter provideActorDetailsPresenter() {
        final ActorDetailsPresenter actorDetailsPresenter = new ActorDetailsPresenter();
        getFragmentComponent().inject(actorDetailsPresenter);
        return actorDetailsPresenter;
    }
}
