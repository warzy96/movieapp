package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

class ApiCredits(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<ApiCast>
)