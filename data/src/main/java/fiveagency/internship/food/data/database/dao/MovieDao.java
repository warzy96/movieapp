package fiveagency.internship.food.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fiveagency.internship.food.data.database.model.MovieModel;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    Single<List<MovieModel>> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovies(MovieModel... movieModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovies(List<MovieModel> movieModels);

    @Delete
    void deleteMovie(MovieModel movieModel);
}
