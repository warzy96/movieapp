package fiveagency.internship.food.movieapp.ui.pager;

public interface ActivityContract {

    interface Presenter {

        void start();

        void setView(ActivityContract.View view);

        void showRecommendedMovies();

        void showFavoriteMovies();
    }

    interface View {

        void render();
    }
}
