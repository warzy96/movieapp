package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

class ApiVideo(
    @SerializedName("key") val key: String?,
    @SerializedName("site") val source: String?,
    @SerializedName("type") val type: String?
)