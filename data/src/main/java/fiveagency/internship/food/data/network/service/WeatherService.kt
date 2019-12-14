package fiveagency.internship.food.data.network.service

import fiveagency.internship.food.data.Location
import fiveagency.internship.food.data.network.model.ApiCities
import fiveagency.internship.food.data.network.model.ApiCityDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("location/search/")
    fun citySearch(@Query("query") cityName: String): Single<ApiCities>

    @GET("location/search/")
    fun citySearch(@Query("lattlong") location: Location): Single<ApiCities>

    @GET("location/{cityId}")
    fun cityDetailsEntity(@Path("cityId") cityId: Int): Single<ApiCityDetails>
}