package fiveagency.internship.food.domain.model;

import java.util.Objects;

public final class Movie {

    public final String title;
    public final int id;
    public final boolean isAdult;

    public Movie(final String title, final int id, final boolean isAdult) {
        this.title = title;
        this.id = id;
        this.isAdult = isAdult;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", isAdult=" + isAdult +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Movie movie = (Movie) o;
        return id == movie.id &&
                isAdult == movie.isAdult &&
                Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, id, isAdult);
    }
}
