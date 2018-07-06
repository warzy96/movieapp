package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.domain.model.Movie;

public final class MovieViewModelMapper {

    public MovieViewModel mapMovieViewModel(final Movie movie) {
        return new MovieViewModel(movie.title, movie.overview, movie.isAdult, movie.releaseDate);
    }

    public MoviesListViewModel mapMoviesListViewModel(final List<Movie> movieList) {
        LinkedList<MovieViewModel> movieViewModels = new LinkedList<>();
        for (Movie movie : movieList) {
            movieViewModels.add(new MovieViewModel(movie));
        }
        return new MoviesListViewModel(movieViewModels);
    }
}
