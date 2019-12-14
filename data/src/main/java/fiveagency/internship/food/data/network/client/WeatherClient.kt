package fiveagency.internship.food.data.network.client

import fiveagency.internship.food.data.Location
import fiveagency.internship.food.data.network.mappers.mapApiCities
import fiveagency.internship.food.data.network.mappers.mapApiCityDetails
import fiveagency.internship.food.data.network.service.WeatherService
import fiveagency.internship.food.domain.model.CitySearchResults
import fiveagency.internship.food.domain.model.WeatherModel
import io.reactivex.Single

class WeatherClient(private val weatherService: WeatherService) {

    fun getCitySearchResults(cityName: String): Single<CitySearchResults> =
        weatherService.citySearch(cityName).map { mapApiCities(it) }

    fun getCityDetails(cityId: Int): Single<WeatherModel> =
        weatherService.cityDetailsEntity(cityId).map { mapApiCityDetails(it) }

    fun getCitySearchResults(latitude: Double, longitude: Double): Single<CitySearchResults> =
        weatherService.citySearch(Location(latitude, longitude)).map { mapApiCities(it) }
}