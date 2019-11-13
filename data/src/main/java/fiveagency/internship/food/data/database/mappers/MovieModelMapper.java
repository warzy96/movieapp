package fiveagency.internship.food.data.database.mappers;

import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.data.database.model.DbMovie;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.domain.model.Movie;

public final class MovieModelMapper {

    public DbMovie mapMovieModel(final ApiMovie apiMovie) {
        if (apiMovie == null) {
            return DbMovie.EMPTY;
        }
        return new DbMovie(apiMovie.id,
                           apiMovie.title == null ? DbMovie.EMPTY.getTitle() : apiMovie.title,
                           apiMovie.isAdult,
                           apiMovie.overview == null ? DbMovie.EMPTY.getOverview() : apiMovie.overview,
                           apiMovie.releaseDate == null ? DbMovie.EMPTY.getReleaseDate() : apiMovie.releaseDate,
                           apiMovie.imageSource == null ? DbMovie.EMPTY.getImageSource() : apiMovie.imageSource,
                           Movie.EMPTY.personalNote,
                           apiMovie.tmdbRating,
                           apiMovie.backdropSource == null ? DbMovie.EMPTY.getBackdropSource() : apiMovie.backdropSource);
    }

    public List<DbMovie> mapMovieModels(final List<Movie> movies) {
        final List<DbMovie> dbMovies = new LinkedList<>();
        for (final Movie movie : movies) {
            dbMovies.add(new DbMovie(movie));
        }
        return dbMovies;
    }

    public List<Movie> mapMovies(final List<DbMovie> dbMovies) {
        final List<Movie> movies = new LinkedList<>();
        for (final DbMovie dbMovie : dbMovies) {
            movies.add(new Movie(dbMovie.getTitle(),
                                 dbMovie.getId(),
                                 dbMovie.isAdult(),
                                 dbMovie.getOverview(),
                                 dbMovie.getReleaseDate(),
                                 dbMovie.getImageSource(),
                                 false,
                                 dbMovie.getPersonalNote(),
                                 dbMovie.getTmdbVote(),
                                 dbMovie.getBackdropSource(),
                                 Movie.EMPTY.imdbId,
                                 Movie.EMPTY.rating));
        }
        return movies;
    }

    public List<Movie> mapFavoriteMovies(final List<DbMovie> dbMovies) {
        final List<Movie> movies = new LinkedList<>();
        for (final DbMovie dbMovie : dbMovies) {
            movies.add(new Movie(dbMovie.getTitle(),
                                 dbMovie.getId(),
                                 dbMovie.isAdult(),
                                 dbMovie.getOverview(),
                                 dbMovie.getReleaseDate(),
                                 dbMovie.getImageSource(),
                                 true,
                                 dbMovie.getPersonalNote(),
                                 dbMovie.getTmdbVote(),
                                 dbMovie.getBackdropSource(),
                                 Movie.EMPTY.imdbId,
                                 Movie.EMPTY.rating));
        }
        return movies;
    }

    public DbMovie mapMovieToDbModel(final Movie movie) {
        return new DbMovie(movie);
    }

    public Movie mapMovie(final DbMovie dbMovie) {
        return new Movie(dbMovie.getTitle(), dbMovie.getId(), dbMovie.isAdult(), dbMovie.getOverview(), dbMovie.getReleaseDate(), dbMovie.getImageSource(), false,
                         dbMovie.getPersonalNote(), dbMovie.getTmdbVote(), dbMovie.getBackdropSource(),
                         Movie.EMPTY.imdbId,
                         Movie.EMPTY.rating);
    }

    public DbMovie mapMovieToMovieModel(final Movie movie) {
        return new DbMovie(movie);
    }
}
