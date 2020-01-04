package fiveagency.internship.food.domain.interactor

import fiveagency.internship.food.domain.interactor.type.FetchUseCase
import fiveagency.internship.food.domain.model.Movie
import fiveagency.internship.food.domain.repository.MovieRepository
import io.reactivex.Flowable

class GetFavoriteMoviesRecommendationsUseCase(private val movieRepository: MovieRepository) : FetchUseCase<Flowable<List<Movie>>> {
    override fun execute(): Flowable<List<Movie>> {
        return movieRepository.fetchFavoriteMoviesRecommendations()
    }
}