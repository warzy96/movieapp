package fiveagency.internship.food.movieapp.ui.moviedetails;

public interface MovieDetailsContract {

    interface Presenter {

        void start(int movieId);

        void setView(MovieDetailsContract.View view);

        void savePersonalNote(MovieDetailsViewModel movieDetailsViewModel);
    }

    interface View {

        void render(MovieDetailsViewModel movieDetailsViewModel);
    }
}
