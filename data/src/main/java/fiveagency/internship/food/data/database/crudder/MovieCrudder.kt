package fiveagency.internship.food.data.database.crudder

import fiveagency.internship.food.data.database.dao.FavoritesDao
import fiveagency.internship.food.data.database.dao.MovieDao
import fiveagency.internship.food.data.database.dao.MovieDatabase
import fiveagency.internship.food.data.database.dao.RecommendationsDao
import fiveagency.internship.food.data.database.mappers.MovieModelMapper
import fiveagency.internship.food.data.database.model.DbFavoriteMovies
import fiveagency.internship.food.data.database.model.DbMovie
import fiveagency.internship.food.domain.model.FavoriteMovie
import fiveagency.internship.food.domain.model.Movie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.*

class MovieCrudder(
    private val movieDao: MovieDao,
    private val favoritesDao: FavoritesDao,
    private val recommendationsDao: RecommendationsDao,
    private val movieModelMapper: MovieModelMapper,
    private val movieDatabase: MovieDatabase
) {
    fun insertMovies(movies: List<Movie?>?): Completable {
        return Completable.fromAction { movieDao.insertAllMovies(movieModelMapper.mapMovieModels(movies)) }
    }

    fun getAllFavoriteMoviesIds(): Single<List<Int>> = favoritesDao.getAllMovieFavoritesId()

    fun getAllFavoriteMovies(): Flowable<List<Movie>> = movieDatabase.getAllFlowableFavorites()
        .map { dbMovies -> movieModelMapper.mapFavoriteMovies(dbMovies) }

    fun setFavorite(favoriteMovie: FavoriteMovie): Completable {
        return Completable.fromAction {
            favoritesDao.insertFavorites(setOf(DbFavoriteMovies(favoriteMovie)))
        }
    }

    fun removeFavorite(favoriteMovie: FavoriteMovie): Completable {
        return Completable.fromAction { favoritesDao.deleteFavorite(DbFavoriteMovies(favoriteMovie)) }
    }

    fun getAllFlowableFavoriteMoviesIds(): Flowable<List<Int>> = favoritesDao.getAllFlowableFavoritesIds()

    fun insertMovie(movie: Movie): Completable {
        return Completable.fromAction {
            favoritesDao.insertFavorites(setOf(DbFavoriteMovies(movie.id, movie.genres.genreList)))
            val dbMovie = movieModelMapper.mapMovieToMovieModel(movie)
            movieDao.insertAllMovies(LinkedList(listOf(dbMovie)))
        }
    }

    fun setPersonalNote(movie: Movie): Completable {
        return Completable.fromAction { movieDao.setPersonalNote(movie.personalNote, movie.id) }
    }

    fun getMovie(movieId: Int): Single<Movie> {
        return movieDao.getMovie(movieId)
            .map { dbMovie: DbMovie? -> movieModelMapper.mapMovie(dbMovie) }
    }

    fun movieExists(movieId: Int): Single<Movie> {
        return movieDao.getMovie(movieId)
            .map { dbMovie: DbMovie? -> movieModelMapper.mapMovie(dbMovie) }
    }

    fun getFavoriteMoviesRecommendations(): Flowable<List<Movie>> {
        return recommendationsDao.getFavoriteMoviesRecommendations().map { movieModelMapper.mapDbMovies(it) }
    }

    fun getWeatherMovieRecommendations(): Flowable<List<Movie>> {
        return recommendationsDao.getWeatherMovieRecommendations().map { movieModelMapper.mapDbMovies(it) }
    }
}