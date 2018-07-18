package fiveagency.internship.food.data.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favorites")
@ForeignKey(entity = DbMovie.class,
        parentColumns = "id",
        childColumns = "movieId",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)
public final class DbFavoriteMovies {

    @PrimaryKey
    private int id;

    public DbFavoriteMovies(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}

