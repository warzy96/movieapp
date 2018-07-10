package fiveagency.internship.food.movieapp.ui.movieslist;

import fiveagency.internship.food.domain.model.Movie;

public final class MovieViewModel {

    public final int id;
    public final String title;
    public final String overview;
    public final boolean isAdult;
    public final String releaseDate;
    public final String imageSource;

    MovieViewModel(final Movie movie) {
        this(movie.title, movie.overview, movie.isAdult, movie.releaseDate, movie.releaseDate, movie.imageSource);
    }

    MovieViewModel(final int id, final String title, final String overview, final boolean isAdult, final String releaseDate, final String imageSource) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.isAdult = isAdult;
        this.releaseDate = releaseDate;
        this.imageSource = imageSource;
    }

    @Override
    public String toString() {
        return "MovieViewModel{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", isAdult=" + isAdult +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
