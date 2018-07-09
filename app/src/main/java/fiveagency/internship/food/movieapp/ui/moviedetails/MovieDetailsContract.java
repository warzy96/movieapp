package fiveagency.internship.food.movieapp.ui.moviedetails;

public interface MovieDetailsContract {

    interface Presenter {

        void start(int id);
    }

    interface View {

        void render(final MovieDetailsViewModel movieDetailsViewModel);
    }
}
