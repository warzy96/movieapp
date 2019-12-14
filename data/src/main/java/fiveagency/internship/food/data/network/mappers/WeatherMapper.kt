package fiveagency.internship.food.data.network.mappers

import fiveagency.internship.food.data.network.model.ApiCities
import fiveagency.internship.food.data.network.model.ApiCity
import fiveagency.internship.food.data.network.model.ApiCityDetails
import fiveagency.internship.food.data.network.model.ConsolidatedWeather
import fiveagency.internship.food.domain.model.City
import fiveagency.internship.food.domain.model.CitySearchResults
import fiveagency.internship.food.domain.model.WeatherDetailsModel
import fiveagency.internship.food.domain.model.WeatherModel
import java.util.*

fun mapConsolidatedWeather(consolidatedWeather: ConsolidatedWeather): WeatherDetailsModel =
    with(consolidatedWeather) {
        return WeatherDetailsModel(
            id, applicableDate, weatherState, weatherStateAbbr,
            windSpeed, windDirection, minTemp, maxTemp, currentTemp, airPressure,
            humidity, visibility, predictability
        )
    }

fun mapApiCityDetails(apiCityDetails: ApiCityDetails): WeatherModel =
    with(apiCityDetails) {
        val weatherDetailsModelList: ArrayList<WeatherDetailsModel> = ArrayList()
        consolidatedWeather.forEach {
            weatherDetailsModelList.add(mapConsolidatedWeather(it))
        }

        WeatherModel(title, cityId, sunRise, sunSet, timezoneName, weatherDetailsModelList)
    }

fun mapApiCities(apiCities: ApiCities): CitySearchResults =
    with(apiCities) {
        val list = ArrayList<City>()
        this.apiCities.forEach {
            list.add(mapCity(it))
        }
        CitySearchResults(list)
    }

fun mapCity(apiCity: ApiCity): City = with(apiCity) {
    City(title, woeid)
}
