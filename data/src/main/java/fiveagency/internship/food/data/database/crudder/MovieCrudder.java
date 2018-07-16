package fiveagency.internship.food.data.database.crudder;

import java.util.List;

import fiveagency.internship.food.data.database.dao.MovieDao;
import fiveagency.internship.food.data.database.mappers.MovieModelMapper;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.domain.model.Movie;
import io.reactivex.Single;

public final class MovieCrudder {

    private final MovieDao movieDao;
    private final MovieModelMapper movieModelMapper;

    public MovieCrudder(final MovieDao movieDao, final MovieModelMapper movieModelMapper) {
        this.movieDao = movieDao;
        this.movieModelMapper = movieModelMapper;
    }

    public void insertMovie(final ApiMovie apiMovie) {
        movieDao.insertAllMovies(movieModelMapper.mapMovieModel(apiMovie));
    }

    public void insertMovies(final List<Movie> movies) {
        movieDao.insertAllMovies(movieModelMapper.mapMovieModels(movies));
    }

    public void deleteMovie(final ApiMovie apiMovie) {
        movieDao.deleteMovie(movieModelMapper.mapMovieModel(apiMovie));
    }

    public Single<List<Movie>> getAllMovies() {
        return movieDao.getAllMovies().map(movieModelMapper::mapMovies);
    }
}
