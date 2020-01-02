package fiveagency.internship.food.data.database.model

class DbFavoriteMoviesList() {
    lateinit var dbMoviesList: List<DbFavoriteMovies>

    constructor(dbMoviesList: List<DbFavoriteMovies>) : this() {
        this.dbMoviesList = dbMoviesList
    }

    companion object {
        val EMPTY = DbFavoriteMoviesList(listOf())
    }
}