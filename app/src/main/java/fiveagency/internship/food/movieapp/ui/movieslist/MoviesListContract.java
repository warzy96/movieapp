package fiveagency.internship.food.movieapp.ui.movieslist;

public interface MoviesListContract {

    interface Presenter {

        void start();

        void showMovieDetails(int movieId);

        void getMoviesUseCase();

        void setView(MoviesListContract.View view);

        void insertFavorite(int movieId);

        void removeFavorite(int movieId);
    }

    interface View {

        void render(final MoviesListViewModel moviesListViewModel);
    }
}
