package fiveagency.internship.food.movieapp.ui.moviedetails;

public interface MovieDetailsContract {

    interface Presenter {

        void start(int id);

        void setView(MovieDetailsContract.View view);
    }

    interface View {

        void render(final MovieDetailsViewModel movieDetailsViewModel);
    }
}
