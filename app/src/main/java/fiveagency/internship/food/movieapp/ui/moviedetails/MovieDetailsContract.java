package fiveagency.internship.food.movieapp.ui.moviedetails;

public interface MovieDetailsContract {

    interface Presenter {

        void start(int movieId);

        void setView(MovieDetailsContract.View view);
    }

    interface View {

        void render(MovieDetailsViewModel movieDetailsViewModel);
    }
}
