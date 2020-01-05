package fiveagency.internship.food.movieapp.ui.moviedetails;

import java.util.List;

import fiveagency.internship.food.domain.model.Rating;
import fiveagency.internship.food.movieapp.ui.moviedetails.beer.BeerViewModel;
import fiveagency.internship.food.movieapp.ui.movieslist.FavoriteMovieModel;
import kotlin.Unit;

public interface MovieDetailsContract {

    interface Presenter {

        void start(int movieId);

        void setView(MovieDetailsContract.View view);

        void savePersonalNote(MovieDetailsViewModel movieDetailsViewModel);

        void onStop();

        void insertFavorite(final FavoriteMovieModel favoriteMovieModel);

        void removeFavorite(final FavoriteMovieModel favoriteMovieModel);

        void goBack();

        Unit showActorDetailsScreen(int castId);

        void recommendBeer();
    }

    interface View {

        void render(MovieDetailsViewModel movieDetailsViewModel);

        void renderRatings(List<Rating> ratings);

        void showRecommendBeerDialog(BeerViewModel beerViewModel);
    }
}
