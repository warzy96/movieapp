package fiveagency.internship.food.data.database.crudder;

import java.util.List;

import fiveagency.internship.food.data.database.dao.FavoritesDao;
import fiveagency.internship.food.data.database.dao.MovieDao;
import fiveagency.internship.food.data.database.mappers.MovieModelMapper;
import fiveagency.internship.food.data.database.model.DbFavoriteMovies;
import fiveagency.internship.food.domain.model.Movie;
import io.reactivex.Completable;
import io.reactivex.Single;

public final class MovieCrudder {

    private final MovieDao movieDao;
    private final FavoritesDao favoritesDao;
    private final MovieModelMapper movieModelMapper;

    public MovieCrudder(final MovieDao movieDao, final FavoritesDao favoritesDao, final MovieModelMapper movieModelMapper) {
        this.movieDao = movieDao;
        this.favoritesDao = favoritesDao;
        this.movieModelMapper = movieModelMapper;
    }

    public void insertMovies(final List<Movie> movies) {
        movieDao.insertAllMovies(movieModelMapper.mapMovieModels(movies));
    }

    public Single<List<Movie>> getAllMovies() {
        return movieDao.getAllMovies().map(movieModelMapper::mapMovies);
    }

    public List<Integer> getAllFavoriteMovies() {
        return favoritesDao.getAllMovieFavoritesId();
    }

    public boolean isMovieFavorite(final int movieId) {
        return favoritesDao.isFavorite(movieId);
    }

    public Completable setFavorite(final int movieId) {
        return Completable.fromAction(() -> favoritesDao.insertFavorites(new DbFavoriteMovies(movieId)));
    }

    public Completable removeFavorite(final Integer movieId) {
        return Completable.fromAction(() -> favoritesDao.deleteFavorite(new DbFavoriteMovies(movieId)));
    }
}
