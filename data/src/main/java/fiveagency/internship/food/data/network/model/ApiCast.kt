package fiveagency.internship.food.data.network.model

import com.google.gson.annotations.SerializedName

class ApiCast(
    @SerializedName("id") val id: Int?,
    @SerializedName("character") val character: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("profile_path") val profileUrl: String?
)