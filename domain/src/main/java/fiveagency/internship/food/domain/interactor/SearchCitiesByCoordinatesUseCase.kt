package fiveagency.internship.food.domain.interactor

import fiveagency.internship.food.domain.interactor.type.SingleQueryUseCase
import fiveagency.internship.food.domain.model.CitySearchResults
import fiveagency.internship.food.domain.model.Location
import fiveagency.internship.food.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchCitiesByCoordinatesUseCase
@Inject
constructor(private val weatherRepository: WeatherRepository) : SingleQueryUseCase<Location, CitySearchResults> {

    override fun execute(param: Location): Single<CitySearchResults> {
        return weatherRepository.citySearch(param.latitude, param.longitude)
    }
}