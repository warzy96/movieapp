package fiveagency.internship.food.domain.interactor

import fiveagency.internship.food.domain.interactor.type.SingleQueryUseCase
import fiveagency.internship.food.domain.model.PersonMovieCredits
import fiveagency.internship.food.domain.repository.MovieRepository
import io.reactivex.Single

class GetPersonMovieCreditsUseCase(private val movieRepository: MovieRepository) : SingleQueryUseCase<Int, PersonMovieCredits> {

    override fun execute(param: Int): Single<PersonMovieCredits> {
        return movieRepository.fetchPersonMovieCredits(param)
    }
}