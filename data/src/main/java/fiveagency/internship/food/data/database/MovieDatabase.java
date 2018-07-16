package fiveagency.internship.food.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import fiveagency.internship.food.data.database.dao.MovieDao;
import fiveagency.internship.food.data.database.model.MovieModel;

@Database(entities = {MovieModel.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
}
