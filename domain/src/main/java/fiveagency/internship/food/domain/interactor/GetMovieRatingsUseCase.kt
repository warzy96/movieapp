package fiveagency.internship.food.domain.interactor

import fiveagency.internship.food.domain.interactor.type.SingleQueryUseCase
import fiveagency.internship.food.domain.model.Rating
import fiveagency.internship.food.domain.repository.MovieRepository
import io.reactivex.Single

class GetMovieRatingsUseCase(val movieRepository: MovieRepository) : SingleQueryUseCase<String, List<Rating>> {

    override fun execute(param: String): Single<List<Rating>> {
        return movieRepository.fetchRatings(param)
    }
}