package fiveagency.internship.food.data.network.mappers;

import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.data.network.ApiConstants;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.data.network.model.ApiMoviesList;
import fiveagency.internship.food.domain.model.Movie;

public final class MovieMapper {

    public Movie mapMovie(final ApiMovie apiMovie) {
        return new Movie(apiMovie.title, apiMovie.id, apiMovie.isAdult, apiMovie.overview, apiMovie.releaseDate, ApiConstants.image_source_url + apiMovie.imageSource);
    }

    public List<Movie> mapMovies(final ApiMoviesList apiMoviesList) {
        final List<Movie> movies = new LinkedList<>();
        for (final ApiMovie apiMovie : apiMoviesList.movieApiEntities) {
            movies.add(new Movie(apiMovie.title, apiMovie.id, apiMovie.isAdult, apiMovie.overview, apiMovie.releaseDate, ApiConstants.image_source_url + apiMovie.imageSource));
        }
        return movies;
    }
}
