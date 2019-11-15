package fiveagency.internship.food.movieapp.ui.movieslist;

import com.annimon.stream.Stream;

import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.domain.model.Movie;

public final class MovieViewModelMapper {

    public MovieViewModel mapMovieViewModel(final Movie movie) {
        return new MovieViewModel(movie.id, movie.title, movie.overview, movie.isAdult, movie.releaseDate, movie.imageSource, movie.isFavorite);
    }

    public MoviesListViewModel mapMoviesListViewModel(final List<Movie> movieList) {
        final LinkedList<MovieViewModel> movieViewModels = new LinkedList<>();
        for (final Movie movie : movieList) {
            movieViewModels.add(new MovieViewModel(movie));
        }
        return new MoviesListViewModel(movieViewModels);
    }

    public List<Movie> mapMovies(final List<MovieViewModel> movieViewModels) {
        return Stream.of(movieViewModels).map(
                movie -> new Movie(movie.title, movie.id, movie.isAdult, movie.overview, movie.releaseDate, movie.imageSource, movie.isFavorite, Movie.EMPTY.personalNote,
                                   Movie.EMPTY.tmdbVote, Movie.EMPTY.backdropSource, Movie.EMPTY.imdbId, Movie.EMPTY.videos))
                     .toList();
    }

    public Movie mapMovie(final MovieViewModel movieViewModel) {
        return new Movie(movieViewModel.title,
                         movieViewModel.id,
                         movieViewModel.isAdult,
                         movieViewModel.overview,
                         movieViewModel.releaseDate,
                         movieViewModel.imageSource,
                         false,
                         Movie.EMPTY.personalNote,
                         Movie.EMPTY.tmdbVote,
                         Movie.EMPTY.backdropSource,
                         Movie.EMPTY.imdbId,
                         Movie.EMPTY.videos);
    }
}
