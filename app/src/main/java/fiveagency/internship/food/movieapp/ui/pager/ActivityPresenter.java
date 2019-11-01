package fiveagency.internship.food.movieapp.ui.pager;

import fiveagency.internship.food.movieapp.ui.base.BasePresenter;

public final class ActivityPresenter extends BasePresenter<ActivityContract.View> implements ActivityContract.Presenter {

    @Override
    public void start() {
        view.render();
    }

    @Override
    public void setView(final ActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void showRecommendedMovies() {
        router.showMoviesListScreen();
    }

    @Override
    public void showFavoriteMovies() {
        router.showFavoriteMoviesScreen();
    }

    @Override
    public void showUserProfileScreen() {
        router.showUserProfileScreen();
    }
}
