package fiveagency.internship.food.data.network.service

import fiveagency.internship.food.data.network.model.ApiRatings
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbService {

    @GET("/")
    fun movieRatingEntity(@Query("apikey") apiKey: String, @Query("i") imdbId: String): Single<ApiRatings>
}