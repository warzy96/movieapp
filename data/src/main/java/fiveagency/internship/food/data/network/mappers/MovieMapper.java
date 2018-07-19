package fiveagency.internship.food.data.network.mappers;

import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.data.network.ApiConstants;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.data.network.model.ApiMoviesList;
import fiveagency.internship.food.domain.model.Movie;

public final class MovieMapper {

    public Movie mapMovie(final ApiMovie apiMovie) {
        if (apiMovie == null) {
            return Movie.EMPTY;
        }
        return new Movie(apiMovie.title == null ? Movie.EMPTY.title : apiMovie.title,
                         apiMovie.id,
                         apiMovie.isAdult,
                         apiMovie.overview == null ? Movie.EMPTY.overview : apiMovie.overview,
                         apiMovie.releaseDate == null ? Movie.EMPTY.releaseDate : apiMovie.releaseDate,
                         apiMovie.imageSource == null ? Movie.EMPTY.imageSource : parseImageSourceUrl(apiMovie.imageSource), false);
    }

    public List<Movie> mapMovies(final ApiMoviesList apiMoviesList) {
        final List<Movie> movies = new LinkedList<>();
        for (final ApiMovie apiMovie : apiMoviesList.movieApiEntities) {
            movies.add(new Movie(apiMovie.title, apiMovie.id, apiMovie.isAdult, apiMovie.overview, apiMovie.releaseDate,
                                 parseImageSourceUrl(apiMovie.imageSource), false));
        }
        return movies;
    }

    private String parseImageSourceUrl(final String imageSource) {
        return ApiConstants.IMAGE_SOURCE_URL + imageSource;
    }
}
