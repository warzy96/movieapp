package fiveagency.internship.food.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import fiveagency.internship.food.data.database.dao.FavoritesDao;
import fiveagency.internship.food.data.database.dao.MovieDao;
import fiveagency.internship.food.data.database.model.FavoriteMovies;
import fiveagency.internship.food.data.database.model.MovieModel;

@Database(entities = {MovieModel.class, FavoriteMovies.class}, version = 2)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public abstract FavoritesDao favoritesDao();
}
