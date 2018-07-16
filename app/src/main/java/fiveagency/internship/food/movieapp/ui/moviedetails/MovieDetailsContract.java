package fiveagency.internship.food.movieapp.ui.moviedetails;

public interface MovieDetailsContract {

    interface Presenter {

        void start(int movieId);
    }

    interface View {

        void render(MovieDetailsViewModel movieDetailsViewModel);
    }
}
