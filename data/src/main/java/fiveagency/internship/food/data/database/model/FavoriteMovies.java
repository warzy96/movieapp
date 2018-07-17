package fiveagency.internship.food.data.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favorites")
@ForeignKey(entity = MovieModel.class,
        parentColumns = "id",
        childColumns = "movieId",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)
public final class FavoriteMovies {

    @PrimaryKey
    private int id;

    public FavoriteMovies(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}

