package fiveagency.internship.food.domain.interactor.type

import fiveagency.internship.food.domain.model.ActorDetails
import fiveagency.internship.food.domain.repository.MovieRepository
import io.reactivex.Single

class GetActorDetailsUseCase(private val movieRepository: MovieRepository) : SingleQueryUseCase<Int, ActorDetails> {
    override fun execute(param: Int): Single<ActorDetails> {
        return movieRepository.fetchActorDetails(param)
    }
}