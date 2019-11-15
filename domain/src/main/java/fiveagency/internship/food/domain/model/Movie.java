package fiveagency.internship.food.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Movie {

    public static final Movie EMPTY = new Movie("", 0, false, "", "", "", false, "", 0f, "", "", new ArrayList<>());
    public final String title;
    public final int id;
    public final boolean isAdult;
    public final String overview;
    public final String releaseDate;
    public final String imageSource;
    public final boolean isFavorite;
    public final String personalNote;
    public final float tmdbVote;
    public final String backdropSource;
    public final String imdbId;
    public final List<Video> videos;

    public Movie(final String title, final int id, final boolean isAdult, final String overview, final String releaseDate, final String imageSource, final boolean isFavorite,
                 final String personalNote, final float tmdbVote, final String backdropSource, final String imdbId, final List<Video> videos) {
        this.title = title;
        this.id = id;
        this.isAdult = isAdult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.imageSource = imageSource;
        this.isFavorite = isFavorite;
        this.personalNote = personalNote;
        this.tmdbVote = tmdbVote;
        this.backdropSource = backdropSource;
        this.imdbId = imdbId;
        this.videos = videos;
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
                ", tmdbVote=" + tmdbVote +
                ", backdropSource='" + backdropSource + '\'' +
                ", imdbId='" + imdbId + '\'' +
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
                Float.compare(movie.tmdbVote, tmdbVote) == 0 &&
                title.equals(movie.title) &&
                overview.equals(movie.overview) &&
                releaseDate.equals(movie.releaseDate) &&
                imageSource.equals(movie.imageSource) &&
                personalNote.equals(movie.personalNote) &&
                backdropSource.equals(movie.backdropSource) &&
                imdbId.equals(movie.imdbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, isAdult, overview, releaseDate, imageSource, isFavorite, personalNote, tmdbVote, backdropSource, imdbId);
    }

    public Movie withIsFavorite(final boolean isFavorite) {
        return new Movie(this.title, this.id, this.isAdult, this.overview, this.releaseDate, this.imageSource, isFavorite, this.personalNote, this.tmdbVote, this.backdropSource,
                         this.imdbId, this.videos);
    }

    public Movie withPersonalNote(final String personalNote) {
        return new Movie(this.title, this.id, this.isAdult, this.overview, this.releaseDate, this.imageSource, this.isFavorite, personalNote, this.tmdbVote, this.backdropSource,
                         this.imdbId, this.videos);
    }

    public Movie withVideos(final List<Video> videos) {
        return new Movie(this.title, this.id, this.isAdult, this.overview, this.releaseDate, this.imageSource, this.isFavorite, personalNote, this.tmdbVote, this.backdropSource,
                         this.imdbId, videos);
    }
}
