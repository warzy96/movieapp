package fiveagency.internship.food.movieapp.ui.pager;

public interface ActivityContract {

    interface Presenter {

        void start();

        void setView(ActivityContract.View view);

        void showRecommendedMovies();

        void showFavoriteMovies();

        void showUserProfileScreen();
    }

    interface View {

        void render();
    }
}
