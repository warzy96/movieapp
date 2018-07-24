package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.Objects;

import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.movieapp.ui.movieslist.diffutil.DiffUtilViewModel;

public final class MovieViewModel extends DiffUtilViewModel<Integer> {

    public final String title;
    public final String overview;
    public final boolean isAdult;
    public final String releaseDate;
    public final String imageSource;
    public final boolean isFavorite;

    MovieViewModel(final Movie movie) {
        this(movie.id, movie.title, movie.overview, movie.isAdult, movie.releaseDate, movie.imageSource, movie.isFavorite);
    }

    MovieViewModel(final int id, final String title, final String overview, final boolean isAdult, final String releaseDate, final String imageSource, final boolean isFavorite) {
        super(id);
        this.title = title;
        this.overview = overview;
        this.isAdult = isAdult;
        this.releaseDate = releaseDate;
        this.imageSource = imageSource;
        this.isFavorite = isFavorite;
    }

    @Override
    public String toString() {
        return "MovieViewModel{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", isAdult=" + isAdult +
                ", releaseDate='" + releaseDate + '\'' +
                ", imageSource='" + imageSource + '\'' +
                ", isFavorite=" + isFavorite +
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
        final MovieViewModel that = (MovieViewModel) o;
        return isAdult == that.isAdult &&
                isFavorite == that.isFavorite &&
                Objects.equals(title, that.title) &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(imageSource, that.imageSource);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, overview, isAdult, releaseDate, imageSource, isFavorite);
    }
}
