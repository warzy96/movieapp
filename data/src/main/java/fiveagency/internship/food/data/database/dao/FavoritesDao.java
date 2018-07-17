package fiveagency.internship.food.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fiveagency.internship.food.data.database.model.FavoriteMovies;
import fiveagency.internship.food.data.database.model.MovieModel;
import io.reactivex.Single;

@Dao
public interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavorites(FavoriteMovies... favoriteMovies);

    @Delete
    void deleteFavorite(FavoriteMovies favoriteMovies);

    @Query("SELECT * FROM favorites")
    Single<List<Integer>> getAllFavorites();

    @Query("SELECT movie.* FROM favorites NATURAL JOIN movie")
    Single<List<MovieModel>> getAllMovieFavorites();

    @Query("SELECT * FROM favorites WHERE id = :movieId")
    boolean isFavorite(int movieId);
}
