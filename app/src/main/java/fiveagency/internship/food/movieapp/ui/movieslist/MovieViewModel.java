package fiveagency.internship.food.movieapp.ui.movieslist;

import fiveagency.internship.food.domain.model.Movie;

public final class MovieViewModel {

    public final String title;
    public final String overview;
    public final boolean isAdult;
    public final String releaseDate;

    MovieViewModel(final Movie movie) {
        this.title = movie.title;
        this.overview = movie.overview;
        this.isAdult = movie.isAdult;
        this.releaseDate = movie.releaseDate;
    }

    MovieViewModel(final String title, final String overview, final boolean isAdult, final String releaseDate) {
        this.title = title;
        this.overview = overview;
        this.isAdult = isAdult;
        this.releaseDate = releaseDate;
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
