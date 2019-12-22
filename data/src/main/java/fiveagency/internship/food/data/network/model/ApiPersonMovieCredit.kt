package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiPersonMovieCredit(
    @SerializedName("backdrop_path")
    val backdropPath: String?
)