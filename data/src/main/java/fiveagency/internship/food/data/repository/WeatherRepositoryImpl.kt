package fiveagency.internship.food.data.repository

import fiveagency.internship.food.data.network.client.WeatherClient
import fiveagency.internship.food.domain.model.CitySearchResults
import fiveagency.internship.food.domain.model.WeatherModel
import fiveagency.internship.food.domain.repository.WeatherRepository
import io.reactivex.Single

class WeatherRepositoryImpl(private val weatherClient: WeatherClient) : WeatherRepository {

    override fun citySearch(latitude: Double, longitude: Double): Single<CitySearchResults> {
        return weatherClient.getCitySearchResults(latitude, longitude)
    }

    override fun fiveDayForecast(cityId: Int): Single<WeatherModel> {
        return weatherClient.getCityDetails(cityId)
    }
}