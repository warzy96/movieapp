package fiveagency.internship.food.movieapp.ui.movieslist;

import fiveagency.internship.food.domain.model.Movie;

public final class MovieViewModel {

    public final int id;
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
        this.id = id;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", isAdult=" + isAdult +
                ", releaseDate='" + releaseDate + '\'' +
                ", imageSource='" + imageSource + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
