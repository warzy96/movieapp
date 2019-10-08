package fiveagency.internship.food.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fiveagency.internship.food.data.database.model.DbFavoriteMovies;
import fiveagency.internship.food.data.database.model.DbMovie;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavorites(DbFavoriteMovies... dbFavoriteMovies);

    @Delete
    void deleteFavorite(DbFavoriteMovies dbFavoriteMovies);

    @Query("SELECT movie.* FROM movie NATURAL JOIN favorites")
    Single<List<DbMovie>> getAllFavorites();

    @Query("SELECT movie.id FROM favorites NATURAL JOIN movie")
    Single<List<Integer>> getAllMovieFavoritesId();

    @Query("SELECT * FROM favorites WHERE id = :movieId")
    boolean isFavorite(int movieId);

    @Query("SELECT movie.* FROM movie NATURAL JOIN favorites")
    Flowable<List<DbMovie>> getAllFlowableFavorites();

    @Query("SELECT id FROM favorites")
    Flowable<List<Integer>> getAllFlowableFavoritesIds();
}
