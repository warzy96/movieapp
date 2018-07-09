package fiveagency.internship.food.movieapp.ui.moviedetails;

import fiveagency.internship.food.domain.model.Movie;

public final class MovieDetailsViewModelMapper {

    public MovieDetailsViewModel mapMovieDetailsViewModel(final Movie movie) {
        return new MovieDetailsViewModel(movie.id, movie.title, movie.overview, movie.isAdult, movie.releaseDate);
    }
}
