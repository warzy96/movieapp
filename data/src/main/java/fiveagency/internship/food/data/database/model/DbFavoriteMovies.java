package fiveagency.internship.food.data.database.model;

import java.util.List;
import java.util.Objects;

import fiveagency.internship.food.domain.model.FavoriteMovie;

public final class DbFavoriteMovies {

    private int id;
    private List<Integer> genres;

    public DbFavoriteMovies() {

    }

    public DbFavoriteMovies(final int id, final List<Integer> genres) {
        this.id = id;
        this.genres = genres;
    }

    public DbFavoriteMovies(final FavoriteMovie favoriteMovie) {
        this.id = favoriteMovie.getMovieId();
        this.genres = favoriteMovie.getGenres();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(final List<Integer> genres) {
        this.genres = genres;
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

