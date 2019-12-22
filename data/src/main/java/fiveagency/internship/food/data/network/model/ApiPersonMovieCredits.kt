package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiPersonMovieCredits(
    @SerializedName("cast")
    val credits: List<ApiPersonMovieCredit>
)