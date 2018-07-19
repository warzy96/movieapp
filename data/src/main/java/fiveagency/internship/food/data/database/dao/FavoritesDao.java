package fiveagency.internship.food.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fiveagency.internship.food.data.database.model.DbFavoriteMovies;
import io.reactivex.Single;

@Dao
public interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavorites(DbFavoriteMovies... dbFavoriteMovies);

    @Delete
    void deleteFavorite(DbFavoriteMovies dbFavoriteMovies);

    @Query("SELECT * FROM favorites")
    Single<List<Integer>> getAllFavorites();

    @Query("SELECT movie.id FROM favorites NATURAL JOIN movie")
    Single<List<Integer>> getAllMovieFavoritesId();

    @Query("SELECT * FROM favorites WHERE id = :movieId")
    Single<Boolean> isFavorite(int movieId);
}
