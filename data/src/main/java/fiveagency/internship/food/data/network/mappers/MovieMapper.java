package fiveagency.internship.food.data.network.mappers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.data.network.ApiConstants;
import fiveagency.internship.food.data.network.model.ApiCast;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.data.network.model.ApiMoviesList;
import fiveagency.internship.food.data.network.model.ApiPersonDetails;
import fiveagency.internship.food.data.network.model.ApiPersonMovieCredit;
import fiveagency.internship.food.data.network.model.ApiPersonMovieCredits;
import fiveagency.internship.food.data.network.model.ApiRating;
import fiveagency.internship.food.data.network.model.ApiRatings;
import fiveagency.internship.food.data.network.model.ApiVideo;
import fiveagency.internship.food.data.network.model.ApiVideos;
import fiveagency.internship.food.domain.model.Cast;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.model.PersonDetails;
import fiveagency.internship.food.domain.model.PersonMovieCredit;
import fiveagency.internship.food.domain.model.PersonMovieCredits;
import fiveagency.internship.food.domain.model.Rating;
import fiveagency.internship.food.domain.model.Video;

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
                         Movie.EMPTY.videos);
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

    public List<Video> mapVideos(final ApiVideos apiVideos) {
        List<Video> videos = new ArrayList<>();
        for (final ApiVideo apiVideo : apiVideos.getVideos()) {
            videos.add(mapVideo(apiVideo));
        }
        return videos;
    }

    public PersonMovieCredits mapPersonMovieCredits(final ApiPersonMovieCredits apiPersonMovieCredits) {
        final PersonMovieCredits result = new PersonMovieCredits(new ArrayList<>());
        for (final ApiPersonMovieCredit apiPersonMovieCredit : apiPersonMovieCredits.getCredits()) {
            result.getCredits().add(mapPersonMovieCredit(apiPersonMovieCredit));
        }
        return result;
    }

    public PersonDetails mapPersonDetails(final ApiPersonDetails apiPersonDetails) {
        return new PersonDetails(apiPersonDetails.getName() == null ? PersonDetails.EMPTY.getName() : apiPersonDetails.getName(),
                                 apiPersonDetails.getBirthday() == null ? PersonDetails.EMPTY.getBirthday() : apiPersonDetails.getBirthday(),
                                 apiPersonDetails.getDeathday() == null ? PersonDetails.EMPTY.getDeathday() : apiPersonDetails.getDeathday(),
                                 apiPersonDetails.getProfession() == null ? PersonDetails.EMPTY.getProfession() : apiPersonDetails.getProfession(),
                                 apiPersonDetails.getProfileImageUrl() == null ? PersonDetails.EMPTY.getProfileImageUrl()
                                                                               : parseImageSourceUrl(apiPersonDetails.getProfileImageUrl()),
                                 apiPersonDetails.getBiography() == null || apiPersonDetails.getBiography().isEmpty() ? PersonDetails.EMPTY.getBiography()
                                                                                                                      : apiPersonDetails.getBiography(),
                                 apiPersonDetails.getPlaceOfBirth() == null ? PersonDetails.EMPTY.getPlaceOfBirth() : apiPersonDetails.getPlaceOfBirth());
    }

    private PersonMovieCredit mapPersonMovieCredit(final ApiPersonMovieCredit apiPersonMovieCredit) {
        return apiPersonMovieCredit.getBackdropPath() == null ? PersonMovieCredit.Companion.getEMPTY() : new PersonMovieCredit(
                parseImageSourceUrl(apiPersonMovieCredit.getBackdropPath()));
    }

    private Video mapVideo(final ApiVideo apiVideo) {
        return new Video(apiVideo.getKey() == null ? Video.EMPTY.getKey() : apiVideo.getKey(),
                         apiVideo.getSource() == null ? Video.EMPTY.getSource() : apiVideo.getSource(),
                         apiVideo.getType() == null ? Video.EMPTY.getType() : apiVideo.getType());
    }

    private String parseImageSourceUrl(final String imageSource) {
        return ApiConstants.IMAGE_SOURCE_URL + imageSource;
    }
}
