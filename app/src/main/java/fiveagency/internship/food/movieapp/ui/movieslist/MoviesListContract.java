package fiveagency.internship.food.movieapp.ui.movieslist;

public interface MoviesListContract {

    interface Presenter {

        void start();

        void showMovieDetails(int movieId);

        void fetchMoviesList();
    }

    interface View {

        void render(final MoviesListViewModel moviesListViewModel);
    }
}
