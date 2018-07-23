package fiveagency.internship.food.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fiveagency.internship.food.data.database.model.DbMovie;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    Single<List<DbMovie>> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllMovies(DbMovie... dbMovie);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllMovies(List<DbMovie> dbMovies);

    @Delete
    void deleteMovie(DbMovie dbMovie);

    @Query("UPDATE movie SET personalNote = :personalNote WHERE id = :movieId")
    void setPersonalNote(String personalNote, int movieId);

    @Query("SELECT * FROM movie WHERE id = :movieId")
    Single<DbMovie> getMovie(int movieId);

    @Query("SELECT * FROM movie WHERE id = :movieId")
    Single<List<DbMovie>> movieExists(int movieId);
}
