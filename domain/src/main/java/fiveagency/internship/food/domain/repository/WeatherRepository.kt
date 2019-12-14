package fiveagency.internship.food.domain.repository

import fiveagency.internship.food.domain.model.CitySearchResults
import fiveagency.internship.food.domain.model.WeatherModel
import io.reactivex.Single

interface WeatherRepository {

    fun fiveDayForecast(cityId: Int): Single<WeatherModel>

    fun citySearch(latitude: Double, longitude: Double): Single<CitySearchResults>
}