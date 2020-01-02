package fiveagency.internship.food.data.database.dao

import fiveagency.internship.food.data.database.model.DbMovie
import io.reactivex.Flowable
import io.reactivex.rxkotlin.Flowables

class MovieDatabase(private val movieDao: MovieDao, private val favoritesDao: FavoritesDao) {

    fun getAllFlowableFavorites(): Flowable<List<DbMovie>> {
        return Flowables.combineLatest(
            favoritesDao.getAllFlowableFavoritesIds(),
            movieDao.getAllMovies()
        ) { favorites: List<Int>, movies: List<DbMovie> ->
            movies.filter { favorites.contains(it.id) }
        }
    }
}