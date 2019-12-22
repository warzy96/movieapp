package fiveagency.internship.food.movieapp.ui.moviedetails;

import java.util.List;

import fiveagency.internship.food.domain.model.Rating;
import kotlin.Unit;

public interface MovieDetailsContract {

    interface Presenter {

        void start(int movieId);

        void setView(MovieDetailsContract.View view);

        void savePersonalNote(MovieDetailsViewModel movieDetailsViewModel);

        void onStop();

        void insertFavorite(final int movieId);

        void removeFavorite(final int movieId);

        void goBack();

        Unit showActorDetailsScreen(int castId);
    }

    interface View {

        void render(MovieDetailsViewModel movieDetailsViewModel);

        void renderRatings(List<Rating> ratings);
    }
}
