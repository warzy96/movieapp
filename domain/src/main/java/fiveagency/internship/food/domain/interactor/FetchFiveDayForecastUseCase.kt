package fiveagency.internship.food.domain.interactor

import fiveagency.internship.food.domain.interactor.type.SingleQueryUseCase
import fiveagency.internship.food.domain.model.WeatherModel
import fiveagency.internship.food.domain.repository.WeatherRepository
import io.reactivex.Single

class FetchFiveDayForecastUseCase(private val weatherRepository: WeatherRepository) :
    SingleQueryUseCase<Int, WeatherModel> {

    override fun execute(param: Int): Single<WeatherModel> {
        return weatherRepository.fiveDayForecast(param)
    }
}