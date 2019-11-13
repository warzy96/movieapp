package fiveagency.internship.food.data.network.mappers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.data.network.ApiConstants;
import fiveagency.internship.food.data.network.model.ApiCast;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.data.network.model.ApiMoviesList;
import fiveagency.internship.food.data.network.model.ApiRating;
import fiveagency.internship.food.data.network.model.ApiRatings;
import fiveagency.internship.food.domain.model.Cast;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.model.Rating;

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
                         apiMovie.imageSource == null ? Movie.EMPTY.imageSource : parseImageSourceUrl(apiMovie.imageSource),
                         false,
                         Movie.EMPTY.personalNote,
                         apiMovie.tmdbRating,
                         apiMovie.backdropSource == null ? Movie.EMPTY.backdropSource : parseImageSourceUrl(apiMovie.backdropSource),
                         apiMovie.imdbId == null ? Movie.EMPTY.imdbId : apiMovie.imdbId,
                         Movie.EMPTY.rating);
    }

    public List<Movie> mapMovies(final ApiMoviesList apiMoviesList) {
        final List<Movie> movies = new LinkedList<>();
        for (final ApiMovie apiMovie : apiMoviesList.movieApiEntities) {
            movies.add(mapMovie(apiMovie));
        }
        return movies;
    }

    public Cast mapCast(final ApiCast apiCast) {
        return new Cast(
                apiCast.getId() == null ? Cast.Companion.getEMPTY().getId() : apiCast.getId(),
                apiCast.getCharacter() == null ? Cast.Companion.getEMPTY().getCharacter() : apiCast.getCharacter(),
                apiCast.getName() == null ? Cast.Companion.getEMPTY().getName() : apiCast.getName(),
                apiCast.getProfileUrl() == null ? Cast.Companion.getEMPTY().getProfileUrl() : parseImageSourceUrl(apiCast.getProfileUrl())
        );
    }

    public List<Cast> mapCast(final List<ApiCast> apiCast) {
        final List<Cast> cast = new LinkedList<>();
        for (final ApiCast tempCast : apiCast) {
            cast.add(mapCast(tempCast));
        }
        return cast;
    }

    public Rating mapRating(final ApiRating apiRating) {
        return new Rating(apiRating.source == null ? Rating.EMPTY.getSource() : apiRating.source,
                          apiRating.ratingValue == null ? Rating.EMPTY.getValue() : apiRating.ratingValue);
    }

    public List<Rating> mapRatings(final ApiRatings apiRatings) {
        List<Rating> ratings = new ArrayList<>();
        for (final ApiRating apiRating : apiRatings.getRatings()) {
            ratings.add(mapRating(apiRating));
        }
        return ratings;
    }

    private String parseImageSourceUrl(final String imageSource) {
        return ApiConstants.IMAGE_SOURCE_URL + imageSource;
    }
}
