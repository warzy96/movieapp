package fiveagency.internship.food.movieapp.ui.moviedetails;

import java.util.Objects;

public final class MovieDetailsViewModel {

    public final int id;
    public final String title;
    public final String overview;
    public final boolean isAdult;
    public final String releaseDate;

    public MovieDetailsViewModel(final int id, final String title, final String overview, final boolean isAdult, final String releaseDate) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.isAdult = isAdult;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "MovieDetailsViewModel{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", isAdult=" + isAdult +
                ", releaseDate='" + releaseDate + '\'' +
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
        final MovieDetailsViewModel that = (MovieDetailsViewModel) o;
        return isAdult == that.isAdult &&
                Objects.equals(title, that.title) &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, overview, isAdult, releaseDate);
    }
}
