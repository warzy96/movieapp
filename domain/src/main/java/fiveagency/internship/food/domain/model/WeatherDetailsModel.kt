package fiveagency.internship.food.domain.model

class WeatherDetailsModel(
    val id: Long,
    val applicableDate: String,
    val weatherState: String,
    val weatherStateAbbr: String,
    val windSpeed: Float,
    val windDirection: String,
    val minTemp: Double,
    val maxTemp: Double,
    val currentTemp: Double,
    val airPressure: Float,
    val humidity: Float,
    val visibility: Float,
    val predictability: Int
)