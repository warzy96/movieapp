package fiveagency.internship.food.movieapp.ui.moviedetails;

import java.util.ArrayList;
import java.util.List;

import fiveagency.internship.food.domain.model.Cast;
import fiveagency.internship.food.domain.model.Movie;

public final class MovieDetailsViewModelMapper {

    public Movie mapMovieDetailsViewModelToMovie(final MovieDetailsViewModel movieDetailsViewModel) {
        return new Movie(movieDetailsViewModel.title, movieDetailsViewModel.id, movieDetailsViewModel.isAdult, movieDetailsViewModel.overview, movieDetailsViewModel.releaseDate,
                         movieDetailsViewModel.imageSource, Movie.EMPTY.isFavorite, movieDetailsViewModel.personalNote, movieDetailsViewModel.tmdbRating,
                         movieDetailsViewModel.backdropPath, movieDetailsViewModel.imdbId, new ArrayList<>(), Movie.EMPTY.genres);
    }

    public MovieDetailsViewModel mapMovieDetailsViewModel(final Movie movie, final List<Cast> cast) {
        return new MovieDetailsViewModel(movie.id, movie.title, movie.overview, movie.isAdult, movie.releaseDate, movie.imageSource, movie.personalNote, cast, movie.tmdbVote,
                                         movie.backdropSource, movie.isFavorite, movie.imdbId, movie.videos, movie.genres);
    }
}
