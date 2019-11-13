package fiveagency.internship.food.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import fiveagency.internship.food.data.database.dao.FavoritesDao;
import fiveagency.internship.food.data.database.dao.MovieDao;
import fiveagency.internship.food.data.database.model.DbFavoriteMovies;
import fiveagency.internship.food.data.database.model.DbMovie;

import static fiveagency.internship.food.data.database.MovieDatabase.DB_VERSION;

@Database(entities = {DbMovie.class, DbFavoriteMovies.class}, version = DB_VERSION)
public abstract class MovieDatabase extends RoomDatabase {

    public static final String DB_NAME = "movie-database";
    public static final int DB_VERSION = 4;

    public abstract MovieDao movieDao();

    public abstract FavoritesDao favoritesDao();
}
