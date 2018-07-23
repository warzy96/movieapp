package fiveagency.internship.food.domain.model;

import java.util.Objects;

public final class Movie {

    public static final Movie EMPTY = new Movie("", 0, false, "", "", "", false, "");
    public final String title;
    public final int id;
    public final boolean isAdult;
    public final String overview;
    public final String releaseDate;
    public final String imageSource;
    public final boolean isFavorite;
    public final String personalNote;

    public Movie(final String title, final int id, final boolean isAdult, final String overview, final String releaseDate, final String imageSource, final boolean isFavorite,
                 final String personalNote) {
        this.title = title;
        this.id = id;
        this.isAdult = isAdult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.imageSource = imageSource;
        this.isFavorite = isFavorite;
        this.personalNote = personalNote;
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
                ", isFavorite=" + isFavorite +
                ", personalNote='" + personalNote + '\'' +
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
                isFavorite == movie.isFavorite &&
                Objects.equals(title, movie.title) &&
                Objects.equals(overview, movie.overview) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(imageSource, movie.imageSource) &&
                Objects.equals(personalNote, movie.personalNote);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, id, isAdult, overview, releaseDate, imageSource, isFavorite, personalNote);
    }

    public Movie withIsFavorite(final boolean isFavorite) {
        return new Movie(this.title, this.id, this.isAdult, this.overview, this.releaseDate, this.imageSource, isFavorite, this.personalNote);
    }

    public Movie withPersonalNote(final String personalNote) {
        return new Movie(this.title, this.id, this.isAdult, this.overview, this.releaseDate, this.imageSource, this.isFavorite, personalNote);
    }
}
