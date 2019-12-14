package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

data class ConsolidatedWeather(

    @SerializedName("id")
    val id: Long,

    @SerializedName("applicable_date")
    val applicableDate: String,

    @SerializedName("weather_state_name")
    val weatherState: String,

    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,

    @SerializedName("wind_speed")
    val windSpeed: Float,

    @SerializedName("wind_direction_compass")
    val windDirection: String,

    @SerializedName("min_temp")
    val minTemp: Double,

    @SerializedName("max_temp")
    val maxTemp: Double,

    @SerializedName("the_temp")
    val currentTemp: Double,

    @SerializedName("air_pressure")
    val airPressure: Float,

    @SerializedName("humidity")
    val humidity: Float,

    @SerializedName("visibility")
    val visibility: Float,

    @SerializedName("predictability")
    val predictability: Int
)