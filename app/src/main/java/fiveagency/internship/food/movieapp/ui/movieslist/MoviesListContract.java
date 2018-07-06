package fiveagency.internship.food.movieapp.ui.movieslist;

public interface MoviesListContract {

    interface Presenter {

        void start();
    }

    interface View {

        void render(final MoviesListViewModel moviesListViewModel);
    }
}
