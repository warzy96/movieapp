package fiveagency.internship.food.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import fiveagency.internship.food.domain.interactor.type.FetchUseCase;
import fiveagency.internship.food.domain.model.Movie;
import fiveagency.internship.food.domain.repository.MovieRepository;
import io.reactivex.Single;

public final class GetFavoritesUseCase implements FetchUseCase<Single<List<Movie>>> {

    private final MovieRepository movieRepository;

    @Inject
    public GetFavoritesUseCase(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Single<List<Movie>> execute() {
        return movieRepository.fetchFavorites();
    }
}
