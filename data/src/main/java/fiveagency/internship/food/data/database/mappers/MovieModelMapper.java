package fiveagency.internship.food.data.database.mappers;

import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.data.database.model.DbMovie;
import fiveagency.internship.food.domain.model.Genres;
import fiveagency.internship.food.domain.model.Movie;

public final class MovieModelMapper {

    public List<DbMovie> mapMovieModels(final List<Movie> movies) {
        final List<DbMovie> dbMovies = new LinkedList<>();
        for (final Movie movie : movies) {
            dbMovies.add(new DbMovie(movie));
        }
        return dbMovies;
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
                                 Movie.EMPTY.videos,
                                 new Genres(dbMovie.getGenres())));
        }
        return movies;
    }

    public List<Movie> mapDbMovies(final List<DbMovie> dbMovies) {
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
                                 Movie.EMPTY.videos,
                                 new Genres(dbMovie.getGenres())));
        }
        return movies;
    }

    public DbMovie mapMovieToDbModel(final Movie movie) {
        return new DbMovie(movie);
    }

    public Movie mapMovie(final DbMovie dbMovie) {
        return new Movie(dbMovie.getTitle(), dbMovie.getId(), dbMovie.isAdult(), dbMovie.getOverview(), dbMovie.getReleaseDate(), dbMovie.getImageSource(), false,
                         dbMovie.getPersonalNote(), dbMovie.getTmdbVote(), dbMovie.getBackdropSource(), Movie.EMPTY.imdbId, Movie.EMPTY.videos, new Genres(dbMovie.getGenres()));
    }

    public DbMovie mapMovieToMovieModel(final Movie movie) {
        return new DbMovie(movie);
    }
}
