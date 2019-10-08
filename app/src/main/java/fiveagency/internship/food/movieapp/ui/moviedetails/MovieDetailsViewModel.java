package fiveagency.internship.food.movieapp.ui.moviedetails;

import java.util.Objects;

public final class MovieDetailsViewModel {

    public final int id;
    public final String title;
    public final String overview;
    public final boolean isAdult;
    public final String releaseDate;
    public final String imageSource;
    public final String personalNote;

    public MovieDetailsViewModel(final int id, final String title, final String overview, final boolean isAdult, final String releaseDate, final String imageSource,
                                 final String personalNote) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.isAdult = isAdult;
        this.releaseDate = releaseDate;
        this.imageSource = imageSource;
        this.personalNote = personalNote;
    }

    public MovieDetailsViewModel withPersonalNote(final String personalNote) {
        return new MovieDetailsViewModel(this.id, this.title, this.overview, this.isAdult, this.releaseDate, this.imageSource, personalNote);
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
        return id == that.id &&
                isAdult == that.isAdult &&
                Objects.equals(title, that.title) &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(imageSource, that.imageSource) &&
                Objects.equals(personalNote, that.personalNote);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, overview, isAdult, releaseDate, imageSource, personalNote);
    }

    @Override
    public String toString() {
        return "MovieDetailsViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", isAdult=" + isAdult +
                ", releaseDate='" + releaseDate + '\'' +
                ", imageSource='" + imageSource + '\'' +
                ", personalNote='" + personalNote + '\'' +
                '}';
    }
}
