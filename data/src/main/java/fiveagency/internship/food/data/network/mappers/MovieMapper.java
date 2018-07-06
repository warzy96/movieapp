package fiveagency.internship.food.data.network.mappers;

import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.data.network.model.ApiMoviesList;
import fiveagency.internship.food.domain.model.Movie;

public final class MovieMapper {

    public Movie mapMovie(ApiMovie apiMovie) {
        return new Movie(apiMovie.title, apiMovie.id, apiMovie.isAdult);
    }

    public List<Movie> mapMovies(ApiMoviesList apiMoviesList) {
        List<Movie> movies = new LinkedList<>();
        for (ApiMovie apiMovie : apiMoviesList.movieApiEntities) {
            movies.add(new Movie(apiMovie.title, apiMovie.id, apiMovie.isAdult));
        }
        return movies;
    }
}
