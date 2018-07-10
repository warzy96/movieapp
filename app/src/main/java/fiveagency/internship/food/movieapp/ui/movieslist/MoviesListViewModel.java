package fiveagency.internship.food.movieapp.ui.movieslist;

import java.util.List;

public final class MoviesListViewModel {

    public final List<MovieViewModel> movieViewModelList;

    MoviesListViewModel(final List<MovieViewModel> movieViewModelList) {
        this.movieViewModelList = movieViewModelList;
    }
}
