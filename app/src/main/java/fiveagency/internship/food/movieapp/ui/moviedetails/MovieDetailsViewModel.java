package fiveagency.internship.food.movieapp.ui.moviedetails;

import java.util.List;
import java.util.Objects;

import fiveagency.internship.food.domain.model.Cast;
import fiveagency.internship.food.domain.model.Genres;
import fiveagency.internship.food.domain.model.Video;

public final class MovieDetailsViewModel {

    public final int id;
    public final String title;
    public final String overview;
    public final boolean isAdult;
    public final String releaseDate;
    public final String imageSource;
    public final String personalNote;
    public final List<Cast> castList;
    public final float tmdbRating;
    public final String backdropPath;
    public final boolean isFavorite;
    public final String imdbId;
    public final List<Video> videos;
    public final Genres genres;

    public MovieDetailsViewModel(final int id, final String title, final String overview, final boolean isAdult, final String releaseDate, final String imageSource,
                                 final String personalNote, final List<Cast> castList, final float tmdbRating, final String backdropPath, final boolean isFavorite,
                                 final String imdbId, final List<Video> videos, final Genres genres) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.isAdult = isAdult;
        this.releaseDate = releaseDate;
        this.imageSource = imageSource;
        this.personalNote = personalNote;
        this.castList = castList;
        this.tmdbRating = tmdbRating;
        this.backdropPath = backdropPath;
        this.isFavorite = isFavorite;
        this.videos = videos;
        this.imdbId = imdbId;
        this.genres = genres;
    }

    public MovieDetailsViewModel withPersonalNote(final String personalNote) {
        return new MovieDetailsViewModel(this.id, this.title, this.overview, this.isAdult, this.releaseDate, this.imageSource, personalNote, this.castList, this.tmdbRating,
                                         this.backdropPath, this.isFavorite, this.imdbId, this.videos, this.genres);
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
                Float.compare(that.tmdbRating, tmdbRating) == 0 &&
                Objects.equals(title, that.title) &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(imageSource, that.imageSource) &&
                Objects.equals(personalNote, that.personalNote) &&
                Objects.equals(castList, that.castList) &&
                Objects.equals(backdropPath, that.backdropPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, overview, isAdult, releaseDate, imageSource, personalNote, castList, tmdbRating, backdropPath);
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
                ", castList=" + castList +
                ", tmdbRating=" + tmdbRating +
                ", backdropPath='" + backdropPath + '\'' +
                '}';
    }
}
