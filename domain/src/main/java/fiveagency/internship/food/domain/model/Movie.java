package fiveagency.internship.food.domain.model;

import java.util.Objects;

public final class Movie {

    public final String title;
    public final int id;
    public final boolean isAdult;
    public final String overview;
    public final String releaseDate;
    public final String imageSource;

    public Movie(final String title, final int id, final boolean isAdult, final String overview, final String releaseDate, final String imageSource) {
        this.title = title;
        this.id = id;
        this.isAdult = isAdult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.imageSource = imageSource;
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
                Objects.equals(title, movie.title) &&
                Objects.equals(overview, movie.overview) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(imageSource, movie.imageSource);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, id, isAdult, overview, releaseDate, imageSource);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", isAdult=" + isAdult +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", imageSource='" + imageSource + '\'' +
                '}';
    }
}
