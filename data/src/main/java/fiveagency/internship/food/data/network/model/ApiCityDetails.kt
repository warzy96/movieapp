package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiCityDetails(

    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather>,

    @SerializedName("woeid")
    val cityId: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("sun_rise")
    val sunRise: String,

    @SerializedName("sun_set")
    val sunSet: String,

    @SerializedName("timezone")
    val timezoneName: String

)