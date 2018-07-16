package fiveagency.internship.food.data.database.mappers;

import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.data.database.model.MovieModel;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.data.network.model.ApiMoviesList;
import fiveagency.internship.food.domain.model.Movie;

public final class MovieModelMapper {

    public MovieModel mapMovieModel(final ApiMovie apiMovie) {
        if (apiMovie == null) {
            return MovieModel.EMPTY;
        }
        return new MovieModel(apiMovie.id,
                              apiMovie.title == null ? MovieModel.EMPTY.getTitle() : apiMovie.title,
                              apiMovie.isAdult,
                              apiMovie.overview == null ? MovieModel.EMPTY.getOverview() : apiMovie.overview,
                              apiMovie.releaseDate == null ? MovieModel.EMPTY.getReleaseDate() : apiMovie.releaseDate,
                              apiMovie.imageSource == null ? MovieModel.EMPTY.getImageSource() : apiMovie.imageSource);
    }

    public List<MovieModel> mapMovieModels(final ApiMoviesList apiMoviesList) {
        final List<MovieModel> movieModels = new LinkedList<>();
        for (final ApiMovie apiMovie : apiMoviesList.movieApiEntities) {
            movieModels.add(new MovieModel(apiMovie));
        }
        return movieModels;
    }

    public List<MovieModel> mapMovieModels(final List<Movie> movies) {
        final List<MovieModel> movieModels = new LinkedList<>();
        for (final Movie movie : movies) {
            movieModels.add(new MovieModel(movie));
        }
        return movieModels;
    }

    public Movie mapMovieModel(final MovieModel movieModel) {
        return new Movie(movieModel.getTitle(),
                         movieModel.getId(),
                         movieModel.isAdult(),
                         movieModel.getOverview(),
                         movieModel.getReleaseDate(),
                         movieModel.getImageSource());
    }

    public List<Movie> mapMovies(final List<MovieModel> movieModels) {
        final List<Movie> movies = new LinkedList<>();
        for (final MovieModel movieModel : movieModels) {
            movies.add(new Movie(movieModel.getTitle(),
                                 movieModel.getId(),
                                 movieModel.isAdult(),
                                 movieModel.getOverview(),
                                 movieModel.getReleaseDate(),
                                 movieModel.getImageSource()));
        }
        return movies;
    }

    public MovieModel mapMovieToMovieModel(final Movie movie) {
        return new MovieModel(movie);
    }
}
