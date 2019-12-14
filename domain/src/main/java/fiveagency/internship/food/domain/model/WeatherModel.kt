package fiveagency.internship.food.domain.model

class WeatherModel(
    val title: String,
    val id: Int,
    val sunRise: String,
    val sunSet: String,
    val timezoneName: String,
    val weatherDetailsList: List<WeatherDetailsModel>
)