package fiveagency.internship.food.data.database.model;

import java.util.Objects;

public final class DbFavoriteMovies {

    private int id;

    public DbFavoriteMovies() {

    }

    public DbFavoriteMovies(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DbFavoriteMovies that = (DbFavoriteMovies) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

