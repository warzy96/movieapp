package fiveagency.internship.food.domain.interactor

import fiveagency.internship.food.domain.interactor.type.SingleQueryUseCase
import fiveagency.internship.food.domain.model.Cast
import fiveagency.internship.food.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMovieCastUseCase @Inject constructor(private val movieRepository: MovieRepository) : SingleQueryUseCase<Int, List<Cast>> {

    override fun execute(movieId: Int): Single<List<Cast>> {
        return movieRepository.fetchMovieCast(movieId)
    }
}